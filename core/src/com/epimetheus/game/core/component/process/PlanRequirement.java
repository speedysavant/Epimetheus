package com.epimetheus.game.core.component.process;

public class PlanRequirement {
	public int itemId;
	public String name;
	public int amountHeld = 0;
	public int amountRequired;
	
	public PlanRequirement(int itemId, String name, int amountRequired) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.amountRequired = amountRequired;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmountHeld() {
		return amountHeld;
	}
	public void setAmountHeld(int amountHeld) {
		this.amountHeld = amountHeld;
	}
	public int getAmountRequired() {
		return amountRequired;
	}
	public void setAmountRequired(int amountRequired) {
		this.amountRequired = amountRequired;
	}
	public int needs() { return amountRequired - amountHeld; }
	public boolean isSatisfied() { return amountRequired <= amountHeld; }
}
