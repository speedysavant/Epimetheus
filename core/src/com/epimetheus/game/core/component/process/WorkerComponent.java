package com.epimetheus.game.core.component.process;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.entity.Entity;

public class WorkerComponent extends AbstractComponent {

	private Entity currentJob;
	private int workRate;
	
	private WorkerComponent(int workRate) {
		setWorkRate(workRate);
	}
	
	public Entity getCurrentJob() {
		return currentJob;
	}
	public void setCurrentJob(Entity currentJob) {
		this.currentJob = currentJob;
	}
	public int getWorkRate() {
		return workRate;
	}
	public void setWorkRate(int workRate) {
		this.workRate = workRate;
	}

	
	@Override
	public Class<? extends Component> getComponentType() {
		return WorkerComponent.class;
	}

	public static WorkerComponent generate(int workRate) {
		return new WorkerComponent(workRate);
	}
}
