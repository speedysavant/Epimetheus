package com.epimetheus.game.core.system;

import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;

public interface System {
	public void accept(EntityList ents);
	public void accept(Entity ent);
	public void remove(Entity ent);	
	public void process();
	public void setSystemController(SystemController controller);
}