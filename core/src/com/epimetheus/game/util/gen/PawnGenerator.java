package com.epimetheus.game.util.gen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.epimetheus.game.core.component.process.WorkerComponent;
import com.epimetheus.game.core.component.render.RenderComponent;
import com.epimetheus.game.core.entity.ActorEntity;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.screen.util.ActionHandler;

public class PawnGenerator {

	public static EntityList generateRandom(int numPawns, int x_bound, int y_bound, ActionHandler handler) {
		EntityList ents = new EntityList();
		
		TextureAtlas atlas = new TextureAtlas("img/pawn/femaleAdventurer.txt");
		TextureRegion region = atlas.findRegion("character_femaleAdventurer_idle");
		
		for ( int i = 0; i < numPawns; i++) {
			Entity ent =
			ActorEntity.generate(
					"Pawn " + i, 
					generateRandom(x_bound, y_bound), 
					new Vector2(1,1), 
					0f, 
					1f, 
					(RenderComponent)RenderComponent.generate(region), 
					handler);
			ent.addComponent(WorkerComponent.generate(1));
			ents.add(ent);
		}
		
		return ents;
	}
	
	private static Location generateRandom(float x_bound, float y_bound) {
		float x = (float)(Math.random() * x_bound);
		float y = (float)(Math.random() * y_bound);
		return new Location(x, y, 0);
	}
	
}
