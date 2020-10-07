package com.epimetheus.game.core.entity;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.epimetheus.game.core.component.process.PlantComponent;
import com.epimetheus.game.core.component.render.RenderComponent;
import com.epimetheus.game.screen.util.ActionHandler;

public class Flora {
	private static Map<String, Entity> prototypes = new HashMap<>();
	private static Map<String, TextureRegion> textures = new HashMap<>();
	
	public static Entity generate(String name, Location location, ActionHandler handler) {
		Entity prototype = prototypes.get(name);
		
		Entity ent = ActorEntity.generate(
				prototype.getName(), 
				location, 
				new Vector2(1f, 1f), 
				0f, 
				1f, 
				(RenderComponent)RenderComponent.generate(textures.get(name)),
				handler);
		
		ent.addComponent(PlantComponent.generate());
		
		return ent;
	}
	
	/**
	 * Loads the Flora class for use. Must be called at least once before being used.
	 */
	public static void load() {
		TextureAtlas atlas = new TextureAtlas("img/tile/plants.txt");
		// Load Item Images
		TextureRegion tex = atlas.findRegion("foliagePack", 1);
		textures.put("Test Plant", tex);
		
		// Then load Item Prototypes
		Entity ent = AbstractEntity.generate();
		ent.setName("Test Plant");
		ent.addComponent(RenderComponent.generate(textures.get("test")));
		prototypes.put(ent.getName(), ent);
	}
}
