package com.adventuregames.fight.event;

import com.adventuregames.fight.FIGHT_PARTY;
import com.fighterlvl.warrior.Fighter;
import com.ui.DialogueBox;
import com.ui.StatusBox;

/**
 * A screen displaying FightEvent has to implement this interface
 */
public interface FightEventPlayer {

    /**
     * Add an event to the queue
     * @param event FightEvent to add to the queue
     */
    void queueEvent(FightEvent event);

    void setFighter(Fighter oFighter);

    DialogueBox getDialogueBox();

    StatusBox getStatusBox(FIGHT_PARTY party);
}
