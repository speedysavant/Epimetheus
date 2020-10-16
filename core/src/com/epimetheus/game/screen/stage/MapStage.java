package com.epimetheus.game.screen.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.epimetheus.game.core.entity.ActorEntity;
import com.epimetheus.game.core.entity.Entity;
import com.epimetheus.game.core.entity.EntityList;
import com.epimetheus.game.core.entity.Flora;
import com.epimetheus.game.core.entity.Items;
import com.epimetheus.game.core.entity.Location;
import com.epimetheus.game.core.entity.MapEntity;
import com.epimetheus.game.core.entity.Tiles;
import com.epimetheus.game.core.system.SystemController;
import com.epimetheus.game.core.system.process.JobSystem;
import com.epimetheus.game.core.system.process.PathingSystem;
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
//	private EntityList entities = new EntityList();
	private TileCursor tileCursor;
	
	// Systems
	private SystemController systemController;
	private ActionHandler actionHandler;
	private CameraHarness cameraHarness;
	
	/**
	 * Creates a default MapStage with default viewport, camera, and a simple randomized world
	 */
	public MapStage() {
		super(new ExtendViewport(800,600, new OrthographicCamera()));
		systemController = new SystemController(this);
		systemController.setJobSystem(new JobSystem());
		systemController.setPlanSystem(new PlanSystem(this));
		systemController.setRenderSystem(new RenderSystem(this));
		systemController.setPathingSystem(new PathingSystem(this));
		actionHandler = new ActionHandler(this);
		cameraHarness = new CameraHarness(this);
		
		Prototypes.init(systemController.getPlanSystem());
		
		mapEnt = (SiteGenerator.testMap(50, 40, Tiles.getSize(), actionHandler));
		this.addActor(mapEnt);
		
		ponds = PondGenerator.pondsByLowest(3, 96, mapEnt.getTiles(), actionHandler);
		for (Entity water: ponds)
			systemController.addEntity(water);
		
		for (int i = 0; i < 50; i++)
			systemController.addEntity(Flora.generate(
					"Test Plant", 
					new Location(MathUtils.random(49), MathUtils.random(39),0), 
					actionHandler));
		
		for (Entity pawn: PawnGenerator.generateRandom(1, 50, 40, actionHandler)) {
			systemController.addEntity(pawn);
			((ActorEntity)(pawn)).toFront();
			
		}
		
		systemController.addEntity(Items.generate("Test Item", new Location(1f,1f,1f), actionHandler));
		
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
		systemController.addEntity(ent);
		mapEnt.addMoveCost(ent);
	}
	
	public boolean isValidLocation(Location loc) {
		return tileAt(loc) != null;
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
		return systemController.getEntities().allEntitiesAt(loc);
	}
	
	public float pathCostAt(Location loc) {
		// This should add any entities at the location that might cause issues!
		return mapEnt.getMoveCostAt(loc);
	}
	
	/**
	 * Draws all Entities to the Stage's Batch.
	 * 
	 * First any Actors not in the RenderSystem are drawn. Then, the RenderSystem is called, which draws any
	 * Entity with an ActorComponent or RenderComponent. 
	 */
	@Override
	public void draw() {
		float delta = Gdx.graphics.getDeltaTime();
		systemController.getJobSystem().process(delta);
		systemController.getPathingSystem().process(delta);
		
		cameraHarness.update();
		getViewport().getCamera().update();
		getBatch().setProjectionMatrix(getViewport().getCamera().combined);
		super.draw();
		
		getBatch().begin();
		systemController.getRenderSystem().process(delta);
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
