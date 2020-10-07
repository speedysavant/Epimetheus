package com.epimetheus.game.screen.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.epimetheus.game.screen.util.MouseMode;
import com.epimetheus.game.screen.util.Skins;

public class PlanPalette extends Window {

	private ScrollPane scrollPane;
	private HorizontalGroup hGroup;
	
	public PlanPalette(String title, Skin skin) {
		super(title, skin);
		
		hGroup = new HorizontalGroup();
		hGroup.space(5);
		hGroup.padBottom(20);
		
		// should be pulled from Prototypes.getAll() or something
		hGroup.addActor(jobButton("Wall", 1, MouseMode.PLANNING));
		hGroup.addActor(jobButton("Door", 2, MouseMode.PLANNING));
		hGroup.addActor(jobButton("Cut Plants", 3, MouseMode.CUT_PLANT));
		hGroup.addActor(jobButton("Wall", 1, MouseMode.PLANNING));
		hGroup.addActor(jobButton("Door", 2, MouseMode.PLANNING));
		hGroup.addActor(jobButton("Cut Plants", 3, MouseMode.CUT_PLANT));
		
		scrollPane = new ScrollPane(hGroup, Skins.default_skin());
		scrollPane.setFadeScrollBars(true);
		scrollPane.setScrollingDisabled(false, true);
		
		this.add(scrollPane).expand().fill();
		this.padTop(25);
		
		this.pack();
		this.setKeepWithinStage(true);
	}
	
//	private Button planButton(final String text, final int prototypeId) {
//		Button button = new TextButton(text, Skins.default_skin());
//		button.addListener(new ClickListener() {
//			@Override
//			public void clicked (InputEvent event, float x, float y) {
//				MouseMode.setCurrent(MouseMode.PLANNING);
//				MouseMode.setPrototypeId(prototypeId);
//			}
//		});
//		return button;
//	}
	private Button jobButton(final String text, final int prototypeId, MouseMode mouseMode) {
		Button button = new TextButton(text, Skins.default_skin());
		button.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				MouseMode.setCurrent(mouseMode);
				MouseMode.setPrototypeId(prototypeId);
				System.out.println("Planning for " + text);
			}
		});
		return button;
	}
}
