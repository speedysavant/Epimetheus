package com.epimetheus.game.core.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.component.render.ScaleComponent;
import com.epimetheus.game.screen.util.ActionHandler;
import com.epimetheus.game.screen.util.MouseMode;

/**
 * A specialized Entity for rendering the map, storing all map data, and handling all UI interactions involving the map.
 * @author pinne
 *
 */
public class MapEntity extends Actor implements Entity {

	private EntityID id;
	private List<Component> components = new LinkedList<>();
	
	private Map<Location, Tiles> tiles;
	
	public MapEntity(ScaleComponent scaleComponent, final ActionHandler actionHandler) {
		super();
		this.addComponent(scaleComponent);
		this.toBack();
		
		addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Location location = Location.fromWorldCoords(new Vector3(x, y, 0));
				if (button == 0)
					actionHandler.clickedOn(location, getTileAt(location));
				else if (button == 1) {
					MouseMode.setCurrent(MouseMode.SELECTING);
					MouseMode.setPrototypeId(0);
				}
				return true;
			}
		});
	}
	
	public Map<Location, Tiles> getTiles() {
		return tiles;
	}
	public Tiles getTileAt(Location location) {
		return tiles.get(location.floor());
	}
	public void setTileAt(Location location, Tiles tile) {
		tiles.put(location.floor(), tile);
	}
	public void setTiles(Map<Location, Tiles> tiles) {
		this.tiles = tiles;
	}
	
	@Override 
	public void draw (Batch batch, float parentAlpha) {
		float scale = ( (ScaleComponent)(getComponent(ScaleComponent.class)) ).getScale();
		for (Entry<Location, Tiles> entry : tiles.entrySet()) {
			batch.draw(
					Tiles.getTexture(entry.getValue()), 
					entry.getKey().getX() * scale * Tiles.getSize(), // x-position 
					entry.getKey().getY() * scale * Tiles.getSize(), // y-position
					0f, // x-origin
					0f, // y-origin
					(float)Tiles.getSize(), // width 
					(float)Tiles.getSize(), // height
					(float)scale, // x-scale
					(float)scale, // y-scale
					0f, // rotation
					true // clockwise
					);
		}
	}
	
	
	@Override
	public EntityID getId() {
		return id;
	}
	@Override
	public void addComponent(Component component) {
		components.add(component);
	}
	@Override
	public void removeComponent(Component component) {
		components.remove(component);
	}
	@Override
	public void clearComponents() {
		components.clear();
	}
	@Override
	public List<Component> getComponents(){
		return components;
	}
	@Override
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
	public void destroy() {
		
	}
}