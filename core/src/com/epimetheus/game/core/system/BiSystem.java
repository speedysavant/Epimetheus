package com.epimetheus.game.core.system;

import java.util.LinkedList;
import java.util.List;

import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;

public abstract class BiSystem implements System {
	
	protected List<BiComponentContainer> components = new LinkedList<>();
	protected Class<? extends Component> componentType1, componentType2;
	
	public void accept(EntityList ents) {
		for(Entity ent: ents)
			accept(ent);
	}
	public void accept(Entity ent) {
		Component comp1 = ent.getComponent(componentType1);
		Component comp2 = ent.getComponent(componentType2);
		
		if (comp1 != null && comp2 != null)
			components.add(new BiComponentContainer(comp1, comp2));
	}
}
