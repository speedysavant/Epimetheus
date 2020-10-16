package com.epimetheus.game.core.system.process;

import java.util.LinkedList;

import com.epimetheus.game.core.component.process.LocationComponent;
import com.epimetheus.game.core.component.process.MoveComponent;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.entity.LocationList;
import com.epimetheus.game.core.system.MultiSystem;
import com.epimetheus.game.screen.stage.MapStage;

public class PathingSystem extends MultiSystem {

	public enum MoveType {WALK, HOVER, GROUND}
	private MapStage mapStage;
	private EntityList movers = new EntityList();
	
	public PathingSystem(MapStage mapStage) {
		this.mapStage = mapStage; 
	}
	
	@Override
	public void accept(EntityList ents) {
		for (Entity ent: ents)
			accept(ent);
	}

	@Override
	public void accept(Entity ent) {
		if (ent.hasComponent(MoveComponent.class)) 
			movers.add(ent);
	}

	@Override
	public void process(float delta) {
		for (Entity ent: movers) {
			MoveComponent mover = ((MoveComponent)(ent.getComponent(MoveComponent.class)));
			LocationComponent lc = ((LocationComponent)(ent.getComponent(LocationComponent.class)));
			if (mover.getTarget() != null) 
				moveToTarget(ent, mover, lc, delta);
		}
	}
	
	private void moveToTarget(Entity ent, MoveComponent mover, LocationComponent lc, float delta) {
		if (mover.getPath().isEmpty() && mover.getTarget() != null)
			buildPathTo(mover.getTarget(), mover, lc);
		step(mover, lc, delta);
		if (lc.getLocation().floor().equals(mover.getTarget().floor()))
			completePath(mover, lc);
	}
	
	private void buildPathTo(Location dest, MoveComponent mover, LocationComponent lc) {
		if (!mapStage.isValidLocation(dest)) {
			return;
		}
		
		LocationList open = new LocationList();
		LocationList closed = new LocationList();
		LinkedList<PathNode> pathNodes = new LinkedList<>();
		
		Location loc = lc.getLocation().floor();
		closed.add(loc);
		pathNodes.addLast(new PathNode(loc, 0, null));
		for (Location n: loc.neighbours())
			open.add(n);
		
		buildPath_recurse(dest, pathNodes, open, closed);
		
		mover.setPath(reconstructPath(pathNodes));
		
	}
	
	private LinkedList<Location> reconstructPath(LinkedList<PathNode> pathNodes) {
		LinkedList<Location> path = new LinkedList<>();
		PathNode step = pathNodes.removeLast();
		
		while (step.prev != null) {
			path.addFirst(step.loc);
			step = step.prev;
		}
		
		return path;
	}

	private void step(MoveComponent mover, LocationComponent lc, float delta) {
		float movePoints = mover.getMovePerSecond() * delta;
		
		Location nextLoc = null;
		Location currLoc = lc.getLocation();
		Location prevLoc = currLoc;
		while (!mover.getPath().isEmpty()) {
			nextLoc = mover.getPath().pop();
			float dist = prevLoc.distanceTo(nextLoc);
			if (dist > movePoints)
				break;
			movePoints -= dist;
			prevLoc = nextLoc;
		}
		
		float percentTravelled = movePoints / prevLoc.distanceTo(nextLoc);
		float xd = nextLoc.getX() + ((nextLoc.getX() - prevLoc.getX()) * percentTravelled);
		float yd = nextLoc.getY() + ((nextLoc.getY() - prevLoc.getY()) * percentTravelled);
		float zd = nextLoc.getZ() + ((nextLoc.getZ() - prevLoc.getZ()) * percentTravelled);
		lc.setLocation(new Location(xd, yd, zd));
	}
	
	private void completePath(MoveComponent mover, LocationComponent lc) {
		mover.setTarget(null);
	}
	
	
	private void buildPath_recurse(Location destination, LinkedList<PathNode> pathNodes, LocationList open, LocationList closed) {
		if (open.isEmpty()) {
			pathNodes.clear();
			return;
		}
		
		Location loc = open.getFirst();
		for (Location i: open)
			if (i.distanceTo(destination) < loc.distanceTo(destination))
				loc = i;
		
		open.remove(loc);
		closed.add(loc);
		
		PathNode prev = pathNodes.getLast();
		float cost = costToDest(loc, destination) + costAt(loc) + prev.cost;
		PathNode next = new PathNode(loc,cost,prev);
		pathNodes.addLast(next);
		
		if (loc.equals(destination)) {
			return;
		} else {
			for (Location n: loc.neighbours()) {
				if (!n.isInList(closed, false))
					open.add(n);
			}
			buildPath_recurse(destination, pathNodes, open, closed);
		}
	}
	
	private float costAt(Location loc) {
		return this.mapStage.pathCostAt(loc);
	}
	
	private float costToDest(Location loc, Location dest) {
		double x = Math.abs(loc.getX() - dest.getX());
		double y = Math.abs(loc.getY() - dest.getY());
		double z = Math.abs(loc.getZ() - dest.getZ());
		return (float)(x+y+z);
	}
	
	class PathNode {
		Location loc;
		float cost;
		PathNode prev;
		PathNode(Location loc, float cost, PathNode prev){
			this.loc = loc;
			this.cost = cost;
			this.prev = prev;
		}
	}
}
