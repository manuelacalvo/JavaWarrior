package com.IndependantTiles;

import com.enumfile.TERRAIN;

public class TileMap {

  private int width, height;
  private Tile[][] tiles;

  public TileMap(int width, int height){
    this.width = width;
    this.height = height;
    tiles = new Tile[width][height];

    /*
    Mapping of the map (Putting step by step each tiles)
     */
    for (int x = 0; x < width; x++){
      for (int y = 0; y < height; y++){
        tiles[x][y] = new Tile(TERRAIN.INDOOR_DARK);
      }
    }
  }

  public Tile getTile(int x, int y){
    return tiles[x][y];
  }
  public int getWidth(){
    return width;
  }
  public int getHeight(){
    return height;
  }
}
