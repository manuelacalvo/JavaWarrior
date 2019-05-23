package com.actor;

import com.badlogic.gdx.math.Interpolation;
import com.enumfile.ACTOR_STATE;
import com.IndependantTiles.TileMap;

public class Actor {

  private TileMap map;
  private ACTOR_STATE state;
  private int x, y;
  private float MovementX ,MovementY;
  private int srcX, srcY;
  private int destX, destY;
  private float TimerForAnim;
  private float TIME_ANIME = 0.5f;

  /*
  Tile's position
  */
  public Actor(TileMap map, int x, int y){
    this.map = map;
    this.x = x;
    this.y = y;
    this.MovementX = x;
    this.MovementY = y;
    map.getTile(x, y).setActor(this);
    this.state = ACTOR_STATE.STANDING;
  }

  /*
  dx,y = direction character
  */
  public boolean move(int dx, int dy){

    if (state != ACTOR_STATE.STANDING){
      return false;
    }

    if (x + dx >= map.getWidth() || x + dx < 0 || y + dy >= map.getHeight() || y + dy < 0){
      return false;
    }

    if (map.getTile(x+dx, y+dy).getActor() != null){
      return false;
    }

    initMove(x, y, dx, dy);
    map.getTile(x, y).setActor(null);
    x += dx;
    y += dy;
    map.getTile(x, y).setActor(this);
    return true;
  }

  /*
  oldx,y = old position
  dirx,y = direction of movement
  */
  private void initMove(int oldx, int oldy, int dirx, int diry){
    this.srcX = oldx;
    this.srcY = oldy;
    this.destX = oldx+dirx;
    this.destY = oldy+diry;
    this.MovementX = oldx;
    this.MovementX = oldy;
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
      MovementX = Interpolation.linear.apply(srcX, destX, TimerForAnim/TIME_ANIME);
      MovementY = Interpolation.linear.apply(srcY, destY, TimerForAnim/TIME_ANIME);
      if(TimerForAnim > TIME_ANIME){
        moveDone();
      }
    }
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
}
