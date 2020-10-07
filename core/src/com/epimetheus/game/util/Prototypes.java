package com.epimetheus.game.util;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;
import com.epimetheus.game.core.component.process.LocationComponent;
import com.epimetheus.game.core.component.process.PlanComponent;
import com.epimetheus.game.core.component.process.PlanRequirement;
import com.epimetheus.game.core.component.render.ActorComponent;
import com.epimetheus.game.core.component.render.RenderComponent;
import com.epimetheus.game.core.component.render.RotationComponent;
import com.epimetheus.game.core.component.render.ScaleComponent;
import com.epimetheus.game.core.entity.AbstractEntity;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.system.process.PlanSystem;
import com.epimetheus.game.screen.util.ActionHandler;

public class Prototypes {
	private static Map<Integer, Prototype> prototypes = new HashMap<>();
	private static PlanSystem planner;
	
	public static void init(PlanSystem planner) {
		Prototypes.planner = planner;
		prototypes.put(1, new Prototype(
				"wall", 
				1, 
				"ui/white_reticles", "crosshair145", 
				10, 
				new PlanRequirement[] {
						new PlanRequirement(1, "dust", 300)
		}));
		prototypes.put(2, new Prototype(
				"door", 
				2, 
				"ui/white_reticles", "crosshair148", 
				20, 
				new PlanRequirement[] {
						new PlanRequirement(1, "dust", 100)
		}));
		prototypes.put(3, new Prototype(
				"cut_plant", 
				2, 
				"ui/white_reticles", "crosshair147", 
				20, 
				null));
	}
	
	public static Entity toEntity(int prototypeId, Location location, ActionHandler handler) {
		Entity ent = AbstractEntity.generate();
		LocationComponent lc = LocationComponent.generate(location, new Vector2(1,1)); 
		Prototype prototype = prototypes.get(prototypeId);
		if (prototype == null)
			return null;
		
		ent.setName(prototype.getName() + " " + ent.getId());
		ent.addComponent(lc);
		ent.addComponent(ScaleComponent.generate(1.0f));
		ent.addComponent(RotationComponent.generate(0.0f));
		ent.addComponent(PlanComponent.generate(10, planner));
		ent.addComponent(ActorComponent.generate(lc, handler));
		ent.addComponent(RenderComponent.generate(prototype.getAtlasId(), prototype.getRegionId()));
		
		return ent;
	}
}
