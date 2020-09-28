package com.epimetheus.game.core.component.process;

import com.badlogic.gdx.math.Vector2;
import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.entity.Location;

public class LocationComponent extends AbstractComponent {

	private Location location;
	private Vector2 bounds;
	
	private LocationComponent(Location location, Vector2 bounds) {
		this.location = location;
		this.bounds = bounds;
	}
	/**
	 * The Location is the cartesian coordinate of the bottom left corner of the entity. This is a float value,
	 * referred to in map-grid coordinate values. I.e., one location unit is one tile space.
	 * @return Location
	 */
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	/**
	 * The Bounds of an Entity specifies the width and height of the entity, or is the vector from the Location to the top right corner.
	 * This is a float value, referred to in map-grid coordinate values. I.e., one location unit is one tile space.
	 * @return Vector2
	 */
	public Vector2 getBounds() {
		return bounds;
	}
	public void setBounds(Vector2 bounds) {
		this.bounds = bounds;
	}
	public boolean inBounds(Vector2 point) {
		boolean inX = (location.getX() < point.x && point.x <= (location.getX() + bounds.x));
		boolean inY = (location.getY() < point.y && point.y <= (location.getY() + bounds.y));
		return inX && inY;
	}
	@Override
	public Class<? extends Component> getComponentType() {
		return LocationComponent.class;
	}

	public static LocationComponent generate(Location location, Vector2 bounds) {
		return new LocationComponent(location, bounds);
	}
}
