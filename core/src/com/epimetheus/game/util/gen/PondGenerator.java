package com.epimetheus.game.util.gen;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.epimetheus.game.core.component.process.FluidComponent;
import com.epimetheus.game.core.component.render.RenderComponent;
import com.epimetheus.game.core.entity.ActorEntity;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.entity.Tiles;
import com.epimetheus.game.screen.util.ActionHandler;

public class PondGenerator {
	public static EntityList pondsByLowest(int numPonds, int depth, Map<Location, Tiles> tiles, ActionHandler handler) {
		EntityList ponds = new EntityList();
		LinkedList<List<Location>> pondTiles = pondLocations(depth, tiles);
		
		if (pondTiles.size() < numPonds)
			numPonds = pondTiles.size();
		
		for (int i = 0; i < numPonds; i++) 
			for (Location loc: pondTiles.remove(MathUtils.random(pondTiles.size()-1))) {
				Entity ent = ActorEntity.generate(
						"tile", 
						loc, 
						new Vector2(1,1), 
						0f, 
						1f, 
						(RenderComponent) RenderComponent.generate(Tiles.getTexture(Tiles.ROCKYSOIL)), 
						handler);
				ent.addComponent(FluidComponent.generate("water", loc.getZ()-depth));
				ponds.add(ent);
				}
		
		return ponds;
	}
	
	private static LinkedList<List<Location>> pondLocations(int depth, Map<Location, Tiles> tiles){
		LinkedList<List<Location>> pondLocations = new LinkedList<>();
		LinkedList<Location> open = new LinkedList<>();
		LinkedList<Location> closed = new LinkedList<>();
		
		for (Location loc: tiles.keySet())
			if (loc.getZ() >= depth)
				closed.add(loc);
			else
				open.add(loc);
		
		while (!open.isEmpty()) {
			LinkedList<Location> pond = new LinkedList<>();
			LinkedList<Location> pondCandidates= new LinkedList<>();
			
			Location lowest = open.peekFirst();
			for (Location loc: open) 
				if (loc.getZ() < lowest.getZ())
					lowest = loc;
			
			lowest.removeFromList(open, true);
			closed.add(lowest);
			pond.add(lowest);
			
			for (Location n: lowest.neighbours())
				if (n.isInList(open, true)) {
					pondCandidates.add(n);
				}
			
			while (!pondCandidates.isEmpty()) {
				Location loc = pondCandidates.removeFirst();
				loc.removeFromList(open, true);
				closed.add(loc);
				pond.add(loc);
				
				for (Location n: loc.neighbours()) 
					if (n.isInList(open, true) && !n.isInList(pondCandidates, true)) {
						pondCandidates.add(n);
					}
			}
			pondLocations.add(pond);
		}
		
		return pondLocations;
	}

	public static EntityList river(Map<Location, Tiles> tiles, ActionHandler handler) {
		EntityList list = new EntityList();
		LinkedList<Vector2> points = new LinkedList<>();
		int max_x = 0;
		int max_y = 0;
		
		for (Location loc: tiles.keySet()) {
			if (loc.getX() > max_x)
				max_x = (int)Math.floor((double)loc.getX());
			if (loc.getY() > max_y)
				max_y = (int)Math.floor((double)loc.getY());
		}
		
		Location p0 = new Location(MathUtils.random(max_x-1), 0, 0);
		Location p2 = new Location(0, MathUtils.random(max_y-1), 0);
		Location p1 = new Location(p0.getX()/2, p2.getY()/2, 0);
		
		for (float i = 0; i < 1; i += 0.05f) 
			points.add(quadBezier(p0,p1,p2,i));
		
		System.out.println("p0 = " + p0);
		System.out.println("p1 = " + p1);
		System.out.println("p2 = " + p2);
		
		System.out.println("River Points");
		System.out.println(points);
		
		return list;
	}
	
	private static Vector2 quadBezier(Location p0, Location p1, Location p2, float t) {
		Vector2 point = new Vector2();
		
		point.x = ((float)Math.pow(1-t, 2d))* p0.getX() + (1-t)*2*t*p1.getX() * t*t*p2.getX();
		point.y = ((float)Math.pow(1-t, 2d))* p0.getY() + (1-t)*2*t*p1.getY() * t*t*p2.getY();
		
		return point;
	}
}
