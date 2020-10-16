package com.epimetheus.game.core.component.process;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.system.process.JobSystem.Jobs;

public class JobComponent extends AbstractComponent {

	private Jobs job; 
	private float work;
	private Entity target;
	private Entity worker;
	
	private JobComponent(Jobs job, float work, Entity target) {
		this.setJob(job);
		this.setWork(work);
		this.setTarget(target);
	}
	
	public Jobs getJob() {
		return job;
	}
	public void setJob(Jobs job) {
		this.job = job;
	}
	public float getWork() {
		return work;
	}
	public void setWork(float work) {
		this.work = work;
	}
	public Entity getWorker() {
		return worker;
	}
	public void setWorker(Entity worker) {
		this.worker = worker;
	}
	public Entity getTarget() {
		return target;
	}
	public void setTarget(Entity target) {
		this.target = target;
	}
	
	@Override
	public Class<? extends Component> getComponentType() {
		return JobComponent.class;
	}
	
	public static JobComponent generate(Jobs job, float work, Entity target) {
		return new JobComponent(job, work, target);
	}
}
