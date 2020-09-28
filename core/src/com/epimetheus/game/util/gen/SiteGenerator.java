package com.epimetheus.game.util.gen;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
		
		Random rand = new Random();
		Map<Location, Tiles> tiles = new HashMap<>();
		for (int x = 0; x < w; x++) 
			for (int y = 0; y < h; y++) {
				float gen = rand.nextFloat();
				if (gen < 0.80)
					tiles.put(new Location(x, y, 0), Tiles.GRASS);
				else if (gen < 0.90)
					tiles.put(new Location(x, y, 0), Tiles.DIRT);
				else
					tiles.put(new Location(x, y, 0), Tiles.STONE);
			}
		mapEnt.setTiles(tiles);
		
		return mapEnt;
	}
	
}
