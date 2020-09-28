package com.epimetheus.game.core.entity;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 
 * Holds and handles all basic static data for map tiles in the game world. This includes:
 * - Game Attributes
 * - Pathfinding Attributes
 * - Graphical textures
 * - Tile Size
 * 
 * It must be loaded before use by calling load() 
 * 
 * @author Colin Pinnell 2020
 *
 */
public enum Tiles {
	DIRT, GRASS, STONE;
	
	private static Map<Tiles, TextureRegion> textures = new HashMap<>();
	private static int size = 128;
	
	/**
	 * Provides the pixel size of tile textures
	 * @return The size in pixels of the tiles used for textures 
	 */
	public static int getSize() {
		return size;
	}
	/**
	 * @param tile 	The Tiles type being requested 
	 * @return		the TextureRegion appropriate to the given Tiles parameter.
	 */
	public static TextureRegion getTexture(Tiles tile) {
		return textures.get(tile);
	}
	/**
	 * Allows the texture of a given Tile to be changed dynamically
	 * @param tile		The Tiles type to be changed
	 * @param texture	The TextureRegion that the Tile should be represented by on the map
	 */
	public static void setTexture(Tiles tile, TextureRegion texture) {
		textures.put(tile, texture);
	}
	
	/**
	 * Loads the default textures used by the map to represent tiles. Must be called before attempting to render the map.
	 */
	public static void load() {
		TextureAtlas atlas = new TextureAtlas("img/tile/tiles.txt");
		textures.put(DIRT, atlas.findRegion("dirt1"));
		textures.put(GRASS, atlas.findRegion("grass1"));
		textures.put(STONE, atlas.findRegion("stone1"));
	}
}
