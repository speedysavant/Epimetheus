package com.epimetheus.game.util.gen;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.epimetheus.game.core.component.process.LocationComponent;
import com.epimetheus.game.core.component.render.RenderComponent;
import com.epimetheus.game.core.component.render.RotationComponent;
import com.epimetheus.game.core.component.render.ScaleComponent;
import com.epimetheus.game.core.entity.AbstractEntity;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.entity.MapEntity;
import com.epimetheus.game.core.entity.Tiles;
import com.epimetheus.game.screen.util.ActionHandler;

public class MapGenerator {
	
	public static MapEntity flatMap(int w, int h, int cellSize, ActionHandler handler) {
		MapEntity ent = new MapEntity(ScaleComponent.generate(1.0f), handler);
		
		ent.setPosition(0, 0);
		ent.setHeight(h * cellSize);
		ent.setWidth(w*cellSize);
		ent.setDebug(true);
		ent.setSize(w*cellSize, h*cellSize);
		ent.setBounds(0, 0, w*cellSize, h* cellSize);
		ent.addComponent(ScaleComponent.generate(0.5f));
		
		Map<Location, Tiles> tiles = new HashMap<>();
		for (int x = 0; x < w; x++) 
			for (int y = 0; y < h; y++)
				tiles.put(new Location(x, y, 0), Tiles.ROCKYSOIL);
		ent.setTiles(tiles);

		return ent;
	}
	
	public static EntityList flatTiles(int w, int h) {
		EntityList list = new EntityList();
		Texture texture = new Texture("img/tile/dirt1.png");
		
		for (int x = 0; x < w; x++)
			for (int y = 0; y < h; y++)
				list.add(flatTile(x, y, texture));
		
		return list;
	}
	
	private static Entity flatTile(int x, int y, Texture texture) {
		Entity ent = AbstractEntity.generate();
		ent.addComponent(LocationComponent.generate(new Location(x, y, 0), new Vector2(texture.getWidth(),texture.getHeight())));
		ent.addComponent(RenderComponent.generate(texture));
		ent.addComponent(RotationComponent.generate(0.0f));
		return ent;
	}
}