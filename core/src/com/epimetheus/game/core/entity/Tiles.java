package com.epimetheus.game.core.entity;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum Tiles {
	DIRT, GRASS, STONE;
	
	private static Map<Tiles, TextureRegion> textures = new HashMap<>();
	private static int size = 128;
	
	public static int getSize() {
		return size;
	}
	public static TextureRegion getTexture(Tiles tile) {
		return textures.get(tile);
	}
	public static void setTexture(Tiles tile, TextureRegion texture) {
		textures.put(tile, texture);
	}
	
	public static void load() {
		TextureAtlas atlas = new TextureAtlas("img/tile/tiles.txt");
		textures.put(DIRT, atlas.findRegion("dirt1"));
		textures.put(GRASS, atlas.findRegion("grass1"));
		textures.put(STONE, atlas.findRegion("stone1"));
	}
}
