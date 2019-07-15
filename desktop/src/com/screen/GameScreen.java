package com.screen;

import com.PackAnimations.EffectsInit;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.brashmonkey.spriter.Player;
import com.enumfile.SCREEN_TYPE;
import com.idea.Settings;
import com.javawarrior.JavaWarrior;
import com.actor.Actor;
import com.actor.PlayerController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.Random;

public class GameScreen extends AbstractScreen {

  private OrthographicCamera gameCamera;
  private PlayerController controller;
  private Actor Character;
  private AssetManager assetManager;
  private TiledMap tiledMap;
  private OrthogonalTiledMapRenderer mapRenderer;
  private SpriteBatch batch;
  private String PlayerGender;
  private String GenderAtlas;

  private int oxFight;
  private int oyFight;
  private int wxFight;
  private int hyFight;

  public GameScreen(final JavaWarrior context) {

    super(context);
    batch = new SpriteBatch();
    this.assetManager = context.getAssetManager();
    this.mapRenderer = context.getMapRenderer();
    this.tiledMap = context.getMap();
    this.gameCamera = context.getGameCamera();
    this.PlayerGender = context.getPlayerGender();

    if ( PlayerGender.equals("M")){ GenderAtlas = "M"; }
    else if (PlayerGender.equals("F")){ GenderAtlas = "F"; }
    TextureAtlas world = assetManager.get("RessourcesTileset/Hero" + GenderAtlas + ".atlas", TextureAtlas.class);
    EffectsInit Effect = new EffectsInit(
            new Animation(0.3f/3f, world.findRegions("run_north"), Animation.PlayMode.LOOP_REVERSED),
            new Animation(0.3f/3f, world.findRegions("run_south"), Animation.PlayMode.LOOP_REVERSED),
            new Animation(0.3f/3f, world.findRegions("run_west"), Animation.PlayMode.LOOP_REVERSED),
            new Animation(0.3f/3f, world.findRegions("run_east"), Animation.PlayMode.LOOP_REVERSED),
            new Animation(0.3f/3f, world.findRegions("walk_north"), Animation.PlayMode.LOOP_REVERSED),
            new Animation(0.3f/3f, world.findRegions("walk_south"), Animation.PlayMode.LOOP_REVERSED),
            new Animation(0.3f/3f, world.findRegions("walk_west"), Animation.PlayMode.LOOP_REVERSED),
            new Animation(0.3f/3f, world.findRegions("walk_east"), Animation.PlayMode.LOOP_REVERSED),
            world.findRegion("stand_north"),
            world.findRegion("stand_south"),
            world.findRegion("stand_west"),
            world.findRegion("stand_east")
      );
    //Origin Position en fonction d'un Spawn
    MapObject spawn = tiledMap.getLayers().get("Spawn").getObjects().get("Spawn");
    Rectangle rectangle = ((RectangleMapObject) spawn).getRectangle();
    int a = (int) rectangle.getX();
    int b = (int) rectangle.getY();
    Character = new Actor(tiledMap,(int) (a/Settings.SCALED_TILE_SIZE), (int) (b/Settings.SCALED_TILE_SIZE), Effect);
    controller = new PlayerController(Character);
    gameCamera = new OrthographicCamera();

    MapObject Fight = tiledMap.getLayers().get("Fight").getObjects().get("FightZone");
    Rectangle FightRect = ((RectangleMapObject) Fight).getRectangle();
    oxFight = (int) FightRect.getX();
    oyFight = (int) FightRect.getY();
    wxFight = (int) FightRect.getX() + (int) FightRect.getWidth();
    hyFight = (int) FightRect.getY() + (int) FightRect.getHeight();
  }

  public int randomNumberGenerator(int min, int max) {
    //Maths.random VS Random.next()
    //https://stackoverflow.com/questions/738629/math-random-versus-random-nextintint
    Random rand = new Random();
    int span = max - min + 1;
    return (rand.nextInt(span) + min);
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(controller);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    controller.updateMove(delta);
    // Update each frame Character
    Character.updateMove(delta);
    // MAP
    this.mapRenderer.setView(gameCamera);
    this.mapRenderer.render();

    batch.begin();
    // Camera Position
    this.gameCamera.position.x = Character.getMovementX()*Settings.SCALED_TILE_SIZE;
    this.gameCamera.position.y = Character.getMovementY()*Settings.SCALED_TILE_SIZE;
    this.gameCamera.update();

    float worldStartX = Gdx.graphics.getWidth() / 2 - gameCamera.position.x;
    float worldStartY = Gdx.graphics.getHeight() / 2 - gameCamera.position.y;

    batch.draw(Character.getSpirit(),
            worldStartX + Character.getMovementX() * Settings.SCALED_TILE_SIZE,
            worldStartY + Character.getMovementY() * Settings.SCALED_TILE_SIZE,
            Settings.SCALED_TILE_SIZE,
            Settings.SCALED_TILE_SIZE * 1.5f
    );
    batch.end();

    //Change screen if fight
    if ((Character.getMovementX() <= wxFight/Settings.SCALED_TILE_SIZE) && (Character.getMovementY() <= hyFight/Settings.SCALED_TILE_SIZE) && (Character.getMovementX() >= oxFight/Settings.SCALED_TILE_SIZE) && (Character.getMovementY() >= oyFight/Settings.SCALED_TILE_SIZE) && (randomNumberGenerator(1,9) == 3)){
      context.setScreen(SCREEN_TYPE.ANIMATE);
    }
  }

  @Override
  public void resize(final int width,final int height) {
    this.gameCamera.viewportWidth = width;
    this.gameCamera.viewportHeight = height;
    this.gameCamera.update();
  }

  @Override
  public void pause(){}
  @Override
  public void resume(){}
  @Override
  public void hide(){}
  @Override
  public void dispose(){}
}