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
  private float TimerForAnim;
  private float TIME_ANIME = 0.3f;
  private float TimeOfWalk;
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
    TimerForAnim = 0f;
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
      TimerForAnim += delta;
      TimeOfWalk += delta;
      MovementX = Interpolation.linear.apply(srcX, destX, TimerForAnim/TIME_ANIME);
      MovementY = Interpolation.linear.apply(srcY, destY, TimerForAnim/TIME_ANIME);
      if(TimerForAnim > TIME_ANIME){
        TimeOfWalk = TimerForAnim-TIME_ANIME;
        moveDone();
        if (MRF){ move(lookingAt); } else { TimeOfWalk = 0f; }
      }
    }
    MRF = false;
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
      // #Todo "return Effect.getWalking(lookingAt).getKeyFrame(TimeOfWalk);" not working ?
      return (TextureRegion) Effect.getWalking(lookingAt).getKeyFrame(TimeOfWalk);
    } else if (state == ACTOR_STATE.STANDING){
      return Effect.getStanding(lookingAt);
    }
    return Effect.getStanding(WAY.DOWN);
  }
}
