package com.adventuregames.fight;

import com.adventuregames.fight.event.FightEvent;
import com.adventuregames.fight.event.FightEventPlayer;
import com.adventuregames.fight.event.FightEventQueuer;
import com.adventuregames.fight.event.FighterChangeEvent;
import com.badlogic.gdx.InputAdapter;
import com.fighterlvl.warrior.Fighter;
import com.javawarrior.JWGame;
import com.shopmanagement.Collection;
import com.ui.DialogueBox;

import java.io.Serializable;
import java.util.Queue;

public class FightScreenController extends InputAdapter implements FightEventQueuer, Serializable {

    private FIGHT_STATE state = FIGHT_STATE.DEACTIVATED;
    private JWGame game;
    private FightEventPlayer eventPlayer;

    private DialogueBox dialogueBox;
    private Queue<FightEvent> queue;

    private Fighter playerFighter;
    private Fighter enemyFighter;

    /*
    Add here more elements to update
     */
    FightScreenController(JWGame oGame, FightScreen fightScreen, Queue<FightEvent> queue, DialogueBox dialogueBox){
        this.game=oGame;
        this.eventPlayer=fightScreen;
        this.dialogueBox=dialogueBox;
        this.queue = queue;

        this.state = FIGHT_STATE.WAITING;

        setPlayerFighter(game.getCollection().getPlayer().getFighter());
    }

    /**
     * Display UI to choose what to do after the fight
     */
    private void displayNextFightDialogue(){
        this.state=FIGHT_STATE.SELECT_ACTION;
        dialogueBox.setVisible(true);
        dialogueBox.animateText("What do you wnat to do next ?");
    }

    private boolean isDisplayingNextDialogue(){return this.state==FIGHT_STATE.SELECT_NEW_FIGHTER;}

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    public FIGHT_STATE getState(){ return this.state; }

    void gameLoop(FIGHT_PART fightAttackMode, boolean connected)
    {

        Collection coll = game.getCollection();
        enemyFighter = game.getCollection().getPlayer().getEnnemi();
        if(!connected) {
            switch (fightAttackMode){
                case USUAL:
                    playerFighter.fight(enemyFighter);
                    break;
                case FIRST_PART :
                    playerFighter.fightAttackBegin();
                    break;
                case SECOND_PART :
                    if (playerFighter.isAlive()) {
                        playerFighter.fight_attacks(enemyFighter);
                    }
                    if (enemyFighter.isAlive()) {
                        enemyFighter.fight_attacks(playerFighter);
                        enemyFighter.setChoiceAttack(enemyFighter.randomNumberGenerator(0, 2));
                    }
                    playerFighter.fightTurnAtack(enemyFighter);
                    break;
            }
        } else {
            switch (fightAttackMode) {
                case FIRST_PART:
                    playerFighter.fightAttackBegin();
                    break;
                case SECOND_PART:
                    if (playerFighter.isAlive()) {
                        playerFighter.fight_attacks(enemyFighter);
                    }
                    playerFighter.fightTurnAtack(enemyFighter);
                    break;
            }
        }
    }

    /**
     * Adds an event to the queue to be displayed
     *
     * @param event
     */
    @Override
    public void queueEvent(FightEvent event) {
        eventPlayer.queueEvent(event);
    }

    private void setPlayerFighter(Fighter playerFighter) {
        this.playerFighter = playerFighter;
        queueEvent(new FighterChangeEvent(playerFighter));
    }

    private void setEnemyFighter(Fighter enemyFighter) {
        this.enemyFighter = enemyFighter;
        queueEvent(new FighterChangeEvent(enemyFighter));
    }
}
