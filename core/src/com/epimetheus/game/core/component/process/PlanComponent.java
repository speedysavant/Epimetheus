package com.epimetheus.game.core.component.process;

import java.util.LinkedList;
import java.util.List;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.system.process.PlanSystem;

public class PlanComponent extends AbstractComponent {

	private int workDone;
	private int workRequired;
	private List<PlanRequirement> requirements = new LinkedList<>();
	
	private PlanSystem planner;
	
	private PlanComponent() {
		
	}
	public int getWorkRequired() {
		return workRequired;
	}
	private void setWorkRequired(int workRequired) {
		this.workRequired = workRequired;
	}
	public int getWorkDone() {
		return workDone;
	}
	private void setWorkDone(int workDone) {
		this.workDone = workDone;
	}
	public float getCompletionPercentage() {
		return (float)workDone / (float)workRequired;
	}
	public boolean requiresWork() {
		return workDone < workRequired;
	}
	public void addWork(int workUnits) {
		workDone += workUnits;
		if (!requiresWork())
			planner.finishPlan(this);
	}
	public List<PlanRequirement> getRequirements() {
		return requirements;
	}
	public void setRequirements(List<PlanRequirement> requirements) {
		this.requirements = requirements;
	}
	
	@Override
	public Class<? extends Component> getComponentType() {
		return PlanComponent.class;
	}

	
	public static PlanComponent generate(int workRequired, PlanSystem planner){
		PlanComponent component = new PlanComponent();
		component.setWorkRequired(workRequired);
		component.setWorkDone(0);
		component.planner = planner;
		
		return component;
	}
}
