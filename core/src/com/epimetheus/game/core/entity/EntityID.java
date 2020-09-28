package com.epimetheus.game.core.entity;

public class EntityID {
	public static long next_id = 100;
	public static long next() { return next_id++; }
	
	private long id;
	
	public EntityID() {
		this.id = EntityID.next();
	}
	public long getID() { return id; }
	
	@Override public String toString() {
		return "" + id;
	}
}
