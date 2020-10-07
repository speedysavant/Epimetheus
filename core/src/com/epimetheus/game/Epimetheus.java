package com.epimetheus.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.epimetheus.game.core.entity.Flora;
import com.epimetheus.game.core.entity.Items;
import com.epimetheus.game.core.entity.Tiles;
import com.epimetheus.game.screen.GameScreen;

/**
 * Launches Epimetheus. It manages the game screen, static resource loading, and other basic setup.
 * 
 * @author Colin Pinnell 2020
 *
 */
public class Epimetheus extends Game {

	Screen screen;
	
	@Override
	public void create () {
		Tiles.load();
		Items.load();
		Flora.load();
		
		Gdx.input.setInputProcessor(new InputMultiplexer());
		
		screen = new GameScreen();
		this.setScreen(screen);
	}

}
