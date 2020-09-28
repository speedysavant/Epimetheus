package com.epimetheus.game.core.entity;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.component.process.LocationComponent;
import com.epimetheus.game.core.component.render.RenderComponent;
import com.epimetheus.game.core.component.render.RotationComponent;
import com.epimetheus.game.core.component.render.ScaleComponent;
import com.epimetheus.game.screen.util.ActionHandler;

public class ActorEntity extends Actor implements Entity {
	private EntityID id;
	private String name;
	private List<Component> components = new LinkedList<>();
	
	private ActorEntity(final ActionHandler handler) {
		super();
		this.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				handler.clickedOn(ActorEntity.super.getX(), ActorEntity.super.getY(), ActorEntity.this);
			}
		});
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

	public static Entity generate(
			String name, 
			Location location, 
			Vector2 bounds, 
			float rotation, 
			float scale, 
			RenderComponent rc,
			final ActionHandler handler) {
		ActorEntity actor = new ActorEntity(handler);
		actor.id = new EntityID();
		actor.setName(name);
		
		
		
		LocationComponent loc = LocationComponent.generate(location, bounds);
		
		actor.setPosition(
				loc.getLocation().getX() * (float)Tiles.getSize() , 
				loc.getLocation().getY() * (float)Tiles.getSize());
		actor.setBounds(
				loc.getLocation().getX() * (float)Tiles.getSize(), 
				loc.getLocation().getY() * (float)Tiles.getSize(), 
				loc.getBounds().x * (float)Tiles.getSize(), 
				loc.getBounds().y * (float)Tiles.getSize());
		
		actor.addComponent(loc);
		actor.addComponent(rc);
		actor.addComponent(ScaleComponent.generate(scale));
		actor.addComponent(RotationComponent.generate(0.0f));
		actor.debug();
		return actor;
	}
}
