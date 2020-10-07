package com.epimetheus.game.screen.util;

import com.badlogic.gdx.math.Vector3;
import com.epimetheus.game.core.component.process.JobComponent;
import com.epimetheus.game.core.component.process.PlantComponent;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.entity.Tiles;
import com.epimetheus.game.core.system.process.JobSystem.Jobs;
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
		System.out.println("ActionHandler:: Clicked on Entity " + ent.getId() + " on tile at " + loc + " type " + tile);
		
		if (MouseMode.isMode(MouseMode.CUT_PLANT)) 
			cutPlantAt(loc, ent);
	}
	public void clickedOn(Location loc, Tiles tile) {
		System.out.println("ActionHandler:: Clicked on tile at " + loc + " type " + tile +" with mousemode of " + MouseMode.getCurrent());
		EntityList ents = mapStage.entitiesAt(loc);
		
		
		if (MouseMode.isMode(MouseMode.PLANNING))
			if (ents.size() == 0)
				createConstructionAt(loc, MouseMode.getPrototypeId());
			else
				System.out.println("ActionHandler:: Can't place at location " + loc + ", location contains " + ents.size() + " entities");
		else if (MouseMode.isMode(MouseMode.CUT_PLANT)) 
			if (ents.size() == 0)
				System.out.println("ActionHandler:: Can't cut plants, no plant at " + loc);
			else
				cutPlantsAt(loc, ents);
	}
	
	private void createConstructionAt(Location location, int prototypeId) {
		mapStage.addEntity(Prototypes.toEntity(prototypeId, location.floor(), this));
	}
	private void cutPlantsAt(Location location, EntityList ents) {
		for (Entity ent: ents) {
			cutPlantAt(location, ent);
		}
	}
	private void cutPlantAt(Location location, Entity ent) {
		if (ent.hasComponent(PlantComponent.class)) {
			Entity job = Prototypes.toEntity(3, location, this);
			job.setName("Cut Plant");
			job.addComponent(JobComponent.generate(Jobs.CUT_PLANT, 100, ent));
			mapStage.addEntity(job);
		}
	}
}
