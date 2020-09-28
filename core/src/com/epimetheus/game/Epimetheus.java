package com.epimetheus.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.epimetheus.game.core.entity.Items;
import com.epimetheus.game.core.entity.Tiles;
import com.epimetheus.game.screen.GameScreen;

public class Epimetheus extends Game {

	Screen screen;
	
	@Override
	public void create () {
		Tiles.load();
		Items.load();
		
		Gdx.input.setInputProcessor(new InputMultiplexer());
		
		screen = new GameScreen();
		this.setScreen(screen);
	}

}
