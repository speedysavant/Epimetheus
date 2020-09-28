package com.epimetheus.game.core.component;

import com.epimetheus.game.core.entity.Entity;

public interface Component {
	public Entity getEntity();
	public void setEntity(Entity ent);
	public Class<? extends Component> getComponentType(); 
}