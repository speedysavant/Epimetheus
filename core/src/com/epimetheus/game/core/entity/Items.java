package com.epimetheus.game.core.entity;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.epimetheus.game.core.component.render.RenderComponent;
import com.epimetheus.game.screen.util.ActionHandler;

public class Items {
	private static Map<String, Entity> itemPrototypes = new HashMap<>();
	private static Map<String, TextureRegion> itemTextures = new HashMap<>();
	
	public static Entity generate(String name, Location location, ActionHandler handler) {
		Entity prototype = itemPrototypes.get(name);
		
		Entity ent = ActorEntity.generate(
				prototype.getName(), 
				location, 
				new Vector2(1f, 1f), 
				0f, 
				1f, 
				(RenderComponent)RenderComponent.generate(itemTextures.get(name)),
				handler);
		
		return ent;
	}
	
	public static void load() {
		TextureAtlas atlas = new TextureAtlas("img/tile/items.txt");
		// Load Item Images
		TextureRegion tex = atlas.findRegion("genericItem_color", 58);
		itemTextures.put("Test Item", tex);
		
		// Then load Item Prototypes
		Entity ent = AbstractEntity.generate();
		ent.setName("Test Item");
		ent.addComponent(RenderComponent.generate(itemTextures.get("test")));
		itemPrototypes.put(ent.getName(), ent);
	}
}
