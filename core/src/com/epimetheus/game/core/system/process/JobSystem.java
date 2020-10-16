package com.epimetheus.game.core.system.process;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.component.process.JobComponent;
import com.epimetheus.game.core.component.process.LocationComponent;
import com.epimetheus.game.core.component.process.MoveComponent;
import com.epimetheus.game.core.component.process.WorkerComponent;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;
import com.epimetheus.game.core.system.MultiSystem;

public class JobSystem extends MultiSystem {
	
	public enum Jobs {CUT_PLANT, MINE}
	private List<Class<? extends Component>> componentClasses = new LinkedList<>();
	
	// This should really just be the WorkerComponent and LocationComponent.
	private EntityList jobSeekers = new EntityList();
	
	public JobSystem() {
		componentClasses.add(JobComponent.class);
		componentClasses.add(LocationComponent.class);
	}
	
	@Override
	public void accept(EntityList ents) {
		for (Entity ent: ents)
			accept(ent);
	}

	@Override
	public void accept(Entity ent) {
		if (ent.hasComponent(JobComponent.class)) {
			Map<Class<? extends Component>, Component> entComponents = new HashMap<>();
			for (Component c: ent.getComponents())
				entComponents.put(c.getClass(), c);
			componentList.add(entComponents);
		}
		else if (ent.hasComponent(WorkerComponent.class)) 
			jobSeekers.add(ent);
	}
	
	@Override
	public void process(float delta) {
		if (jobSeekers.isEmpty())
			return;
		
		// Sort workers by need-to-work
		for (Entity ent: jobSeekers) {
			WorkerComponent worker = ((WorkerComponent)ent.getComponent(WorkerComponent.class));
			if (worker.getCurrentJob() == null) {
				MoveComponent mover = ((MoveComponent)ent.getComponent(MoveComponent.class));
				seekJob(ent, worker, mover);
			}
			else
				processJob(ent, worker, delta);
		}
	}
	
	
	
	private void seekJob(Entity ent, WorkerComponent worker, MoveComponent mover) {
		if (componentList.size() == 0)
			return;
		
		// get first unclaimed job
		// sort by need-to-complete
		Map<Class<? extends Component>, Component> map = componentList.remove(0);
		JobComponent job = ((JobComponent) (map.get(JobComponent.class)));
		
		worker.setCurrentJob(job.getEntity());
		job.setWorker(ent);
		mover.setTarget(((LocationComponent)(job.getEntity().getComponent(LocationComponent.class))).getLocation());
	}
	
	private void processJob(Entity ent, WorkerComponent worker, float delta) {
		LocationComponent lc = ((LocationComponent)(ent.getComponent(LocationComponent.class)));
		JobComponent job = ((JobComponent)(worker.getCurrentJob().getComponent(JobComponent.class)));
		LocationComponent jlc = ((LocationComponent)(job.getEntity().getComponent(LocationComponent.class)));
		float distance = lc.getLocation().distanceTo(jlc.getLocation());
		
		if (distance > 1.0f) {
			// System.out.println(ent.getName() + ", Job out of range, distance of " + distance);
		} else {
			float remainingWork = (job.getWork() - (worker.getWorkRate() * delta));
			job.setWork(remainingWork);
			System.out.println(ent.getName() + " working at "+ lc.getLocation() +", " + remainingWork + " work remaining");
			
			if (remainingWork <= 0) 
				finishJob(job, worker);
		}
		
	}
	
	private void finishJob(JobComponent job, WorkerComponent worker) {
		job.setWorker(null);
		controller.removeEntity(job.getEntity());
		
		worker.setCurrentJob(null);
		System.out.println(worker.getEntity().getName() + ": Job's done!");
	}
}
