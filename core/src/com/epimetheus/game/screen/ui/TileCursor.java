package com.epimetheus.game.screen.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.entity.Tiles;

public class TileCursor extends Actor {
	
	TextureRegion region;
	Camera camera;
	
	public TileCursor(Camera camera) {
		this.camera = camera;
		TextureAtlas atlas = new TextureAtlas("img/ui/white_reticles.txt");
		region = atlas.findRegion("crosshair020");
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(region, getX() * Tiles.getSize(), getY() * Tiles.getSize());
	}
	
	@Override
	public void act (float delta) {
		Vector2 vec = Location.fromScreenCoords(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0), camera).floor().toVec2();
		this.setPosition(vec.x, vec.y);
	}
	
}
