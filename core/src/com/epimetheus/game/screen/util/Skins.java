package com.epimetheus.game.screen.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Skins {
	private static Skin default_skin = null;
	private static Skin tracer_skin = null;
	
	public static Skin default_skin() {
		if (default_skin == null)
			default_skin = new Skin(Gdx.files.internal("skin/default/uiskin.json"));
		return default_skin;
	}
	public static Skin tracer_skin() {
		if (tracer_skin == null)
			tracer_skin = new Skin(Gdx.files.internal("skin/tracer/tracer-ui.json"));
		return tracer_skin;
	}
}
