package com.epimetheus.game.core.component.process;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;

public class VelocityComponent extends AbstractComponent {

	private float x = 0;
	private float y = 0;
	
	private VelocityComponent() {
		
	}
	@Override
	public Class<? extends Component> getComponentType() {
		return VelocityComponent.class;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public static VelocityComponent generate(float x, float y) {
		VelocityComponent component = new VelocityComponent();
		component.x = x;
		component.y = y;
		return component;
	}
}
