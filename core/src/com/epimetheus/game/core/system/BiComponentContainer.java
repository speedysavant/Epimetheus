package com.epimetheus.game.core.system;

import com.epimetheus.game.core.component.Component;

public class BiComponentContainer {
	private Component c1;
	private Component c2;
	private Class<? extends Component> cType1;
	private Class<? extends Component> cType2;
	
	public BiComponentContainer(Component c1, Component c2) {
		super();
		this.c1 = c1;
		this.c2 = c2;
		cType1 = c1.getClass();
		cType2 = c2.getClass();
	}
	
	public Component getComponent(Class<? extends Component> type) {
		if (type == cType1)
			return c1;
		else if (type == cType2)
			return c2;
		else return null;
	}
	
	public Component getC1() {
		return c1;
	}
	public void setC1(Component c1) {
		this.c1 = c1;
	}
	public Component getC2() {
		return c2;
	}
	public void setC2(Component c2) {
		this.c2 = c2;
	}
}