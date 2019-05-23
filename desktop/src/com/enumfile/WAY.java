package com.enumfile;

public enum DIRECTIONS {

    NORTH(0,1),
    SOUTH(0,-1),
    EAST(1,0),
    WEST(-1,0),
    ;

    private int dirx, diry;

    private DIRECTIONS(int dirx, int diry) {
        this.dirx = dirx;
        this.diry =diry;
    }

    public int getDirx() { return dirx; }
    public int getDiry() { return diry; }
}
