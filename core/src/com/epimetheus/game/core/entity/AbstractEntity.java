package com.epimetheus.game.core.entity;

import java.util.LinkedList;
import java.util.List;

import com.epimetheus.game.core.component.Component;

public class AbstractEntity implements Entity {
	private EntityID id;
	private String name;
	private List<Component> components = new LinkedList<>();
	
	private AbstractEntity() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EntityID getId() {
		return id;
	}
	public void addComponent(Component component) {
		components.add(component);
		component.setEntity(this);
	}
	public void removeComponent(Component component) {
		components.remove(component);
	}
	public void clearComponents() {
		components.clear();
	}
	public List<Component> getComponents(){
		return components;
	}
	public Component getComponent(Class<? extends Component> componentType) {
		if (componentType == null)
			throw new NullPointerException("No Component Type given to fetch Component from Entity " + id.toString());
		for(Component comp: components)
			if (comp.getClass() == componentType)
				return comp;
		return null;
	}
	public boolean hasComponent(Class<? extends Component> componentType) {
		Component c = getComponent(componentType);
		return c != null;
	}
	
	public static Entity generate() {
		AbstractEntity ent = new AbstractEntity();
		ent.id = new EntityID();
		return ent;
	}
}
