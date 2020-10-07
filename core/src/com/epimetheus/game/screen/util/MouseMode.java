package com.epimetheus.game.screen.util;

public enum MouseMode {
	SELECTING, PLANNING, CUT_PLANT, MINE;
	
	private static MouseMode current;
	private static int prototypeId;
	private MouseMode() {
	}
	public static MouseMode getCurrent() {
		return current;
	}
	public static void setCurrent(MouseMode current) {
		MouseMode.current = current;
	}
	public static boolean isMode(MouseMode mode) {
		return MouseMode.current == mode;
	}
	public static int getPrototypeId() {
		return prototypeId;
	}
	public static void setPrototypeId(int prototypeId) {
		MouseMode.prototypeId = prototypeId;
	}
}
