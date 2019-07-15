package com.PackAnimations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.enumfile.WAY;

import java.util.HashMap;
import java.util.Map;

public class EffectsInit {

    private Map<WAY, Animation> running;
    private Map<WAY, Animation> walking;
    private Map<WAY, TextureRegion> standing;

    public EffectsInit(Animation runUp, Animation runDown, Animation runLeft, Animation runRight, Animation walkUp, Animation walkDown, Animation walkLeft, Animation walkRight, TextureRegion standUp, TextureRegion standDown, TextureRegion standLeft, TextureRegion standRight){
        running = new HashMap<WAY, Animation>();
            running.put(WAY.UP, runUp);
            running.put(WAY.DOWN, runDown);
            running.put(WAY.LEFT, runLeft);
            running.put(WAY.RIGHT, runRight);
        walking = new HashMap<WAY, Animation>();
            walking.put(WAY.UP, walkUp);
            walking.put(WAY.DOWN, walkDown);
            walking.put(WAY.LEFT, walkLeft);
            walking.put(WAY.RIGHT, walkRight);
        standing = new HashMap<WAY, TextureRegion>();
            standing.put(WAY.UP, standUp);
            standing.put(WAY.DOWN, standDown);
            standing.put(WAY.LEFT, standLeft);
            standing.put(WAY.RIGHT, standRight);
    }

    public Animation getRunning(WAY dir) { return running.get(dir); }
    public Animation getWalking(WAY dir) { return walking.get(dir); }
    public TextureRegion getStanding(WAY dir) { return standing.get(dir); }
}