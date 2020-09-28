package com.epimetheus.game.core.entity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Location implements Comparable<Location>{
	private float x = 0;
	private float y = 0;
	private float z = 0;
	
	public Location(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Location(Vector3 vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	
	public Location floor() {
		return new Location((int)Math.floor(getX()), (int)Math.floor(getY()), (int)Math.floor(getZ()));
	}
	public Vector2 toVec2() {
		return new Vector2(x, y);
	}
	public Vector3 toVec3() {
		return new Vector3(x, y, z);
	}
	
	/**
	 * Converts a screen-coordinate vector into a Location in map-grid coordinates.
	 * 
	 * This method handles camera unprojection.
	 * 
	 * @param screen
	 * @param camera
	 * @return
	 */
	public static Location fromScreenCoords(Vector3 screen, Camera camera) {
		return fromWorldCoords(camera.unproject(screen.cpy()));
	}
	
	/**
	 * Converts from world coordinates into a Location in map-grid coordinates.
	 * @param world
	 * @return
	 */
	public static Location fromWorldCoords(Vector3 world) {
		world.x /= (float) Tiles.getSize();
		world.y /= (float) Tiles.getSize();
		world.z /= (float) Tiles.getSize();
		return new Location(world);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Location))
			return false;
		Location loc = (Location) o;
		return loc.x == x && loc.y == y && loc.z == z;
	}
	@Override
	public int hashCode() {
		return (int)((z + (y * 100) + (x * 10000)) * 100);
	}
	@Override
	public int compareTo(Location c) {
		return (int) Math.ceil((c.x - x) + (c.y-y) + (c.z-z));
	}
}
