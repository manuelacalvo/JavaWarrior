package com.adventuregames;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fighterlvl.warrior.Fighter;

public class FightDisplay implements Screen {

    private Game game;
    private Stage stage;
    private Fighter[] enemies;


    public FightDisplay(Game theGame){

        this.game=theGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        // TABLE
        Table tableUI=new Table();
        tableUI.setFillParent(true);
        //ASSET MANAGER
        AssetManager assetManager = new AssetManager();
        String path_background = "core/assets/graphics/pictures/main_background.png";
        String path_SkinUI = "core/assets/graphics/ui/pixthulhu-ui/pixthulhu-ui.json";
        assetManager.load(path_background,Texture.class);
        assetManager.load(path_SkinUI,Skin.class);
        assetManager.finishLoading();

        // BACKGROUND
        Texture backgroundTexture = assetManager.get(path_background,Texture.class);

        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        //SKIN
        Skin UISkin = assetManager.get(path_SkinUI,Skin.class);

        Button superButt = new TextButton("Text Button", UISkin,"default");
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

        Label promptLabel = new Label("Vide", UISkin);

        // Prepare the Table
        tableUI.setDebug(true);
        tableUI.add(promptLabel).expandX().top().left();
        tableUI.add(superButt);
        tableUI.bottom();

        // Load all on stage
        stage.addActor(backgroundImage);
        stage.addActor(tableUI);


    }

    /**
     *
     */
    private void loadEnemy(/*Fighter enemy*/){

        Texture enemyTexture = new Texture(Gdx.files.internal("core/assets/graphics/fighter_picture/Berserker.jpg"));
        Image enemy1Image = new Image(enemyTexture);

    }

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
