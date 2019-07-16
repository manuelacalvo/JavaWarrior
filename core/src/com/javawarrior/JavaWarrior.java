package com.javawarrior;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.enumfile.SCREEN_TYPE;
import com.screen.AbstractScreen;
import com.screen.GameScreen;

import java.util.EnumMap;

public class JavaWarrior extends Game {

	private GameScreen gameScreen;
	private static final String TAG = JavaWarrior.class.getSimpleName();
	private EnumMap<SCREEN_TYPE, AbstractScreen> screenCache;
	private AssetManager assetManager;
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;
	private OrthographicCamera gameCamera;
	private MapProperties mapProperties;
	private World world;
	private String PlayerGender;
	private boolean flag;

	private static final float FIXED_TIME = 1 / 60f;
	private float accumulator;

	@Override
	public void create() {
		//To debug with TAG
	    Gdx.app.setLogLevel(Application.LOG_DEBUG);

	    flag = false;
		accumulator = 0;
	    world = new World(new Vector2(0, 0), true);
		assetManager = new AssetManager();
		map = new TmxMapLoader().load("Ressources/Map/Map3.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		mapProperties = new MapProperties();
		gameCamera = new OrthographicCamera();
		screenCache = new EnumMap<SCREEN_TYPE, AbstractScreen>(SCREEN_TYPE.class);

		setScreen(SCREEN_TYPE.CHARACTERSEL);
	}

	public void setScreen(final SCREEN_TYPE screen_type) {
		final Screen screen = screenCache.get(screen_type);
		//creat or change screen depending of needed + error display
		if(screen == null){
			try{
				Gdx.app.debug(TAG, "Creating new screen " + screen_type);  //debug information
				final AbstractScreen newScreen = (AbstractScreen) ClassReflection.getConstructor(screen_type.getScreenClass(), JavaWarrior.class).newInstance(this);
				screenCache.put(screen_type, newScreen);
				setScreen(newScreen);
			} catch (Exception e) {
				throw new GdxRuntimeException("Screen " + screen_type + " could not be loaded");  //debug information
			}
		} else if ((screen != null) && !flag) {
			Gdx.app.debug(TAG, "Switching to screen " + screen_type);  //debug information
			flag = true;
			setScreen(screen_type);
		}
	}

	public GameScreen getGameScreen() { return gameScreen; }
	public AssetManager getAssetManager() { return assetManager; }
	public OrthographicCamera getGameCamera() { return gameCamera; }
	public TiledMap getMap() { return map; }
	public OrthogonalTiledMapRenderer getMapRenderer() { return mapRenderer; }
	public MapProperties getMapProperties() { return mapProperties; }
	public World getWorld() { return world; }
	public String getPlayerGender() { return PlayerGender; }

	public String setPlayerGender(String playerGender) { PlayerGender = playerGender; return playerGender;}

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
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
		map.dispose();
		mapRenderer.dispose();
		world.dispose();
	}
}
