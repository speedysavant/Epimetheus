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
		hGroup.addActor(planButton("Wall", 1));
		hGroup.addActor(planButton("Door", 2));
		hGroup.addActor(planButton("Wall", 1));
		hGroup.addActor(planButton("Door", 2));
		hGroup.addActor(planButton("Wall", 1));
		hGroup.addActor(planButton("Door", 2));
		hGroup.addActor(planButton("Wall", 1));
		hGroup.addActor(planButton("Door", 2));
		hGroup.addActor(planButton("Wall", 1));
		hGroup.addActor(planButton("Door", 2));
		hGroup.addActor(planButton("Wall", 1));
		hGroup.addActor(planButton("Door", 2));
		hGroup.addActor(planButton("Wall", 1));
		hGroup.addActor(planButton("Door", 2));
		
		scrollPane = new ScrollPane(hGroup, Skins.default_skin());
		scrollPane.setFadeScrollBars(true);
		scrollPane.setScrollingDisabled(false, true);
		
		this.add(scrollPane).expand().fill();
		this.padTop(25);
		
		this.pack();
		this.setKeepWithinStage(true);
	}
	
	private Button planButton(final String text, final int prototypeId) {
		Button button = new TextButton(text, Skins.default_skin());
		button.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				MouseMode.setCurrent(MouseMode.PLANNING);
				MouseMode.setPrototypeId(prototypeId);
				System.out.println("Planning for " + text);
			}
		});
		return button;
	}

}
