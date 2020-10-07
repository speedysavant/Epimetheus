package com.epimetheus.game.core.component.process;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;

public class FluidComponent extends AbstractComponent {

	private String fluidType;
	private float volume;
	
	private FluidComponent(String fluidType, float volume) {
		this.fluidType = fluidType;
		this.volume = volume;
	}
	
	public String getFluidType() {
		return fluidType;
	}
	public void setFluidType(String fluidType) {
		this.fluidType = fluidType;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}

	@Override
	public Class<? extends Component> getComponentType() {
		return FluidComponent.class;
	}

	public static FluidComponent generate(String fluidType, float volume) {
		return new FluidComponent(fluidType, volume);
	}
}
