package com.PackAnimations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.enumfile.WAY;

import java.util.HashMap;
import java.util.Map;

public class AnimationsInit {

    private Map<WAY, Animation> walking;
    private Map<WAY, TextureRegion> standing;

    // #TODO "what the hell is the HashMap" ?
    public AnimationsInit(Animation walkUp, Animation walkDown, Animation walkLeft, Animation walkRight, TextureRegion standUp, TextureRegion standDown, TextureRegion standLeft, TextureRegion standRight){
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

    public Animation getWalking(WAY dir) { return walking.get(dir); }
    public TextureRegion getStanding(WAY dir) { return standing.get(dir); }
}