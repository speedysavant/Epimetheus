package com.epimetheus.game.core.component.process;

import java.util.LinkedList;

import com.epimetheus.game.core.component.AbstractComponent;
import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.system.process.PathingSystem.MoveType;

public class MoveComponent extends AbstractComponent {

	private MoveType moveType = MoveType.WALK;
	private float movePerSecond = 1f;
	private Location target;
	private LinkedList<Location> path = new LinkedList<>();
	
	private MoveComponent(MoveType moveType, float movePerSecond) {
		super();
		this.moveType = moveType;
		this.movePerSecond = movePerSecond;
	}

	public MoveType getMoveType() {
		return moveType;
	}
	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}
	public float getMovePerSecond() {
		return movePerSecond;
	}
	public void setMovePerSecond(float movePerSecond) {
		this.movePerSecond = movePerSecond;
	}
	public Location getTarget() {
		return target;
	}
	public void setTarget(Location target) {
		this.target = target;
	}
	public LinkedList<Location> getPath() {
		return path;
	}
	public void setPath(LinkedList<Location> path) {
		this.path = path;
	}

	@Override
	public Class<? extends Component> getComponentType() {
		return MoveComponent.class;
	}

	public static MoveComponent generate(MoveType moveType, float movePerSecond) {
		return new MoveComponent(moveType, movePerSecond);
	}
}
