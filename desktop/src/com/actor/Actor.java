package com.actor;

import com.PackAnimations.EffectsInit;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.enumfile.ACTOR_STATE;
import com.IndependantTiles.TileMap;
import com.enumfile.WAY;

public class Actor {

  private TileMap map;
  private ACTOR_STATE state;
  private int x, y;
  private float MovementX ,MovementY;
  private int srcX, srcY;
  private int destX, destY;
  private float REFACE_TIME_EFFECT = 0.1F;
  private float EffectTimer;
  private float WALK_TIME_EFFECT = 0.3f;
  private float WalkTimer;
  private boolean MRF; //Move Request on that Frame ?
  private WAY lookingAt;
  private EffectsInit Effect;

  /*
  Tile's position
  */
  public Actor(TileMap map, int x, int y, EffectsInit Effect){
    this.map = map;
    this.x = x;
    this.y = y;
    this.MovementX = x;
    this.MovementY = y;
    this.Effect = Effect;
    map.getTile(x, y).setActor(this);
    this.state = ACTOR_STATE.STANDING;
    this.lookingAt = WAY.DOWN;
  }

  /*
  dx,y = direction character
  */
  public boolean move(WAY dir){

    if (state == ACTOR_STATE.WALKING){
      if (lookingAt == dir){ MRF = true; }
      return false;
    }
    if (x + dir.getDirx() >= map.getWidth() || x + dir.getDirx() < 0 || y + dir.getDiry() >= map.getHeight() || y + dir.getDiry() < 0){ return false; }
    if (map.getTile(x+dir.getDirx(), y+dir.getDiry()).getActor() != null){ return false; }

    initMove(dir);
    map.getTile(x, y).setActor(null);
    x += dir.getDirx();
    y += dir.getDiry();
    map.getTile(x, y).setActor(this);
    return true;
  }

  /*
  oldx,y = old position
  dirx,y = direction of movement
  */
  private void initMove(WAY dir){
    this.lookingAt = dir;
    this.srcX = x;
    this.srcY = y;
    this.destX = x+dir.getDirx();
    this.destY = y+dir.getDiry();
    this.MovementX = x;
    this.MovementY = y;
    EffectTimer = 0f;
    state = ACTOR_STATE.WALKING;
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
    if (state == ACTOR_STATE.REFACING){
      EffectTimer += delta;
      if (EffectTimer > REFACE_TIME_EFFECT){
        state = ACTOR_STATE.STANDING;
      }
    }
    MRF = false;
  }

  public void reface(WAY dir){
    if (state != ACTOR_STATE.STANDING){ return; } // Can't reface if you're walking.
    if (lookingAt == dir){ return; } // Can't reface if you're already looking at.
    lookingAt = dir;
    state = ACTOR_STATE.STANDING;
    EffectTimer = 0f;
  }

  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public float getMovementX() {
    return MovementX;
  }
  public float getMovementY() {
    return MovementY;
  }

  public TextureRegion getSpirit(){
    if (state == ACTOR_STATE.WALKING){
      // #Todo "return Effect.getWalking(lookingAt).getKeyFrame(WalkTimer);" not working ? Casting (TextureRegion ?)
      return (TextureRegion) Effect.getWalking(lookingAt).getKeyFrame(WalkTimer);
    } else if (state == ACTOR_STATE.STANDING){
      return Effect.getStanding(lookingAt);
    } else if (state == ACTOR_STATE.REFACING){
      return (TextureRegion) Effect.getWalking(lookingAt).getKeyFrames()[0];
    }
    return Effect.getStanding(WAY.DOWN);
  }
}
