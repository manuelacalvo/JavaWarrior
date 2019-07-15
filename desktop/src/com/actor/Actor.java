package com.actor;

import com.PackAnimations.EffectsInit;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.enumfile.ACTOR_STATE;
import com.enumfile.WAY;
import com.idea.Settings;
import com.javawarrior.JavaWarrior;
import com.screen.GameScreen;

import java.util.Random;

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
  private float WalkTimer;
  private float RunTimer;
  private boolean MRF; //Move Request on that Frame ?
  private WAY lookingAt;
  private EffectsInit Effect;
  private Sprite sprite;
  private TiledMapTileLayer playerLayer;

  private int oxFight;
  private int oyFight;
  private int wxFight;
  private int hyFight;

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

    //todo here collision
    //bellow is the code to select a Fight zone interaction on my map
    MapObject Fight = map.getLayers().get("Fight").getObjects().get("FightZone");
    Rectangle FightRect = ((RectangleMapObject) Fight).getRectangle();
    oxFight = (int) FightRect.getX();
    oyFight = (int) FightRect.getY();
    wxFight = (int) FightRect.getX() + (int) FightRect.getWidth();
    hyFight = (int) FightRect.getY() + (int) FightRect.getHeight();
    if ((x + dir.getDirx() <= wxFight/Settings.SCALED_TILE_SIZE) && (y + dir.getDiry() <= hyFight/Settings.SCALED_TILE_SIZE) && (x + dir.getDirx() >= oxFight/Settings.SCALED_TILE_SIZE) && (y + dir.getDiry() >= oyFight/Settings.SCALED_TILE_SIZE)){ return false; }

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
}