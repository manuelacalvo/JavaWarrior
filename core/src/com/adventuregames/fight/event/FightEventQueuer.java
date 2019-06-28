package com.adventuregames.fight.event;

/**
 * Implement to send events to a Screen
 */
public interface FightEventQueuer {

    /**
     * Adds an event to the queue to be displayed
     * @param event
     */
    public void queueEvent(FightEvent event);
}
