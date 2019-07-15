package com.Map;

import static com.idea.Settings.TILE_SIZE;

public class Collision_Area {

    private final float x;
    private final float y;
    private final float[] vertices;
    //vertex = position x and y

    public Collision_Area(final float x, final float y,final float[] vertices) {
        this.x = x*(1/TILE_SIZE);
        this.y = y*(1/TILE_SIZE);
        this.vertices = vertices;

        for ( int i = 0; i < vertices.length ; i += 2){
            vertices[i] = vertices[i]*(1/TILE_SIZE);
            vertices[i+1] = vertices[i+1]*(1/TILE_SIZE);
        }
    }
    public float[] getVertices() { return vertices; }
    public float getX() { return x; }
    public float getY() { return y; }
}
