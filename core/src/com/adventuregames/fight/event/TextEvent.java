package com.adventuregames.fight.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ui.DialogueBox;

import java.io.Serializable;

public class TextEvent extends FightEvent implements Serializable {

    private boolean finished = false;

    private float timer = 0f;
    private float delay;
    private boolean awaitInput = false;
    private String text;

    private DialogueBox dialogueBox;

    public TextEvent(String sText){
        this.text=sText;
        this.delay = 0f;
    }
    public TextEvent(String text, float fDelay){
        this(text);
        this.delay=fDelay;
    }
    public TextEvent(String sText, boolean bAwaitInput){
        this(sText);
        this.awaitInput=bAwaitInput;
    }

    @Override
    public void begin(FightEventPlayer oPlayer){
        super.begin(oPlayer);
        dialogueBox = player.getDialogueBox();
        dialogueBox.setVisible(true);
        dialogueBox.animateText(text);
    }

    @Override
    public void update(float delta){
        if(dialogueBox.isFinished()){
            if (awaitInput) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
                    finished = true;
                }
            } else {
                timer += delta;
                if (timer >=  delay) {
                    timer = delay;
                    finished = true;
                }
            }
        }
    }

    @Override
    public boolean isFinished(){return finished;}
}
