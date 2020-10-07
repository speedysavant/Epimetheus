package com.epimetheus.game.core.component.process;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;

public class PlantComponent extends AbstractComponent {

	// plant stuff here
	
	private PlantComponent() {
		
	}
	
	@Override
	public Class<? extends Component> getComponentType() {
		return PlantComponent.class;
	}

	public static PlantComponent generate() {
		return new PlantComponent();
	}
}
