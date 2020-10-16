package com.epimetheus.game.core.system.process;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.epimetheus.game.core.component.process.PlanComponent;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;
import com.epimetheus.game.core.system.MonoSystem;
import com.epimetheus.game.screen.stage.MapStage;

public class PlanSystem extends MonoSystem {

	@SuppressWarnings("unused")
	private MapStage mapStage;
	private BlockingQueue<PlanComponent> components = new LinkedBlockingQueue<>();
	
	public PlanSystem(MapStage mapStage) {
		this.mapStage = mapStage;
	}
	
	@Override
	public void accept(EntityList ents) {
		for (Entity ent: ents) 
			accept(ent);
	}

	@Override
	public void accept(Entity ent) {
		PlanComponent pc = (PlanComponent)(ent.getComponent(PlanComponent.class));
		if (pc != null) {
			components.add(pc);
		} else {
			System.out.println("PlanSystem.accept:: no component");
		}
	}

	@Override
	public void process(float delta) {
		
	}
	

	public void finishPlan(PlanComponent pc) {
		System.out.println("Job done for entity " + pc.getEntity().getId());
		components.remove(pc);
	}
}
