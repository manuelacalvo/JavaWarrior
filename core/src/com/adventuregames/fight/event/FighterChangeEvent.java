package com.adventuregames.fight.event;

import com.fighterlvl.warrior.Fighter;

import java.io.Serializable;

public class FighterChangeEvent extends FightEvent implements Serializable {

    private Fighter fighter;

    public FighterChangeEvent(Fighter oFighter){
        this.fighter=oFighter;
    }
    @Override
    public void update(float delta) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void setFinished(boolean finish) {

    }

    @Override
    public void begin(FightEventPlayer oEventPlayer) {
        super.begin(oEventPlayer);
        oEventPlayer.setFighter(fighter);
    }

    public Fighter getFighter() { return fighter; }
}
