package com.adventuregames.fight.event;

import java.io.Serializable;

/**
 * Events occuring during a fight
 */
public abstract class FightEvent implements Serializable {

    /* children have to access player */
    protected FightEventPlayer player;

    public void begin(FightEventPlayer oPlayer){
        this.player=oPlayer;
    }

    public abstract void update(float delta);

    public abstract boolean isFinished();

}
