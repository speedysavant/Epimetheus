package com.epimetheus.game.core.entity;

import java.util.LinkedList;

public class LocationList extends LinkedList<Location> {
	private static final long serialVersionUID = 1241442724209947863L;

	public boolean remove(Location loc) {
		return remove(loc, false);
	}
	
	public boolean remove(Location loc, boolean xy_only) {
		Location toRemove = null;
		if (xy_only) {
			for (Location n: this) 
				if (n.getX() == loc.getX() && n.getY() == loc.getY()) { 
					toRemove = n;
					break;
				}
		} else {
			for (Location n: this) 
				if (n.getX() == loc.getX() && n.getY() == loc.getY() && n.getZ() == loc.getZ()) { 
					toRemove = n;
					break;
				}
		}
		if (toRemove != null)
			return super.remove(toRemove);
		else return false;
	}
	
	@Override
	public boolean contains(Object o) {
		if (!(o instanceof Location))
			return false;
		
		Location loc = (Location) o;
		
		for (Location l: this)
			if (loc.equals(l))
				return true;
		return false;
	}
	
}
