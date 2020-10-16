package com.epimetheus.game.core.component.process;

import java.util.LinkedList;
import java.util.List;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.entity.Entity;

public class InventoryComponent extends AbstractComponent {

	private List<Entity> items = new LinkedList<>();
	private float currentWeight;
	private float maxVolume = 0f;
	
	private static float TEST_MASS_MULT = 1.0f;
	
	private InventoryComponent(float maxVolume) {
		this.currentWeight = 0;
		this.maxVolume = maxVolume;
	}
	public float getCurrentWeight() {
		return currentWeight;
	}
	public float getMaxVolume() {
		return maxVolume;
	}
	public void setMaxVolume(float maxVolume) {
		this.maxVolume = maxVolume;
	}
	public void addItem(Entity ent) {
		ItemComponent ic = ((ItemComponent)(ent.getComponent(ItemComponent.class)));
		if (ic == null)
			return;
		
		PhysicalComponent pc = ((PhysicalComponent)(ent.getComponent(PhysicalComponent.class)));
		float weight = pc.getMass() * TEST_MASS_MULT;
		currentWeight += weight;
		items.add(ent);
	}
	public void removeItem(Entity ent) {
		ItemComponent ic = ((ItemComponent)(ent.getComponent(ItemComponent.class)));
		if (ic == null)
			return;
		
		PhysicalComponent pc = ((PhysicalComponent)(ent.getComponent(PhysicalComponent.class)));
		float weight = pc.getMass() * TEST_MASS_MULT;
		currentWeight -= weight;
		items.remove(ent);
	}
	public List<Entity> getItems(){
		return items;
	}
	
	@Override
	public Class<? extends Component> getComponentType() {
		return InventoryComponent.class;
	}

	public static InventoryComponent generate(float maxVolume) {
		return new InventoryComponent(maxVolume);
	}
}
