package com.epimetheus.game.util.gen;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;
import com.epimetheus.game.core.component.render.RenderComponent;
import com.epimetheus.game.core.entity.ActorEntity;
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
			for (Location loc: pondTiles.removeFirst()) 
				ponds.add(ActorEntity.generate(
						"tile", 
						loc, 
						new Vector2(1,1), 
						0f, 
						1f, 
						(RenderComponent) RenderComponent.generate(Tiles.getTexture(Tiles.ROCKYSOIL)), 
						handler));
		
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
}
