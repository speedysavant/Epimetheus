package com.epimetheus.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.epimetheus.game.screen.stage.MapStage;
import com.epimetheus.game.screen.stage.UIStage;

public class GameScreen implements Screen {

	private MapStage mapStage;
	private UIStage uiStage;
	
	public GameScreen() {
		mapStage = new MapStage();
		uiStage = new UIStage();
		
		InputMultiplexer imx = (InputMultiplexer)Gdx.input.getInputProcessor();
		imx.addProcessor(uiStage);
		imx.addProcessor(mapStage.getCameraHarness());
		imx.addProcessor(mapStage);
	}
	
	@Override
	public void show() {
		mapStage.show();
		uiStage.show();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		mapStage.act();
		uiStage.act();
		
		mapStage.draw();
		uiStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		mapStage.resize(width, height);
		uiStage.resize(width, height);
	}

	@Override
	public void pause() {
		mapStage.pause();
		uiStage.pause();
	}

	@Override
	public void resume() {
		mapStage.resume();
		uiStage.resume();
	}

	@Override
	public void hide() {
		mapStage.hide();
		uiStage.hide();
	}

	@Override
	public void dispose() {
		mapStage.dispose();
		uiStage.dispose();
	}

}
