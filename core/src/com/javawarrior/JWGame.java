package com.javawarrior;

import com.adventuregames.fight.FIGHT_PART;
import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.Display.SCREEN_TYPE;
import com.openworld.screen.LoadingScreen;
import com.shopmanagement.Collection;
import com.tools.JWAssetManager;
import com.tools.SkinGenerator;

import java.util.EnumMap;

public class JWGame extends Game {

	private Class screenType=null;

	private boolean debug = false;

	private JWAssetManager assetManager;
	private Collection collection;
	private Skin skin;

	//private static final String TAG = JWGame.class.getSimpleName();
	private EnumMap<SCREEN_TYPE, Screen> screenCache;

	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;
	private OrthographicCamera gameCamera;
	private MapProperties mapProperties;
	private World world;
	private String PlayerGender;

	private static final float FIXED_TIME = 1 / 60f;
	private float accumulator;

	/**
	 * Standard constructor called from DesktopLauncher
	 */
	public JWGame(){
		/* DO NOT use getInstance() here - results in error */
		this.assetManager = null;
	}

	/**
	 * Test&Debug constructor
	 * @param screenType type of the screen to debug
	 */
	public JWGame(Class screenType){
		this.debug=true;
		this.screenType=screenType;
	}

	@Override
	public void create() {
		/* INIT ASSETS */
		this.assetManager = JWAssetManager.getInstance();

		skin = SkinGenerator.generateSkin(assetManager);

		accumulator = 0;
	    world = new World(new Vector2(0, 0), true);

		map = new TmxMapLoader().load("Ressources/Map/Map3.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		mapProperties = new MapProperties();

		gameCamera = new OrthographicCamera();
		screenCache = new EnumMap<>(SCREEN_TYPE.class);

		if (!debug) { //Standard case
			//setScreen(SCREEN_TYPE.LOADING);
			setScreen(SCREEN_TYPE.LOADING);
		} else {
			Gdx.app.setLogLevel(Application.LOG_DEBUG); //debug information
			// DEBUG
			if (screenType == FightScreen.class) {
				this.setScreen(new FightScreen(this, FIGHT_PART.USUAL, false));
			}
		}
	}

	/**
	 * Sets a screen
	 * @param screen_type SCREEN_TYPE to set
	 */
	public void setScreen(final SCREEN_TYPE screen_type) {
		final Screen screen = screenCache.get(screen_type);
		//creat or change screen depending of needed + error display
		if(screen == null){
			try{
				//Gdx.app.debug(TAG, "Creating new screen " + screen_type);  //debug information
				final Screen newScreen = (Screen) ClassReflection.getConstructor(screen_type.getScreenClass(), JWGame.class).newInstance(this);
				screenCache.put(screen_type, newScreen);
				setScreen(newScreen);
			} catch (Exception e) {
				throw new GdxRuntimeException("Screen " + screen_type + " could not be loaded");  //debug information
			}
		} else {
			//Gdx.app.debug(TAG, "Switching to screen " + screen_type);  //debug information
			setScreen(screen_type);
		}
	}

	// Fix the frames so they always wait for the game engine to be done before rendering the next frame (in order to detect correctly collision)
	@Override
	public void render() {
		super.render();
		//Display value betwwen to calculated frames
		//Gdx.app.debug(TAG, "Time between frames is actually : " + Gdx.graphics.getRawDeltaTime());
		//correction of Frame stack (macking a pause in order to finish game engine and get a fixe time between every frame)
		accumulator += Math.min(0.25f,Gdx.graphics.getRawDeltaTime());
		while (accumulator >= FIXED_TIME){
			world.step(FIXED_TIME, 6, 2);
			accumulator -= FIXED_TIME;
		}
		// Pourcentage % left before next step gonna help us do interpollation (having an quarter frame mouvement so it's smoother on screen)
		/* final float alpha = accumulator / FIXED_TIME; */

		// TODO : check render instructions order
		if(getScreen() instanceof com.Display.AbstractScreen){
			((com.Display.AbstractScreen)getScreen()).update(Gdx.graphics.getDeltaTime());
		}
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getScreen().render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
		map.dispose();
		mapRenderer.dispose();
		world.dispose();
	}

	public AssetManager getAssetManager() { return assetManager; }

	public boolean isDebug() { return debug; }

	public Skin getSkin(){ return this.skin; }

	public Collection getCollection() { return collection; }

	public void setCollection(Collection collection) { this.collection = collection; }

	public OrthographicCamera getGameCamera() { return gameCamera; }
	public TiledMap getMap() { return map; }
	public OrthogonalTiledMapRenderer getMapRenderer() { return mapRenderer; }
	public MapProperties getMapProperties() { return mapProperties; }
	public World getWorld() { return world; }
	public String getPlayerGender() { return PlayerGender; }

	public String setPlayerGender(String playerGender) { PlayerGender = playerGender; return playerGender;}
}
