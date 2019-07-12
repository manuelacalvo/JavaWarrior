package com.adventuregames.fight.event;

import com.fighterlvl.warrior.Fighter;

public class FighterChangeEvent extends FightEvent {

    private Fighter fighter;
    private int HPInstant;
    private int HPMax;

    public FighterChangeEvent(Fighter oFighter){
        this.fighter=oFighter;
        this.HPInstant = oFighter.getHitPoints();
        this.HPMax = oFighter.getMaxHP();
    }
    @Override
    public void update(float delta) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void begin(FightEventPlayer oEventPlayer) {
        super.begin(oEventPlayer);
        oEventPlayer.setFighter(fighter, HPInstant, HPMax);
    }

    public Fighter getFighter() { return fighter; }
}
