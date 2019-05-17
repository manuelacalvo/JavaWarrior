package com.actor;

import com.tiles.TileMap;

public class Actor {

  private TileMap map;
  private int x;
  private int y;

  /*
  Tile's position
  */
  public Actor(TileMap map, int x, int y){
    this.map = map;
    this.x = x;
    this.y = y;
    map.getTile(x, y).setActor(this);
  }

  public boolean move(int dx, int dy){

    if (x + dx >= map.getWidth() || x + dx < 0 || y + dy >= map.getHeight() || y + dy < 0){
      return false;
    }

    if (map.getTile(x+dx, y+dy).getActor() != null){
      return false;
    }

    map.getTile(x, y).setActor(null);
    x += dx;
    y += dy;
    map.getTile(x, y).setActor(this);
    return true;
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }
}
