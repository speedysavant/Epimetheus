package com.epimetheus.game.core.system.render;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.component.process.LocationComponent;
import com.epimetheus.game.core.component.render.RenderComponent;
import com.epimetheus.game.core.component.render.RotationComponent;
import com.epimetheus.game.core.component.render.ScaleComponent;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.system.MultiSystem;
import com.epimetheus.game.core.system.SystemController;
import com.epimetheus.game.screen.stage.MapStage;

public class RenderSystem extends MultiSystem {

	protected SystemController controller;
	private Batch batch;
	
	private int tileSize = 128;
	private int sourceTileSize = 128;
	
	public RenderSystem(MapStage mapStage) {
		this.batch = mapStage.getBatch();
	}
	
	public void setSystemController(SystemController controller) {
		this.controller = controller;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void accept(EntityList ents) {
		super.accept(ents, LocationComponent.class, RenderComponent.class, ScaleComponent.class, RotationComponent.class);
	}
	
	@Override
	public void accept(Entity ent) {
		Map<Class<? extends Component>, Component> entMap = new HashMap<>();
		
		entMap.put(
				LocationComponent.class, 
				(LocationComponent)ent.getComponent(LocationComponent.class));
		entMap.put(
				RenderComponent.class, 
				(RenderComponent)ent.getComponent(RenderComponent.class));
		entMap.put(
				ScaleComponent.class, 
				(ScaleComponent)ent.getComponent(ScaleComponent.class));
		entMap.put(
				RotationComponent.class, 
				(RotationComponent)ent.getComponent(RotationComponent.class));
		
		this.componentList.add(entMap);
	}
	
	@Override
	public void process() {
		if (!componentList.isEmpty()) {
			for (Map<Class<? extends Component>, Component> entry: componentList)
				processRenderable(entry, batch);
		}
	}
	
	private void processRenderable(Map<Class<? extends Component>, Component> entry, Batch batch) {
		Location location = ( (LocationComponent)(entry.get(LocationComponent.class)) ).getLocation();
		float scale = ( (ScaleComponent)(entry.get(ScaleComponent.class)) ).getScale();
		float rotation = ( (RotationComponent)(entry.get(RotationComponent.class)) ).getRotation();
		RenderComponent renderComponent = (RenderComponent)(entry.get(RenderComponent.class));
		
		if (renderComponent.isTextureRegion()) {
			TextureRegion region = ( (RenderComponent)(entry.get(RenderComponent.class)) ).getTextureRegion();
			batch.draw(
					region, 
					location.getX() * tileSize * scale, // x-position 
					location.getY() * tileSize * scale, // y-position
					0, // x-origin
					0, // y-origin
					tileSize, // width 
					tileSize, // height
					scale, // x-scale
					scale, // y-scale
					rotation // rotation
					);
		} else {
			Texture texture = ( (RenderComponent)(entry.get(RenderComponent.class)) ).getTexture();
			batch.draw(
					texture, 
					location.getX() * tileSize * scale, // x-position 
					location.getY() * tileSize * scale, // y-position
					0, // x-origin
					0, // y-origin
					tileSize, // width 
					tileSize, // height
					scale, // x-scale
					scale, // y-scale
					rotation, // rotation
					0, // src x
					0, // src y
					sourceTileSize, // src width
					sourceTileSize, // src height
					false, // x flip
					false // y flip
					);
		}
		
		
	}
}
