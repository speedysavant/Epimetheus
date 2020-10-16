package com.epimetheus.game.core.component.process;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;

public class TerrainComponent extends AbstractComponent {

	private float pathCost = 1.0f;
	
	private TerrainComponent(float pathCost) {
		this.pathCost = pathCost;
	}

	public float getPathCost() {
		return pathCost;
	}
	public void setPathCost(float pathCost) {
		this.pathCost = pathCost;
	}

	@Override
	public Class<? extends Component> getComponentType() {
		return TerrainComponent.class;
	}

	public static TerrainComponent generate(float pathCost) {
		return new TerrainComponent(pathCost);
	}
}
