package com.epimetheus.game.core.entity;

import java.util.List;

import com.epimetheus.game.core.component.Component;

public interface Entity {
	public EntityID getId();
	public String getName();
	public void setName(String name);
	public void addComponent(Component component);
	public void removeComponent(Component component);
	public void clearComponents();
	public List<Component> getComponents();
	public Component getComponent(Class<? extends Component> componentType);
}
