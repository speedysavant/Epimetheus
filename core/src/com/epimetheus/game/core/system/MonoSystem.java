package com.epimetheus.game.core.system;

import java.util.LinkedList;
import java.util.List;

import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.entity.Entity;

public abstract class MonoSystem implements System {
	
	private List<Component> components = new LinkedList<>();
	private Class<? extends Component> componentType;
	
	public void accept(List<Entity> ents) {
		for(Entity ent: ents)
			accept(ent);
	}
	public void accept(Entity ent) {
		Component comp = ent.getComponent(componentType);
		if (comp != null)
			components.add(comp);
	}
}