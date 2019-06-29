package com.adventuregames.fight;

import com.Display.AbstractScreen;
import com.adventuregames.MyGame;
import com.adventuregames.fight.event.FightEvent;
import com.adventuregames.fight.event.FightEventPlayer;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fighterlvl.warrior.Fighter;
import com.ui.StatusBox;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class FightScreen extends AbstractScreen implements FightEventPlayer {

    private Stage uiStage;
    private Viewport gameViewport;

    private Table statusBoxRoot;

    private ArrayList<Fighter> enemies = new ArrayList<Fighter>();

    private Button superButt;
    private StatusBox playerStatusBox;
    private StatusBox enemyStatusBox;

    /* Event system */
    private FightEvent currentEvent;
    // Double Ended Queue https://docs.oracle.com/javase/8/docs/api/index.html?java/util/ArrayDeque.html
    private Queue<FightEvent> queue = new ArrayDeque<FightEvent>();

    public FightScreen(MyGame pGame){
        super(pGame);
        gameViewport=new ScreenViewport();

        initUI();

    }

    private void initUI(){

        // ROOT UI STAGE
        uiStage = new Stage(gameViewport);
        if(getGame().isDebug()){
            uiStage.setDebugAll(true);
            //tableUI.setDebug(true);
            //tableFighters.setDebug(true);
        }
        // Gdx.input.setInputProcessor(uiStage); controller as inputProcessor

        /* STATUS BOXES
         Name and Health of the fighters */
        statusBoxRoot = new Table();
        statusBoxRoot.setFillParent(true);
        uiStage.addActor(statusBoxRoot);

        playerStatusBox = new StatusBox(getGame().getSkin());
        playerStatusBox.setNameLabel(getGame().getPlayer().getName());

        enemyStatusBox = new StatusBox(getGame().getSkin());
        enemyStatusBox.setNameLabel("???");

        statusBoxRoot.add(playerStatusBox);
        statusBoxRoot.add(enemyStatusBox);

        /* MOVE SELECTION BOX */


        // TABLE
        Table tableUI=new Table();
        tableUI.setFillParent(true);
        Table tableFighters=new Table();

        // BACKGROUND
        Texture backgroundTexture = getGame().getAssetManager().get("core/assets/graphics/pictures/main_background.png",Texture.class);
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);

        ///////////////////////
        Label promptLabel = new Label("Vide", getGame().getSkin());

        //PLAYER CHARACTER
        Image playerImage = new Image(getGame().getAssetManager().get("core/assets/graphics/fighter_picture/User.jpg", Texture.class));
        Image enemyImage = new Image(getGame().getAssetManager().get("core/assets/graphics/fighter_picture/User.jpg", Texture.class));
        
        // Prepare Tables
        tableUI.add(tableFighters).expandX();
        tableUI.row();
        tableUI.add(promptLabel).expandX().left();
        tableUI.bottom();

        tableFighters.add(playerImage).padRight(100);
        tableFighters.add(enemyImage);

        // Load all on uiStage
        uiStage.addActor(backgroundImage);
        //uiStage.addActor(playerImage);
        uiStage.addActor(tableUI);
        //uiStage.addActor(tableFighters);

    }

    /**
     * This function inits HUD elements
     */
    /*
    private void initHUD(){
        this.superButt = new TextButton("Text Button", this.UISkin,"default");
        superButt.setSize(400,100);
        superButt.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Press a button");
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Pressed text button");
                return true;
            }
        });
    }*/

    /**
     *
     */
    public void queueEvent(FightEvent event){queue.add(event);}

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

        uiStage.act();
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
        System.out.println(
                "update implementation missing"
        );
    }
}
