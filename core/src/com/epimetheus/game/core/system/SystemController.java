package com.epimetheus.game.core.system;

import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.component.process.JobComponent;
import com.epimetheus.game.core.component.process.LocationComponent;
import com.epimetheus.game.core.component.process.PlanComponent;
import com.epimetheus.game.core.component.process.WorkerComponent;
import com.epimetheus.game.core.component.render.RenderComponent;
import com.epimetheus.game.core.entity.ActorEntity;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.system.process.JobSystem;
import com.epimetheus.game.core.system.process.PlanSystem;
import com.epimetheus.game.core.system.render.RenderSystem;
import com.epimetheus.game.screen.stage.MapStage;

public class SystemController {

	private EntityList entities = new EntityList();
	private MapStage mapStage;
	
	private PlanSystem planSystem;
	private RenderSystem renderSystem;
	private JobSystem jobSystem;
	
	public SystemController(MapStage mapStage) {
		this.mapStage = mapStage;
	}
	public MapStage getMapStage() {
		return mapStage;
	}
	public PlanSystem getPlanSystem() {
		return planSystem;
	}
	public void setPlanSystem(PlanSystem planSystem) {
		this.planSystem = planSystem;
		planSystem.setSystemController(this);
	}
	public RenderSystem getRenderSystem() {
		return renderSystem;
	}
	public void setRenderSystem(RenderSystem renderSystem) {
		this.renderSystem = renderSystem;
		renderSystem.setSystemController(this);
	}
	public JobSystem getJobSystem() {
		return jobSystem;
	}
	public void setJobSystem(JobSystem jobSystem) {
		this.jobSystem = jobSystem;
		jobSystem.setSystemController(this);
	}
	public EntityList getEntities() {
		return entities;
	}
	public void setEntities(EntityList entities) {
		this.entities = entities;
	}
	public void setMapStage(MapStage mapStage) {
		this.mapStage = mapStage;
	}

	public void addEntity(Entity ent) {
		Location location = ((LocationComponent)(ent.getComponent(LocationComponent.class))).getLocation();
		EntityList ents = entities.allEntitiesAt(location); 
		
		if (ents.size() != 0) {
			return;
		}
		
		entities.add(ent);
		if (ent instanceof ActorEntity)
			mapStage.addActor((ActorEntity)ent);
		
		for (Component c: ent.getComponents()) {
			if (c instanceof PlanComponent) planSystem.accept(ent);
			if (c instanceof RenderComponent) renderSystem.accept(ent);
			if (c instanceof JobComponent) jobSystem.accept(ent);
			if (c instanceof WorkerComponent) jobSystem.accept(ent);
		}
	}
	
	public void removeEntity(Entity ent) {
		planSystem.remove(ent);
		renderSystem.remove(ent);
		jobSystem.remove(ent);
	}
}
