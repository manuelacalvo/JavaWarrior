package com.screen;

import com.javawarrior.JavaWarrior;
import com.actor.Actor;
import com.actor.Camera;
import com.actor.PlayerController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.idea.Settings;
import com.IndependantTiles.TileMap;

public class GameScreen extends AbstractScreen {

  private Camera camera;
  private PlayerController controller;
  private Actor Character;
  private TileMap map;

  private SpriteBatch batch;
  private Texture StandingSouth;
  private Texture INDOOR_DARK;
  private Texture INDOOR_LIGHT;

  public GameScreen(JavaWarrior app)
  {
    super(app);
    StandingSouth = new Texture("RessourcesTiles/brendan_stand_south.png");
    INDOOR_DARK = new Texture("RessourcesTiles/indoor_tiles.png");
    INDOOR_LIGHT = new Texture("RessourcesTiles/indoor_tiles_shadow.png");
    batch = new SpriteBatch();

    /*
    Map Size
    */
    map = new TileMap(11,11);
    /*
    Origin Position
     */
    Character = new Actor(map,0,0);

    controller = new PlayerController(Character);
    camera = new Camera();
  }

  @Override
  public void show()
  {
    Gdx.input.setInputProcessor(controller);
  }

  @Override
  public void render(float delta)
  {
    controller.updateMove(delta);

    /*
    Update each frame Character
    */
    Character.updateMove(delta);

    /*
    Character's Position
    */
    camera.update(Character.getMovementX() + 0.5f , Character.getMovementY() + 0.5f);

    batch.begin();

    /*
    Camera Position
    */
    float worldStartX = Gdx.graphics.getWidth()/2 - camera.getCameraX()*Settings.SCALED_TILE_SIZE;
    float worldStartY = Gdx.graphics.getHeight()/2 - camera.getCameraY()* Settings.SCALED_TILE_SIZE;

    /*
    Refill
    */
    for (int x = 0; x < map.getWidth(); x++) {
      for (int y = 0; y < map.getHeight(); y++){
        Texture render;
        render = INDOOR_DARK;
        batch.draw(render,
                worldStartX+x*Settings.SCALED_TILE_SIZE,
                worldStartY+y*Settings.SCALED_TILE_SIZE,
                Settings.SCALED_TILE_SIZE,
                Settings.SCALED_TILE_SIZE);
      }
    }

    batch.draw(StandingSouth,
            worldStartX+ Character.getMovementX()* Settings.SCALED_TILE_SIZE,
            worldStartY+ Character.getMovementY()*Settings.SCALED_TILE_SIZE,
            Settings.SCALED_TILE_SIZE,
            Settings.SCALED_TILE_SIZE*1.5f
    );
    batch.end();
  }

  @Override
  public void resize(int width, int height){}

  @Override
  public void pause(){}

  @Override
  public void resume(){}

  @Override
  public void hide(){}

  @Override
  public void dispose(){}
}
