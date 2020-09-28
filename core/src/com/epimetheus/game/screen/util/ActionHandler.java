package com.epimetheus.game.screen.util;

import com.badlogic.gdx.math.Vector3;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.entity.Tiles;
import com.epimetheus.game.screen.stage.MapStage;
import com.epimetheus.game.util.Prototypes;

public class ActionHandler {
	
	private MapStage mapStage;
	
	public ActionHandler(MapStage mapStage) {
		this.mapStage = mapStage;
	}
	
	public void clickedOn(float x, float y, Entity ent) {
		Vector3 coords = new Vector3(x, y, 0);
		Location loc = Location.fromWorldCoords(coords);
		Tiles tile = mapStage.tileAt(loc);
		System.out.println("Clicked on Entity " + ent.getId() + " on tile at " + loc + " type " + tile);
	}
	public void clickedOn(Location loc, Tiles tile) {
		System.out.println("Clicked on tile at " + loc + " type " + tile +" with mousemode of " + MouseMode.getCurrent());
		EntityList ents = mapStage.entitiesAt(loc);
		if (MouseMode.isMode(MouseMode.PLANNING))
			if (ents.size() == 0)
				createConstructionAt(loc, MouseMode.getPrototypeId());
			else
				System.out.println("Can't place at location " + loc + ", location contains " + ents.size() + " entities");
	}
	
	public void createConstructionAt(Location location, int prototypeId) {
		mapStage.addEntity(Prototypes.toEntity(prototypeId, location.floor(), this));
	}
}
