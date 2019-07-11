package com.adventuregames.fight;

import com.Display.AbstractScreen;
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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Treasure;
import com.ui.DialogueBox;
import com.ui.StatusBox;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class FightScreen extends AbstractScreen implements FightEventPlayer {

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


    /**
     * Default Test Constructor - in prod Enemy must be provided
     * @param pGame - Game instance
     */
    public FightScreen(MyGame pGame){
        this(pGame,
                new Fighter("MrOrc",
                pGame.getCollection().getWeaponVector().firstElement(),
                pGame.getCollection().getArmorVector().firstElement(),
                new ArrayList<Treasure>(),
                30,0,"core/assets/graphics/fighter_picture/Orc.jpg"));
    }

    /**
     * Fighter Screen Constructor
     * @param pGame - Game instance
     * @param pEnemy The enemyFighter involved
     */
    public FightScreen(MyGame pGame, Fighter pEnemy){
        super(pGame);
        this.enemyFighter = pEnemy;
        this.playerFighter = getGame().getPlayer().getFighter();
        this.playerFighter.setParty(FIGHT_PARTY.PLAYER); // Make sure playerFighter is tagged as player

        getGame().getAssetManager().load(enemyFighter.getThumbnailPath(),Texture.class);
        getGame().getAssetManager().finishLoading();
        gameViewport=new ScreenViewport();

        Fighter playerFighter = getGame().getPlayer().getFighter();
        playerFighter.setEventPlayer(this);

        fightRenderer = new FightRenderer(
                getGame().getAssetManager(),
                playerFighter.getThumbnailPath(),
                enemyFighter.getThumbnailPath());

        eventRenderer = new EventQueueRenderer(getGame().getSkin(), queue);

        initUI();

        controller = new FightScreenController(playerFighter, enemyFighter, queue, dialogueBox);

        controller.startFight();
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

        playerStatusBox = new StatusBox(getGame().getSkin());
        playerStatusBox.setNameLabel(getGame().getPlayer().getName());
        //playerStatusBox.setLifeLabel(String.valueOf(getGame().getPlayer().getFighter().getArmor1().getProtection()));
        playerStatusBox.setHPText(playerFighter.getHitPoints(),playerFighter.getMaxHP());

        enemyStatusBox = new StatusBox(getGame().getSkin());
        enemyStatusBox.setNameLabel(this.enemyFighter.getName());
        enemyStatusBox.setHPText(enemyFighter.getHitPoints(),enemyFighter.getMaxHP());

        statusBoxRoot.add(playerStatusBox).expand().align(Align.left);
        statusBoxRoot.add(enemyStatusBox).expand().align(Align.right);

        /* DIALOGUE BOX */
        dialogueRoot = new Table();
        dialogueRoot.setFillParent(true);
        uiStage.addActor(dialogueRoot);

        dialogueBox = new DialogueBox(getGame().getSkin());
        dialogueRoot.add(dialogueBox).expand().align(Align.bottom);

        // TABLE


        // BACKGROUND
        Texture backgroundTexture = getGame().getAssetManager().get("core/assets/graphics/pictures/main_background.png",Texture.class);
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);

        ///////////////////////
        //Label promptLabel = new Label("Vide", getGame().getSkin());

        //PLAYER CHARACTER
        // Image playerImage = new Image(getGame().getAssetManager().get(getGame().getPlayer().getFighter().getThumbnailPath(), Texture.class));
        // Image enemyImage = new Image(getGame().getAssetManager().get(enemyFighter.getThumbnailPath(), Texture.class));
        
        // Prepare Tables

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
        if(currentEvent != null){
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
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        uiStage.dispose();
    }

    @Override
    public void update(float delta) {

        while ( currentEvent == null || currentEvent.isFinished()){
            if(queue.isEmpty()){ // Event queue is empty
                currentEvent = null;
                break;
            }else {
                currentEvent = queue.poll();
                currentEvent.begin(this);
            }
        }

        if (currentEvent != null) {
            currentEvent.update(delta);
        }

        uiStage.act();
    }


}
