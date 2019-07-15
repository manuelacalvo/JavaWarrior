package com.adventuregames.fight.event;

import com.adventuregames.fight.FIGHT_PARTY;
import com.badlogic.gdx.math.Interpolation;
import com.ui.HPBar;
import com.ui.StatusBox;

import java.io.Serializable;

/**
 * A BattleEvent where HP can be seen, depleting.
 */
public class HPChangeEvent extends FightEvent implements Serializable {

    private FIGHT_PARTY party;

    private int hpBefore;
    private int hpAfter;
    private int hpTotal;
    private float duration;

    private FightEventPlayer eventPlayer;
    private float timer;
    private boolean finished;

    public HPChangeEvent(FIGHT_PARTY party, int hpBefore, int hpAfter, int hpTotal, float duration) {
        this.party = party;
        this.hpBefore = hpBefore;
        this.hpAfter = hpAfter;
        this.hpTotal = hpTotal; // Max HP for the jauge
        this.duration = duration;
        this.timer = 0f; // Measure the time since animation started
        this.finished = false;
    }

    @Override
    public void update(float delta) {
        timer += delta;
        if (timer > duration) {
            finished = true;
            return;
        }

        // Overall progress from 0.00 to 1.00
        float progress = timer/duration;
        // HP to be displayed given the progress (From hpBefore to hpAfter)
        float hpProgress = Interpolation.linear.apply(hpBefore, hpAfter, progress);
        // Instant HP ratio to send to HPBar
        float hpProgressRelative = hpProgress/hpTotal;

        StatusBox statusBox = eventPlayer.getStatusBox(party);

        HPBar hpbar = statusBox.getHPBar();

        hpbar.displayHPLeft(hpProgressRelative);
        statusBox.setHPText((int)hpProgress, hpTotal);
    }

    @Override
    public void begin(FightEventPlayer player) {
        super.begin(player);
        this.eventPlayer = player;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
