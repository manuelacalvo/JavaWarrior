package com.adventuregames.fight;

import com.adventuregames.MyGame;
import com.adventuregames.fight.event.FightEvent;
import com.adventuregames.fight.event.FightEventPlayer;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fighterlvl.warrior.Fighter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class FightScreen implements Screen, FightEventPlayer {

    private MyGame game;
    private Stage stage;
    private ArrayList<Fighter> enemies = new ArrayList<Fighter>();
    private Skin UISkin;
    private boolean debug;

    private Button superButt;

    /* Event system */
    private FightEvent currentEvent;
    // Double Ended Queue https://docs.oracle.com/javase/8/docs/api/index.html?java/util/ArrayDeque.html
    private Queue<FightEvent> queue = new ArrayDeque<FightEvent>();

    public FightScreen(MyGame pGame, boolean... pDebug){

        this.debug = (pDebug.length>0 && pDebug[0]);
        this.game=pGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        // TABLE
        Table tableUI=new Table();
        tableUI.setFillParent(true);
        Table tableFighters=new Table();

        // BACKGROUND
        Texture backgroundTexture = game.getAssetManager().get("core/assets/graphics/pictures/main_background.png",Texture.class);
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        //SKIN
        this.UISkin = game.getAssetManager().get("core/assets/graphics/ui/pixthulhu-ui/pixthulhu-ui.json",Skin.class);

        initHUD();

        Label promptLabel = new Label("Vide", UISkin);

        //PLAYER CHARACTER
        Image playerImage = new Image(game.getAssetManager().get("core/assets/graphics/fighter_picture/User.jpg", Texture.class));
        playerImage.setPosition(50,50);

        // Prepare Tables
        if(this.debug){
            tableUI.setDebug(true);
            tableFighters.setDebug(true);
        }
        tableUI.add(promptLabel).expandX().top().left();
        tableUI.add(superButt);
        tableUI.bottom();
        tableUI.add(tableFighters);
        tableFighters.add(playerImage);

        // Load all on stage
        stage.addActor(backgroundImage);
        //stage.addActor(playerImage);
        stage.addActor(tableUI);
        //stage.addActor(tableFighters);

        // HANDLE FIGHT
        //Fighter f1 = enemies.get(0); //TBC
        //f1.setEventPlayer(this);


    }

    /**
     * This function inits HUD elements
     */
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
    }

    /**
     *
     */
    public void queueEvent(FightEvent event){queue.add(event);}

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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

        stage.act();
        stage.draw();
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
        stage.dispose();
    }
}
