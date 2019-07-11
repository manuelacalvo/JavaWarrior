package com.adventuregames.fight.event;

/**
 * Events occuring during a fight
 */
public abstract class FightEvent {

    /* children have to access player */
    protected FightEventPlayer player;

    public void begin(FightEventPlayer oPlayer){
        this.player=oPlayer;
    }

    public abstract void update(float delta);

    public abstract boolean isFinished();

}
