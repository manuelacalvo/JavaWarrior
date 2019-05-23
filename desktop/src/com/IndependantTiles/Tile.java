package com.tiles;

import com.actor.Actor;
import com.enumtile.TERRAIN;

public class Tile {

  private TERRAIN terrain;
  private Actor actor = null;

  public Tile(TERRAIN terrain){
    this.terrain = terrain;
  }

  public TERRAIN getTerrain(){
    return terrain;
  }

  public Actor getActor(){
    return actor;
  }

  public void setActor(Actor actor){
    this.actor = actor;
  }
}
