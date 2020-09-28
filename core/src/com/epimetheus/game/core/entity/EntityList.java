package com.epimetheus.game.core.entity;

import java.util.concurrent.LinkedBlockingQueue;

import com.epimetheus.game.core.component.process.LocationComponent;

public class EntityList extends LinkedBlockingQueue<Entity> {
	private static final long serialVersionUID = 7640154910449879988L;

	public EntityList allEntitiesAt(Location loc) {
		EntityList collisions = new EntityList();
		
		Location floor = loc.floor();
		for (Entity ent : this) {
			LocationComponent locComponent = (LocationComponent)ent.getComponent(LocationComponent.class);
			if (locComponent != null)
				if (locComponent.getLocation().floor() == floor)
					collisions.add(ent);
		}
		
		return collisions;
	}

}
