package com.epimetheus.game.util.gen;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.MathUtils;
import com.epimetheus.game.core.component.render.ScaleComponent;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.entity.MapEntity;
import com.epimetheus.game.core.entity.Tiles;
import com.epimetheus.game.screen.util.ActionHandler;

public class SiteGenerator {
	
	public static MapEntity testMap(int w, int h, int cellSize, ActionHandler actionHandler) {
		MapEntity mapEnt = new MapEntity(
				ScaleComponent.generate(1f),
				actionHandler);
		mapEnt.setPosition(0, 0);
		mapEnt.setHeight(h * cellSize);
		mapEnt.setWidth(w*cellSize);
		mapEnt.setDebug(true);
		mapEnt.setSize(w*cellSize, h*cellSize);
		mapEnt.setBounds(0, 0, w*cellSize, h* cellSize);
		
		Map<Location, Tiles> tiles = new HashMap<>();
		double height_scale = 3;
		double fertility_scale = (double) MathUtils.random() * 1d;
		double fertility_offset = (double) MathUtils.random() * 100d;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				double xin = (double)x / (double)w * height_scale;
				double yin = (double)y / (double)h * height_scale;
				int height = (int)((SimplexNoise.noise(xin * fertility_scale, yin * fertility_scale) +1) * 128d);
				int fertility = (int)((SimplexNoise.noise(xin + fertility_offset, yin + fertility_offset) +1) * 128d) ;
				tiles.put(new Location(x,y,height), setGradientTile(x, y, fertility, height));
			}
		}
		mapEnt.setTiles(tiles);
		
		
		return mapEnt;
	}
	
	static Tiles setGradientTile(int x, int y, int height, int fertility) {
		int val = (height + fertility) /2;
		if (val < 32) return Tiles.E_0;
		else if (val < 64) return Tiles.E_1;
		else if (val < 96) return Tiles.E_2;
		else if (val < 128) return Tiles.E_3;
		else if (val < 160) return Tiles.E_4;
		else if (val < 192) return Tiles.E_5;
		else if (val < 224) return Tiles.E_6;
		else return Tiles.E_7;
	}
	
	static Tiles setLandscapeTile(int x, int y, int height, int fertility) {
		if (height < 32) return Tiles.SOIL;
		else if (height < 96) return Tiles.ROCKYSOIL;
		else if (height < 128) {
			if (MathUtils.randomBoolean())
				return Tiles.SAND;
			else return Tiles.GRAVEL;
		}
		else return Tiles.STONE; // place walls here
	}
	
	static void bezierCube(float t) {
		
	}
}
