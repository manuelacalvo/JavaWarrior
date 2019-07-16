package com.openworld.actor;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.openworld.enumfile.WAY;

public class PlayerController extends InputAdapter {

  private Actor actor;
  private float REFACE_OR_WALK = 0.07f;
  /* give ability to hold down a direction or just reface */
  private boolean[] wichWay;
  private float[] holdWay;

  public PlayerController(Actor p){
    this.actor = p;
    wichWay = new boolean[WAY.values().length];
    wichWay[WAY.UP.ordinal()] = false;
    wichWay[WAY.DOWN.ordinal()] = false;
    wichWay[WAY.LEFT.ordinal()] = false;
    wichWay[WAY.RIGHT.ordinal()] = false;
    holdWay = new float[WAY.values().length];
    holdWay[WAY.UP.ordinal()] = 0f;
    holdWay[WAY.DOWN.ordinal()] = 0f;
    holdWay[WAY.LEFT.ordinal()] = 0f;
    holdWay[WAY.RIGHT.ordinal()] = 0f;
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Keys.UP){ wichWay[WAY.UP.ordinal()] = true; }
    if (keycode == Keys.DOWN){ wichWay[WAY.DOWN.ordinal()] = true; }
    if (keycode == Keys.LEFT){ wichWay[WAY.LEFT.ordinal()] = true; }
    if (keycode == Keys.RIGHT){ wichWay[WAY.RIGHT.ordinal()] = true; }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Keys.UP){ stopWay(WAY.UP); }
    if (keycode == Keys.DOWN){ stopWay(WAY.DOWN); }
    if (keycode == Keys.LEFT){ stopWay(WAY.LEFT); }
    if (keycode == Keys.RIGHT){ stopWay(WAY.RIGHT); }
    return false;
  }

  public void updateMove(float delta){
    if(wichWay[WAY.UP.ordinal()]){ updateDir(WAY.UP, delta); return; }
    if(wichWay[WAY.DOWN.ordinal()]){ updateDir(WAY.DOWN, delta); return; }
    if(wichWay[WAY.LEFT.ordinal()]){ updateDir(WAY.LEFT, delta); return; }
    if(wichWay[WAY.RIGHT.ordinal()]){ updateDir(WAY.RIGHT, delta); return; }
  }

  private void updateDir(WAY dir, float delta){
    holdWay[dir.ordinal()] += delta;
    letSmove(dir);
  }

  private void stopWay(WAY dir){
    wichWay[dir.ordinal()] = false;
    letSreface(dir);
    holdWay[dir.ordinal()] = 0f;
  }

  private void letSmove(WAY dir){
    if (holdWay[dir.ordinal()] > REFACE_OR_WALK){
      actor.move(dir);
    }
  }

  private void letSreface(WAY dir){
    if (holdWay[dir.ordinal()] < REFACE_OR_WALK){
      actor.reface(dir);
    }
  }
}
