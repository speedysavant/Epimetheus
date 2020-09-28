package com.epimetheus.game.core.component.render;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;

public class ScaleComponent extends AbstractComponent {

	private float scale;
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	public float getScale() {
		return scale;
	}
	
	private ScaleComponent() {
		
	}
	
	@Override
	public Class<? extends Component> getComponentType() {
		return ScaleComponent.class;
	}
	
	public static ScaleComponent generate(float scale) {
		ScaleComponent comp = new ScaleComponent();
		comp.scale = scale;
		return comp;
	}

}
