package com.openworld.enumfile;

public enum WAY {

    UP(0,1),
    DOWN(0,-1),
    RIGHT(1,0),
    LEFT(-1,0),
    ;

    private int dirx, diry;
    private WAY(int dirx, int diry) {
        this.dirx = dirx;
        this.diry =diry;
    }

    public int getDirx() { return dirx; }
    public int getDiry() { return diry; }
}
