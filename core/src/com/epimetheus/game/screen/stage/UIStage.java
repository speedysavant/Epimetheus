package com.epimetheus.game.screen.stage;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.epimetheus.game.screen.ui.PlanPalette;
import com.epimetheus.game.screen.util.Skins;

public class UIStage extends Stage implements Screen {

	private Table layout;
	private PlanPalette planPalette;
	
	public UIStage() {
		super(new ExtendViewport(800,600, new OrthographicCamera()));
		
		planPalette = new PlanPalette("Plans", Skins.default_skin());
		planPalette.addListener(new InputListener() {
			@Override 
			public void exit(InputEvent event, float x, float y, int pointer, @Null Actor fromActor) {
				UIStage.this.unfocus(planPalette);
			}
		});
		
		layout = new Table();
		layout.setFillParent(true);
		layout.left().top().pad(10);
		layout.add(planPalette).width(500).height(100);
		
		this.addActor(layout);
		layout.pack();
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
	}

	@Override
	public void resize(int width, int height) {
		getViewport().update(width, height);
		layout.layout();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

}
