package com.epimetheus.game.core.component.process;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;

public class TemperatureComponent extends AbstractComponent {

	float temp = 0;
	
	private TemperatureComponent() {
		
	}
	
	@Override
	public Class<? extends Component> getComponentType() {
		return TemperatureComponent.class;
	}

	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		this.temp = temp;
	}
	
	public static TemperatureComponent generate(float new_temp) {
		TemperatureComponent component = new TemperatureComponent();
		component.temp = new_temp;
		return component;
	}
}
