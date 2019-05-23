package com.actor;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.enumfile.WAY;

public class PlayerController extends InputAdapter {

  /*
  give ability to hold down a direction
  */
  private boolean up, down, left, right;
  private Actor actor;

  public PlayerController(Actor p){
    this.actor = p;
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Keys.UP){ up = true; }
    if (keycode == Keys.DOWN){ down = true; }
    if (keycode == Keys.LEFT){ left = true; }
    if (keycode == Keys.RIGHT){ right = true; }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Keys.UP){ up = false; }
    if (keycode == Keys.DOWN){ down = false; }
    if (keycode == Keys.LEFT){ left = false; }
    if (keycode == Keys.RIGHT){ right = false; }
    return false;
  }

  public void updateMove(float delta){
    if(up){ actor.move(WAY.UP); return; }
    if(down){ actor.move(WAY.DOWN); return; }
    if(left){ actor.move(WAY.LEFT); return; }
    if(right){ actor.move(WAY.RIGHT); return; }
  }
}
