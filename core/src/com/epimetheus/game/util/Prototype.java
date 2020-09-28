package com.epimetheus.game.util;

import java.util.LinkedList;
import java.util.List;

import com.epimetheus.game.core.component.process.PlanRequirement;

public class Prototype {
	private String name;
	private int prototypeId;
	private String atlasId;
	private String regionId;
	private List<PlanRequirement> requirements = new LinkedList<>();
	
	private int workRequired;
	
	public Prototype(String name, int prototypeId, String atlasId, String regionId, int workRequired, PlanRequirement[] requirements) {
		super();
		this.name = name;
		this.prototypeId = prototypeId;
		this.atlasId = atlasId;
		this.regionId = regionId;
		this.workRequired = workRequired;
		for (int i = 0; i < requirements.length; i++)
			this.requirements.add(requirements[i]);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrototypeId() {
		return prototypeId;
	}
	public void setPrototypeId(int prototypeId) {
		this.prototypeId = prototypeId;
	}
	public String getAtlasId() {
		return atlasId;
	}
	public void setAtlasId(String atlasId) {
		this.atlasId = atlasId;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public int getWorkRequired() {
		return workRequired;
	}
	public void setWorkRequired(int workRequired) {
		this.workRequired = workRequired;
	}
	public List<PlanRequirement> getRequirements() {
		return requirements;
	}
	public void setRequirements(List<PlanRequirement> requirements) {
		this.requirements = requirements;
	}
}
