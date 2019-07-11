package com.adventuregames.fight;

import com.adventuregames.fight.event.FightEvent;
import com.badlogic.gdx.InputAdapter;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Player;
import com.ui.DialogueBox;

import java.util.Queue;

public class FightScreenController extends InputAdapter {

    public enum STATE{
        FIGHTING, //While the fight is engaged
        SELECT_ACTION, //choose what to do after victory
        DEACTIVATED // Do nothing
        ;
    }

    private STATE state = STATE.DEACTIVATED;

    private DialogueBox dialogueBox;
    private Queue<FightEvent> queue;

    private Fighter player;
    private Fighter enemy;
    /*
    Add here more elements to update
     */
    FightScreenController(Fighter oPlayer, Fighter oEnemy, Queue<FightEvent> queue, DialogueBox dialogueBox){
        this.dialogueBox=dialogueBox;
        this.queue = queue;

        this.player = oPlayer;
        this.enemy=oEnemy;
    }

    void startFight(){
        player.fight(enemy);
    }

    /**
     * Display UI to choose what to do after the fight
     */
    private void displayNextFightDialogue(){
        this.state=STATE.SELECT_ACTION;
        dialogueBox.setVisible(true);
        dialogueBox.animateText("What do you wnat to do next ?");
    }

    private boolean isDisplayingNextDialogue(){return this.state==STATE.SELECT_ACTION;}

    public FightScreenController(Queue<FightEvent> pQueue){
        this.queue=pQueue;
    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    /*
    public void gameLoop()
    {
        for(int i=1; i< coll.getFighterVector().size(); i++)
        {
            if(!quitFight && fighter.isAlive()) {
                int choice = 0;
                coll.getFighterVector().get(0).fight(coll.getFighterVector().get(i));
                restOnce = false;

                /*while(choice!= 4 && fighter.isAlive() && choice !=5 && !quitFight)
                {

                    choice = choiceMenuFight(coll.getFighterVector().get(i));

                }*//*


            }
        }
        if(!fighter.isAlive())
        {
            System.out.println(" You are dead. You've got \" + fighter.getHitPoints() + \" life points and you've made \" + nbFights + \" fights\");");
        }
    }*/
}
