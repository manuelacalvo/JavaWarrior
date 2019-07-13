package com.adventuregames.fight;

import com.Display.FightMenuDisplay;
import com.adventuregames.MyGame;
import com.adventuregames.fight.event.FightEvent;
import com.adventuregames.fight.event.FightEventPlayer;
import com.adventuregames.fight.event.FightEventQueuer;
import com.adventuregames.fight.event.FighterChangeEvent;
import com.badlogic.gdx.InputAdapter;
import com.fighterlvl.warrior.Fighter;
import com.shopmanagement.Collection;
import com.ui.DialogueBox;

import java.util.Queue;

public class FightScreenController extends InputAdapter implements FightEventQueuer {

    private FIGHT_STATE state = FIGHT_STATE.DEACTIVATED;
    private MyGame game;
    private FightEventPlayer eventPlayer;

    private DialogueBox dialogueBox;
    private Queue<FightEvent> queue;

    private Fighter playerFighter;
    private Fighter enemyFighter;
    /*
    Add here more elements to update
     */

    FightScreenController(MyGame oGame, FightScreen fightScreen, Queue<FightEvent> queue, DialogueBox dialogueBox){
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

    void gameLoop()
    {
        //this.state = FIGHT_STATE.SELECT_ACTION;
        //dialogueBox.setVisible(false);

        Collection coll = game.getCollection();

        //for(int i=1; i< coll.getFighterVector().size(); i++)
        //{
            //game.setScreen(new FightScreen(game));

            setEnemyFighter(game.getCollection().getPlayer().getEnnemi());
            playerFighter.fight(enemyFighter);
        //}
        /*if(!playerFighter.isAlive())        {
            System.out.println(" You are dead. You've got \" + fighter.getHitPoints() + \" life points and you've made \" + nbFights + \" fights\");");
        }*/
    }

    public void displayNextDialogue()
    {
        this.state = FIGHT_STATE.SELECT_NEW_FIGHTER;
        dialogueBox.setVisible(true);
        dialogueBox.animateText("Send out next fighter?");
    }

    public void update(float delta)
    {
        if(isDisplayingNextDialogue() && dialogueBox.isFinished())
        {

        }
    }
    public void gameLoopAttack()
    {
        Collection coll = game.getCollection();
            setEnemyFighter(coll.getFighterVector().get(1));
            playerFighter.fightTurnAtack(coll.getFighterVector().get(1));

        if(!playerFighter.isAlive())
    {
            System.out.println(" You are dead. You've got " + playerFighter.getHitPoints() + " life points and you've made " + playerFighter + " fights");
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
