package com.epimetheus.game.core.component.render;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;

public class RotationComponent extends AbstractComponent {

	private float rotation;
	
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	public float getRotation() {
		return rotation;
	}
	
	private RotationComponent() {
		
	}
	
	@Override
	public Class<? extends Component> getComponentType() {
		return RotationComponent.class;
	}
	
	public static RotationComponent generate(float rotation) {
		RotationComponent comp = new RotationComponent();
		comp.rotation = rotation;
		return comp;
	}

}
