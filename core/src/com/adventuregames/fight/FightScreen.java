package com.adventuregames.fight;

import com.Display.AbstractScreen;
import com.Display.GameDisplay;
import com.Display.TakeFeatures;
import com.Display.renderer.EventQueueRenderer;
import com.Display.renderer.FightRenderer;
import com.adventuregames.MyGame;
import com.adventuregames.fight.event.FightEvent;
import com.adventuregames.fight.event.FightEventPlayer;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fighterlvl.warrior.Fighter;
import com.ui.DialogueBox;
import com.ui.StatusBox;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Queue;

public class FightScreen extends AbstractScreen implements FightEventPlayer, Serializable {

    /* FightScreen Controller */
    private FightScreenController controller;

    /* VIEW */
    private Viewport gameViewport;
    private SpriteBatch batch;
    private FightRenderer fightRenderer;
    private EventQueueRenderer eventRenderer;
    //FightDebugRenderer

    /* UI */
    private Stage uiStage;

    private Table statusBoxRoot;
    private Table dialogueRoot;

    private DialogueBox dialogueBox;
    private StatusBox playerStatusBox;
    private StatusBox enemyStatusBox;

    /* Event system */
    private FightEvent currentEvent;
    // Double Ended Queue https://docs.oracle.com/javase/8/docs/api/index.html?java/util/ArrayDeque.html
    private Queue<FightEvent> queue = new ArrayDeque<FightEvent>();

    /* Data */
    private Fighter playerFighter;
    private Fighter enemyFighter;

    //fightAttackMode = 0 --> usual fight
    //fightAttackMode = 1 --> first part of the fightAttack
    //fightAttackMode = 2 --> second part of the fightAttack
    private int fightAttackMode = 0;
    private boolean connected;

    /**
     * Fighter Screen Constructor
     * @param pGame - Game instance
     */
    public FightScreen(MyGame pGame, int attackChoosen, boolean connected){
        super(pGame);
        this.connected = connected;
        this.playerFighter = getGame().getCollection().getPlayer().getFighter();
        this.playerFighter.setParty(FIGHT_PARTY.PLAYER); // Make sure playerFighter is tagged as player
        fightAttackMode = attackChoosen;
        this.enemyFighter = getGame().getCollection().getPlayer().getEnnemi();
        this.enemyFighter.setParty(FIGHT_PARTY.OPPONENT);
        // enemyFighter = Fighter.placeHolderFighter;

        gameViewport=new ScreenViewport();

        Fighter.setEventPlayer(this);

        fightRenderer = new FightRenderer(
                getGame().getAssetManager(),
                playerFighter.getRelativePathPicture(),
                enemyFighter.getRelativePathPicture());

        eventRenderer = new EventQueueRenderer(getGame().getSkin(), queue);

        initUI();

        controller = new FightScreenController(getGame(), this, queue, dialogueBox);

        controller.gameLoop(fightAttackMode, connected);
    }

    private void initUI(){

        // ROOT UI STAGE
        uiStage = new Stage(gameViewport);
        batch = new SpriteBatch();
        if(getGame().isDebug()){
            uiStage.setDebugAll(true);
            //tableMain.setDebug(true);
            //tableFighters.setDebug(true);
        }
        // Gdx.input.setInputProcessor(uiStage); controller as inputProcessor

        /* STATUS BOXES
         Name and Health of the fighters */
        statusBoxRoot = new Table();
        statusBoxRoot.setFillParent(true);

        playerStatusBox = new StatusBox(getGame(),this.playerFighter);
        enemyStatusBox = new StatusBox(getGame(),this.enemyFighter);
        statusBoxRoot.add(playerStatusBox).expand().align(Align.left);
        statusBoxRoot.add(enemyStatusBox).expand().align(Align.right);

        /* DIALOGUE BOX */
        dialogueRoot = new Table();
        dialogueRoot.setFillParent(true);
        uiStage.addActor(dialogueRoot);


        dialogueBox = new DialogueBox(getGame().getSkin());
        dialogueRoot.add(dialogueBox).expand().align(Align.bottom);

        // BACKGROUND
        Texture backgroundTexture = getGame().getAssetManager().get("core/assets/graphics/pictures/main_background.png",Texture.class);
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);

        // Load all on uiStage
        uiStage.addActor(statusBoxRoot);

    }

    @Override
    public StatusBox getStatusBox(FIGHT_PARTY party) {
        if (party == FIGHT_PARTY.PLAYER) {
            return playerStatusBox;
        } else if (party == FIGHT_PARTY.OPPONENT) {
            return enemyStatusBox;
        } else {
            return null;
        }
    }

    /**
     * Add an event to the queue to display
     */
    public void queueEvent(FightEvent event){queue.add(event);}

    public void queueAttack(FightEvent event){queue.add(event);}

    @Override
    public DialogueBox getDialogueBox() {
        return this.dialogueBox;
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(uiStage);

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameViewport.apply();

        batch.begin();
        fightRenderer.render(batch);
        if(currentEvent != null && getGame().isDebug()){
            eventRenderer.render(batch, currentEvent);
        }
        batch.end();

        //uiStage.act();
        uiStage.draw();

    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {}

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {}

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {}

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {}

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() { uiStage.dispose(); }

    @Override
    public void update(float delta) {

        while ( currentEvent == null || currentEvent.isFinished()){
            if(queue.isEmpty() ) { // Event queue is empty
                if(fightAttackMode == 0 ) {
                    currentEvent = null;
                    if (playerFighter.isAlive()) {
                        getGame().setScreen(new TakeFeatures(getGame(), getGame().getCollection().getPlayer()));
                    } else
                        getGame().setScreen(new GameDisplay(getGame(), getGame().getCollection().getPlayer(), getGame().getCollection()));
                }
                if(fightAttackMode ==1) {
                    currentEvent = null;
                    if (playerFighter.isAlive()) {
                    getGame().setScreen(new FightAttackDisplay(getGame(), playerFighter, enemyFighter, connected));
                    }
                }
                if(fightAttackMode == 2 )
                {   currentEvent = null;

                    if(connected == false) {
                        enemyFighter = new Fighter(getGame().getCollection().getPlayer().getEnnemi());

                        if (enemyFighter.isAlive()) {

                            fightAttackMode = 1;
                            getGame().setScreen(new FightScreen(getGame(), fightAttackMode, connected));

                        } else
                            getGame().setScreen(new GameDisplay(getGame(), getGame().getCollection().getPlayer(), getGame().getCollection()));
                    }

                }
                break;

            }
            else {
                currentEvent = queue.poll();
                currentEvent.begin(this);
            }
        }

        if (currentEvent != null) {
            currentEvent.update(delta);
        }



        uiStage.act();
    }

    /**
     * Used to change a Fighter on the scene
     * @param oFighter
     */
    public void setFighter(Fighter oFighter){
        if(oFighter.getParty()==FIGHT_PARTY.OPPONENT){
            enemyStatusBox=new StatusBox(getGame(),oFighter);
        } else{
            playerStatusBox=new StatusBox(getGame(),oFighter);
        }
        fightRenderer.updatePlayerTexturePath(oFighter.getParty(),oFighter.getRelativePathPicture());
    }
}
