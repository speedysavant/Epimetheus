package com.epimetheus.game.core.component.process;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;

public class PhysicalComponent extends AbstractComponent {

	private float mass;
	private String material;
	
	private PhysicalComponent(String material, float mass) {
		this.mass = mass;
		this.material = material;
	}
	
	public float getMass() {
		return mass;
	}
	public void setMass(float mass) {
		this.mass = mass;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public Class<? extends Component> getComponentType() {
		return PhysicalComponent.class;
	}

	public static PhysicalComponent generate(String material, float mass) {
		return new PhysicalComponent(material, mass);
	}
	
}
