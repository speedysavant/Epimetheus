package com.epimetheus.game.screen.util;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.epimetheus.game.screen.stage.MapStage;

public class CameraHarness extends InputAdapter {

	private OrthographicCamera camera;
	
	private int keyMoveSpeed = 20;
	private float zoomSpeed = 0.10f;
	private float currentZoom = 0;
	private Vector2 cameraMoveVec = new Vector2();
	private Vector2 prevDrag = new Vector2();
	private boolean isDragging = false;
	
	public static int MAP_UP = Keys.W;
	public static int MAP_DOWN = Keys.S;
	public static int MAP_LEFT = Keys.A;
	public static int MAP_RIGHT = Keys.D;
	
	public CameraHarness(MapStage stage) {
		this.camera = (OrthographicCamera) stage.getCamera();
		camera.zoom = 2f;
		MouseMode.setCurrent(MouseMode.SELECTING);
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public void update() {
		camera.zoom += currentZoom;
		currentZoom = 0;
		
		if (isDragging) {
			cameraMoveVec.x *= camera.zoom;
			cameraMoveVec.y *= camera.zoom;
			camera.translate(cameraMoveVec);
			cameraMoveVec.x = 0;
			cameraMoveVec.y = 0;
		} else {
			camera.translate(cameraMoveVec);
		}
	}
	
	@Override
	public boolean keyDown (int keycode) {
		if (keycode == MAP_UP)
			cameraMoveVec.y += keyMoveSpeed;
		if (keycode == MAP_DOWN)
			cameraMoveVec.y -= keyMoveSpeed;
		if (keycode == MAP_LEFT)
			cameraMoveVec.x -= keyMoveSpeed;
		if (keycode == MAP_RIGHT)
			cameraMoveVec.x += keyMoveSpeed;
		return false;
	}

	@Override
	public boolean keyUp (int keycode) {
		if (keycode == MAP_UP)
			cameraMoveVec.y -= keyMoveSpeed;
		if (keycode == MAP_DOWN)
			cameraMoveVec.y += keyMoveSpeed;
		if (keycode == MAP_LEFT)
			cameraMoveVec.x += keyMoveSpeed;
		if (keycode == MAP_RIGHT)
			cameraMoveVec.x -= keyMoveSpeed;
		return false;
	}

	@Override
	public boolean keyTyped (char character) {
		return false;
	}

	@Override
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		if (button == 2) {
			prevDrag.x = screenX;
			prevDrag.y = screenY;
			isDragging = true;
		}
		return false;
	}

	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		if (button == 2) {
			isDragging = false;
			cameraMoveVec.x = 0;
			cameraMoveVec.y = 0;
		}
		return false;
	}

	@Override
	public boolean touchDragged (int screenX, int screenY, int pointer) {
		if (isDragging) {
			cameraMoveVec.x = (prevDrag.x - screenX);
			cameraMoveVec.y = -(prevDrag.y - screenY);
			prevDrag.x = screenX;
			prevDrag.y = screenY;
		}
		return false;
	}

	@Override
	public boolean mouseMoved (int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled (int amount) {
		currentZoom = amount * zoomSpeed;
		return false;
	}
}
