package com.adventuregames.fight.event;

/**
 * A screen displaying FightEvent has to implement this interface
 */
public interface FightEventPlayer {

    public void queueEvent(FightEvent event);
}
