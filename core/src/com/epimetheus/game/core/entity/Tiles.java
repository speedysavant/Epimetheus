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
	SOIL, ROCKYSOIL, SAND, GRAVEL, STONE,
	E_0, E_1, E_2, E_3, E_4, E_5, E_6, E_7, E_8;
	
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
		textures.put(SOIL, atlas.findRegion("fertilesoil"));
		textures.put(ROCKYSOIL, atlas.findRegion("rockysoil"));
		textures.put(SAND, atlas.findRegion("sand"));
		textures.put(GRAVEL, atlas.findRegion("gravel"));
		textures.put(STONE, atlas.findRegion("stone"));
		
		textures.put(E_0, atlas.findRegion("elevation", 0));
		textures.put(E_1, atlas.findRegion("elevation", 1));
		textures.put(E_2, atlas.findRegion("elevation", 2));
		textures.put(E_3, atlas.findRegion("elevation", 3));
		textures.put(E_4, atlas.findRegion("elevation", 4));
		textures.put(E_5, atlas.findRegion("elevation", 5));
		textures.put(E_6, atlas.findRegion("elevation", 6));
		textures.put(E_7, atlas.findRegion("elevation", 7));
		textures.put(E_8, atlas.findRegion("elevation", 8));
	}
}
