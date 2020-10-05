package com.epimetheus.game.screen.stage;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.epimetheus.game.core.component.Component;
import com.epimetheus.game.core.component.process.LocationComponent;
import com.epimetheus.game.core.component.process.PlanComponent;
import com.epimetheus.game.core.component.render.RenderComponent;
import com.epimetheus.game.core.entity.ActorEntity;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;
import com.epimetheus.game.core.entity.Items;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.entity.MapEntity;
import com.epimetheus.game.core.entity.Tiles;
import com.epimetheus.game.core.system.process.PlanSystem;
import com.epimetheus.game.core.system.render.RenderSystem;
import com.epimetheus.game.screen.ui.TileCursor;
import com.epimetheus.game.screen.util.ActionHandler;
import com.epimetheus.game.screen.util.CameraHarness;
import com.epimetheus.game.util.Prototypes;
import com.epimetheus.game.util.gen.PawnGenerator;
import com.epimetheus.game.util.gen.PondGenerator;
import com.epimetheus.game.util.gen.SiteGenerator;

/**
 * 
 * A container for game entities and systems. Also manages user interaction with entities. As a LibGDX Stage,
 * most game entities intersect here.
 * 
 * @author Colin Pinnell 2020
 *
 */
public class MapStage extends Stage implements Screen {
	
	Texture tex;
	
	// Entities
	private MapEntity mapEnt;
	private EntityList ponds;
	// private EntityList rivers = new EntityList();
	private EntityList entities = new EntityList();
	private TileCursor tileCursor;
	
	// Systems
	private ActionHandler actionHandler;
	private CameraHarness cameraHarness;
	
	private PlanSystem planSystem;
	private RenderSystem renderSystem;
	
	/**
	 * Creates a default MapStage with default viewport, camera, and a simple randomized world
	 */
	public MapStage() {
		super(new ExtendViewport(800,600, new OrthographicCamera()));
		planSystem = new PlanSystem(this);
		renderSystem = new RenderSystem(this);
		actionHandler = new ActionHandler(this);
		cameraHarness = new CameraHarness(this);
		
		Prototypes.init(planSystem);
		
		mapEnt = (SiteGenerator.testMap(50, 40, Tiles.getSize(), actionHandler));
		this.addActor(mapEnt);
		
		ponds = PondGenerator.pondsByLowest(3, 64, mapEnt.getTiles(), actionHandler);
		for (Entity water: ponds)
			addEntity(water);
		
		for (Entity pawn: PawnGenerator.generateRandom(20, 50, 40, actionHandler))
			addEntity(pawn);
		
		addEntity(Items.generate("Test Item", new Location(1f,1f,1f), actionHandler));
		
		tileCursor = new TileCursor(this.getCamera());
		this.addActor(tileCursor);
		tileCursor.toFront();
	}
	
	/**
	 * 
	 * @return the CameraHarness object used to control the Camera position
	 */
	public CameraHarness getCameraHarness() {
		return cameraHarness;
	}
	
	/**
	 * Adds an Entity to the MapStage's entity list. This will also add it to any applicable
	 * Systems that the MapStage is handling.
	 * 
	 * If an entity is being placed at a location that already has an entity there, this method will fail.
	 * 
	 * @param ent	The Entity being added.
	 */
	public void addEntity(Entity ent) {
		Location location = ((LocationComponent)(ent.getComponent(LocationComponent.class))).getLocation();
		EntityList ents = entitiesAt(location); 
		
		if (ents.size() != 0) {
			System.out.println("Unable to place entity, colliding with " + ents.size() + " entities");
			return;
		}
		
		entities.add(ent);
		
		if (ent instanceof ActorEntity)
			addActor((ActorEntity)ent);
		
		for (Component c: ent.getComponents()) {
			if (c instanceof PlanComponent) planSystem.accept(ent);
			if (c instanceof RenderComponent) renderSystem.accept(ent);
		}
	}
	
	/**
	 * @param loc	The tile location, rounded down if not already
	 * @return	The Tile at the given location. Returns null if no tile exists there.
	 */
	public Tiles tileAt(Location loc) {
		return mapEnt.getTileAt(loc);
	}
	/**
	 * @param loc	The tile location
	 * @return	An EntityList of all Entities at the Location
	 */
	public EntityList entitiesAt(Location loc){
		return entities.allEntitiesAt(loc);
	}
	
	/**
	 * Draws all Entities to the Stage's Batch.
	 * 
	 * First any Actors not in the RenderSystem are drawn. Then, the RenderSystem is called, which draws any
	 * Entity with an ActorComponent or RenderComponent. 
	 */
	@Override
	public void draw() {
		cameraHarness.update();
		getViewport().getCamera().update();
		getBatch().setProjectionMatrix(getViewport().getCamera().combined);
		super.draw();
		
		getBatch().begin();
		renderSystem.process();
		getBatch().end();
	}
	
	@Override
	public void resize(int width, int height) {
		getViewport().update(width, height);
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		act(delta);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}
	
}
