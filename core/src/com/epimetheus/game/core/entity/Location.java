package com.epimetheus.game.core.entity;

import java.util.List;

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
	
	public Location west() {
		return new Location(x-1f, y, z);
	}
	public Location east() {
		return new Location(x+1f, y, z);
	}
	public Location north() {
		return new Location(x, y+1f, z);
	}
	public Location south() {
		return new Location(x, y-1f, z);
	}
	public Location northwest() {
		return new Location(x-1f, y+1f, z);
	}
	public Location northeast() {
		return new Location(x+1f, y+1f, z);
	}
	public Location southwest() {
		return new Location(x-1f, y-1f, z);
	}
	public Location southeast() {
		return new Location(x+1f, y-1f, z);
	}
	
	public Location[] neighbours() {
		return new Location[] {
				this.west(),
				this.east(),
				this.north(),
				this.south(),
				this.northwest(),
				this.southeast(),
				this.northeast(),
				this.southwest()
		};
	}
	
	public float distanceTo(Location loc) {
		float xd = x-loc.x;
		float yd = y-loc.y;
		return (float)(Math.sqrt(xd*xd + yd*yd));
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Location))
			return false;
		Location loc = ((Location) o);
		return loc.x == x && loc.y == y;
	}
	@Override
	public int hashCode() {
		return (int)((z + (y * 100) + (x * 10000)) * 100);
	}
	@Override
	public int compareTo(Location c) {
		return (int) Math.ceil((c.x - x) + (c.y-y) + (c.z-z));
	}
	
	public boolean isInList(List<Location> list, boolean xy_only) {
		if (xy_only) {
			for (Location n: list) 
				if (n.getX() == x && n.getY() == y) 
					return true;
			return false;
		} else {
			for (Location n: list) 
				if (n.getX() == x && n.getY() == y && n.getZ() == z) 
					return true;
			return false;
		}
	}
	
	public void removeFromList(List<Location> list, boolean xy_only) {
		Location toRemove = null;
		if (xy_only) {
			for (Location n: list) 
				if (n.getX() == x && n.getY() == y) { 
					toRemove = n;
					break;
				}
		} else {
			for (Location n: list) 
				if (n.getX() == x && n.getY() == y && n.getZ() == z) { 
					toRemove = n;
					break;
				}
		}
		if (toRemove != null)
			list.remove(toRemove);
	}
	
	public static void main(String[] args) {
		Location a = new Location(1,1,2);
		Location b = new Location(1,1.0f,15f);
		System.out.println("a = b :: " + a.equals(b) );
	}
}
