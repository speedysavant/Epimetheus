package com.epimetheus.game.core.component.render;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.component.process.LocationComponent;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.entity.Tiles;
import com.epimetheus.game.screen.util.ActionHandler;

public class ActorComponent extends Actor implements Component {

	private Entity parent;
	
	public ActorComponent(final ActionHandler handler) {
		super();
		this.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				handler.clickedOn(ActorComponent.super.getX(), ActorComponent.super.getY(), parent);
			}
		});
	}
	
	@Override
	public Entity getEntity() {
		return parent;
	}
	@Override
	public void setEntity(Entity ent) {
		parent = ent;
	}
	@Override
	public Class<? extends Component> getComponentType() {
		return ActorComponent.class;
	}
	
	public static ActorComponent generate(LocationComponent lc, ActionHandler handler) {
		ActorComponent actor = new ActorComponent(handler);
		Location location = lc.getLocation();
		Vector2 bounds = lc.getBounds();
		actor.setPosition(location.getX() * (float)Tiles.getSize() , location.getY() * (float)Tiles.getSize());
		actor.setBounds(
				location.getX() * (float)Tiles.getSize(), 
				location.getY() * (float)Tiles.getSize(), 
				bounds.x * (float)Tiles.getSize(), 
				bounds.y * (float)Tiles.getSize());
		actor.debug();
		return actor;
	}
}
