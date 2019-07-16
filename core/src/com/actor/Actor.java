package com.actor;

import com.PackAnimations.EffectsInit;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.enumfile.ACTOR_STATE;
import com.enumfile.WAY;
import com.idea.Settings;

public class Actor {

  private TiledMap map;
  private ACTOR_STATE state;
  private int x, y;
  private float MovementX ,MovementY;
  private int srcX, srcY;
  private int destX, destY;
  private float REFACE_TIME_EFFECT = 0.1F;
  private float EffectTimer;
  private float WALK_TIME_EFFECT = 0.3f;
  private float RUN_TIME_EFFECT = 0.3f;
  private float WalkTimer = 0;
  private float RunTimer = 0;
  private boolean MRF; //Move Request on that Frame ?
  private WAY lookingAt;
  private EffectsInit Effect;
  private Sprite sprite;
  private TiledMapTileLayer playerLayer;

  //Tile's position
  public Actor(TiledMap map, int x, int y, EffectsInit Effect) {
    this.map = map;
    this.x = x;
    this.y = y;
    this.MovementX = x;
    this.MovementY = y;
    this.Effect = Effect;
    this.state = ACTOR_STATE.STANDING;
    this.lookingAt = WAY.DOWN;
    this.sprite = new Sprite();
    this.sprite.setPosition(this.x, this.y);
    this.playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
  }

  // dx,y = direction character
  public boolean move(WAY dir){
    if (state == ACTOR_STATE.WALKING){
      if (lookingAt == dir){ MRF = true; }
      return false;
    }

    MapObject Barrier1 = map.getLayers().get("Collision").getObjects().get("Barrier1");
    Rectangle Barrier01 = ((RectangleMapObject) Barrier1).getRectangle();
    int oxBarrier01 = (int) Barrier01.getX();
    int oyBarrier01 = (int) Barrier01.getY();
    int wxBarrier01 = (int) Barrier01.getX() + (int) Barrier01.getWidth();
    int hyBarrier01 = (int) Barrier01.getY() + (int) Barrier01.getHeight();
    if ((x + dir.getDirx() <= wxBarrier01 /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyBarrier01 /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxBarrier01 /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyBarrier01 /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject Barrier2 = map.getLayers().get("Collision").getObjects().get("Barrier2");
    Rectangle Barrier02 = ((RectangleMapObject) Barrier2).getRectangle();
    int oxBarrier02 = (int) Barrier02.getX();
    int oyBarrier02 = (int) Barrier02.getY();
    int wxBarrier02 = (int) Barrier02.getX() + (int) Barrier02.getWidth();
    int hyBarrier02 = (int) Barrier02.getY() + (int) Barrier02.getHeight();
    if ((x + dir.getDirx() <= wxBarrier02 /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyBarrier02 /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxBarrier02 /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyBarrier02 /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject Hole = map.getLayers().get("Collision").getObjects().get("Hole");
    Rectangle holerect = ((RectangleMapObject) Hole).getRectangle();
    int oxHole = (int) holerect.getX();
    int oyHole = (int) holerect.getY();
    int wxHole = (int) holerect.getX() + (int) holerect.getWidth();
    int hyHole = (int) holerect.getY() + (int) holerect.getHeight();
    if ((x + dir.getDirx() <= wxHole /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyHole /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxHole /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyHole /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject Water = map.getLayers().get("Collision").getObjects().get("Water");
    Rectangle Waterrect = ((RectangleMapObject) Water).getRectangle();
    int oxWater = (int) Waterrect.getX();
    int oyWater = (int) Waterrect.getY();
    int wxWater = (int) Waterrect.getX() + (int) Waterrect.getWidth();
    int hyWater = (int) Waterrect.getY() + (int) Waterrect.getHeight();
    if ((x + dir.getDirx() <= wxWater /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyWater /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxWater /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyWater /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject Moulin = map.getLayers().get("Collision").getObjects().get("Moulin");
    Rectangle Moulinrect = ((RectangleMapObject) Moulin).getRectangle();
    int oxMoulin = (int) Moulinrect.getX();
    int oyMoulin = (int) Moulinrect.getY();
    int wxMoulin = (int) Moulinrect.getX() + (int) Moulinrect.getWidth();
    int hyMoulin = (int) Moulinrect.getY() + (int) Moulinrect.getHeight();
    if ((x + dir.getDirx() <= wxMoulin /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyMoulin /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxMoulin /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyMoulin /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject Lamp1 = map.getLayers().get("Collision").getObjects().get("Lamp1");
    Rectangle Lamp01 = ((RectangleMapObject) Lamp1).getRectangle();
    int oxLamp01 = (int) Lamp01.getX();
    int oyLamp01 = (int) Lamp01.getY();
    int wxLamp01 = (int) Lamp01.getX() + (int) Lamp01.getWidth();
    int hyLamp01 = (int) Lamp01.getY() + (int) Lamp01.getHeight();
    if ((x + dir.getDirx() <= wxLamp01 /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyLamp01 /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxLamp01 /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyLamp01 /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject Lamp2 = map.getLayers().get("Collision").getObjects().get("Lamp2");
    Rectangle Lamp02 = ((RectangleMapObject) Lamp2).getRectangle();
    int oxLamp02 = (int) Lamp02.getX();
    int oyLamp02 = (int) Lamp02.getY();
    int wxLamp02 = (int) Lamp02.getX() + (int) Lamp02.getWidth();
    int hyLamp02 = (int) Lamp02.getY() + (int) Lamp02.getHeight();
    if ((x + dir.getDirx() <= wxLamp02 /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyLamp02 /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxLamp02 /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyLamp02 /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject Panel = map.getLayers().get("Collision").getObjects().get("Panel");
    Rectangle Panelrect = ((RectangleMapObject) Panel).getRectangle();
    int oxPanel = (int) Panelrect.getX();
    int oyPanel = (int) Panelrect.getY();
    int wxPanel = (int) Panelrect.getX() + (int) Panelrect.getWidth();
    int hyPanel = (int) Panelrect.getY() + (int) Panelrect.getHeight();
    if ((x + dir.getDirx() <= wxPanel /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyPanel /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxPanel /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyPanel /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject House1 = map.getLayers().get("Collision").getObjects().get("House1");
    Rectangle House01 = ((RectangleMapObject) House1).getRectangle();
    int oxHouse01 = (int) House01.getX();
    int oyHouse01 = (int) House01.getY();
    int wxHouse01 = (int) House01.getX() + (int) House01.getWidth();
    int hyHouse01 = (int) House01.getY() + (int) House01.getHeight();
    if ((x + dir.getDirx() <= wxHouse01 /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyHouse01 /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxHouse01 /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyHouse01 /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject House2 = map.getLayers().get("Collision").getObjects().get("House2");
    Rectangle House02 = ((RectangleMapObject) House2).getRectangle();
    int oxHouse02 = (int) House02.getX();
    int oyHouse02 = (int) House02.getY();
    int wxHouse02 = (int) House02.getX() + (int) House02.getWidth();
    int hyHouse02 = (int) House02.getY() + (int) House02.getHeight();
    if ((x + dir.getDirx() <= wxHouse02 /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyHouse02 /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxHouse02 /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyHouse02 /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject House3 = map.getLayers().get("Collision").getObjects().get("House3");
    Rectangle House03 = ((RectangleMapObject) House3).getRectangle();
    int oxHouse03 = (int) House03.getX();
    int oyHouse03 = (int) House03.getY();
    int wxHouse03 = (int) House03.getX() + (int) House03.getWidth();
    int hyHouse03 = (int) House03.getY() + (int) House03.getHeight();
    if ((x + dir.getDirx() <= wxHouse03 /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyHouse03 /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxHouse03 /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyHouse03 /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject House4 = map.getLayers().get("Collision").getObjects().get("House4");
    Rectangle House04 = ((RectangleMapObject) House4).getRectangle();
    int oxHouse04 = (int) House04.getX();
    int oyHouse04 = (int) House04.getY();
    int wxHouse04 = (int) House04.getX() + (int) House04.getWidth();
    int hyHouse04 = (int) House04.getY() + (int) House04.getHeight();
    if ((x + dir.getDirx() <= wxHouse04 /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyHouse04 /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxHouse04 /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyHouse04 /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject WallBack = map.getLayers().get("Collision").getObjects().get("WallBack");
    Rectangle WallB = ((RectangleMapObject) WallBack).getRectangle();
    int oxWallB = (int) WallB.getX();
    int oyWallB = (int) WallB.getY();
    int wxWallB = (int) WallB.getX() + (int) WallB.getWidth();
    int hyWallB = (int) WallB.getY() + (int) WallB.getHeight();
    if ((x + dir.getDirx() <= wxWallB /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyWallB /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxWallB /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyWallB /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject WallLeft = map.getLayers().get("Collision").getObjects().get("WallLeft");
    Rectangle WallL = ((RectangleMapObject) WallLeft).getRectangle();
    int oxWallL = (int) WallL.getX();
    int oyWallL = (int) WallL.getY();
    int wxWallL = (int) WallL.getX() + (int) WallL.getWidth();
    int hyWallL = (int) WallL.getY() + (int) WallL.getHeight();
    if ((x + dir.getDirx() <= wxWallL /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyWallL /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxWallL /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyWallL /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    MapObject WallRight = map.getLayers().get("Collision").getObjects().get("WallRight");
    Rectangle WallR = ((RectangleMapObject) WallRight).getRectangle();
    int oxWallR = (int) WallR.getX();
    int oyWallR = (int) WallR.getY();
    int wxWallR = (int) WallR.getX() + (int) WallR.getWidth();
    int hyWallR = (int) WallR.getY() + (int) WallR.getHeight();
    if ((x + dir.getDirx() <= wxWallR /Settings.SCALED_TILE_SIZE - 1) &&
            (y + dir.getDiry() <= hyWallR /Settings.SCALED_TILE_SIZE - 1) &&
            (x + dir.getDirx() >= oxWallR /Settings.SCALED_TILE_SIZE) &&
            (y + dir.getDiry() >= oyWallR /Settings.SCALED_TILE_SIZE)){
      return false;
    }

    initMove(dir);
    playerLayer.setCell(this.x,this.y,null);
    this.x += dir.getDirx();
    this.y += dir.getDiry();
    this.sprite.setPosition(this.x,this.y);
    this.sprite.setScale(Settings.TILE_SIZE, Settings.TILE_SIZE);
    return true;
  }

  // oldx,y = old position
  // dirx,y = direction of movement
  private void initMove(WAY dir){
    this.lookingAt = dir;
    this.srcX = x;
    this.srcY = y;
    this.destX = x+dir.getDirx();
    this.destY = y+dir.getDiry();
    this.MovementX = x;
    this.MovementY = y;
    EffectTimer = 0f;
    if ((Gdx.input.isKeyPressed(Input.Keys.SPACE)) || (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) || (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))){
      state = ACTOR_STATE.RUNNING;
    } else if ((!Gdx.input.isKeyPressed(Input.Keys.SPACE)) && (!Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) && (!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))){
      state = ACTOR_STATE.WALKING;
    }
  }

  private void moveDone(){
    state = ACTOR_STATE.STANDING;
    MovementX = destX;
    MovementY = destY;
    this.srcX = 0;
    this.srcY = 0;
    this.destX = 0;
    this.destY = 0;
  }

  public void updateMove(float delta){
    if(state == ACTOR_STATE.WALKING){
      EffectTimer += delta;
      WalkTimer += delta;
      MovementX = Interpolation.linear.apply(srcX, destX, EffectTimer / WALK_TIME_EFFECT);
      MovementY = Interpolation.linear.apply(srcY, destY, EffectTimer / WALK_TIME_EFFECT);
      if(EffectTimer > WALK_TIME_EFFECT){
        WalkTimer = EffectTimer - WALK_TIME_EFFECT;
        moveDone();
        if (MRF){ move(lookingAt); } else { WalkTimer = 0f; }
      }
    }
    if(state == ACTOR_STATE.RUNNING){
      EffectTimer += delta;
      RunTimer += delta;
      MovementX = Interpolation.linear.apply(srcX, destX, EffectTimer / RUN_TIME_EFFECT);
      MovementY = Interpolation.linear.apply(srcY, destY, EffectTimer / RUN_TIME_EFFECT);
      if(EffectTimer > RUN_TIME_EFFECT){
        RunTimer = EffectTimer - RUN_TIME_EFFECT;
        moveDone();
        if (MRF){ move(lookingAt); } else { RunTimer = 0f; }
      }
    }
    if (state == ACTOR_STATE.REFACING){
      EffectTimer += delta;
      if (EffectTimer > REFACE_TIME_EFFECT){
        state = ACTOR_STATE.STANDING;
      }
    }
    MRF = false;
  }

  public void reface(WAY dir){
    // Can't reface if you're walking.
    if (state != ACTOR_STATE.STANDING){ return; }
    // Can't reface if you're already looking at.
    if (lookingAt == dir){ return; }
    lookingAt = dir;
    state = ACTOR_STATE.STANDING;
    EffectTimer = 0f;
  }

  public float getX(){ return x; }
  public float getY(){ return y; }
  public float getMovementX() { return MovementX; }
  public float getMovementY() { return MovementY; }
  public int getDestX() { return destX; }
  public int getDestY() { return destY; }

  public TextureRegion getSpirit(){
    if (state == ACTOR_STATE.WALKING) {
      return (TextureRegion) Effect.getWalking(lookingAt).getKeyFrame(WalkTimer);
    }else if (state == ACTOR_STATE.RUNNING){
      return (TextureRegion) Effect.getRunning(lookingAt).getKeyFrame(RunTimer);
    } else if (state == ACTOR_STATE.STANDING){
      return Effect.getStanding(lookingAt);
    } else if (state == ACTOR_STATE.REFACING){
      return (TextureRegion) Effect.getWalking(lookingAt).getKeyFrames()[0];
    }
    return Effect.getStanding(WAY.DOWN);
  }

  public void draw(SpriteBatch batch) {
    if (batch.isDrawing()) {
      sprite.draw(batch);
    }
  }

  public WAY getLookingAt() { return lookingAt; }
}