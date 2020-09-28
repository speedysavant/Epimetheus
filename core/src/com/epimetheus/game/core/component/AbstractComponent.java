package com.epimetheus.game.core.component;

import com.epimetheus.game.core.entity.Entity;

public abstract class AbstractComponent implements Component {
	private Entity parent;
	
	@Override
	public Entity getEntity() {
		return parent;
	}
	@Override
	public void setEntity(Entity ent) {
		parent = ent;
	}
}
