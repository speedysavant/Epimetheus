package com.epimetheus.game.core.system;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;

public abstract class MultiSystem implements System {
	
	protected List<Map<Class<? extends Component>, Component>> componentList = new LinkedList<>();
	
	protected SystemController controller;
	public void setSystemController(SystemController controller) {
		this.controller = controller;
	}
	
	protected void accept(EntityList ents, @SuppressWarnings("unchecked") Class<? extends Component> ... types) {
		List<Class<? extends Component>> classList = Arrays.asList(types);
		for (Entity ent: ents) {
			Map<Class<? extends Component>, Component> map = new HashMap<>();
			for (Component c: ent.getComponents()) 
				if (classList.contains(c.getClass()))
					map.put(c.getClass(), c);
			componentList.add(map);
		}
	}
	
	public void remove(Entity ent) {
		Map<Class<? extends Component>, Component> toRemove = null;
		for (Map<Class<? extends Component>, Component> entry: componentList)
			if (((Component)((entry.values().toArray()[0]))).getEntity() == ent) {
				toRemove = entry;
				break;
			}
		if (toRemove != null)
			componentList.remove(toRemove);
	}
}
