package com.Display;

import com.adventuregames.MyGame;
import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fighterlvl.warrior.Player;

public class RestDisplay implements Screen {

    private Stage stage;
    private MyGame game;
    private BitmapFont font;
    private Batch batch;
    private String str;
    private int choice = 0;
    private Image image;
    private ImageTextButton buttonContinue;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle3;
    private TextureAtlas buttonAtlas;


    public RestDisplay(MyGame aGame, Player player) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal("core/assets/graphics/Background/bg.png"));
        image = new Image(texture);
        image.setSize(stage.getWidth(), stage.getHeight());
        font = new BitmapFont();
        str = player.getFighter().takeARest(player.getEnnemi());
        if (str.charAt(0) == ' ') {
            game.setScreen(new FightScreen(game, 0, false));
        }


        Skin skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/graphics/map/TilesetGame.atlas")); //
        skin.addRegions(buttonAtlas);
        textButtonStyle3 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle3.font = font;
        textButtonStyle3.up = skin.getDrawable("continue");
        textButtonStyle3.down = skin.getDrawable("continue");

        buttonContinue = new ImageTextButton(" ", textButtonStyle3);
        buttonContinue.setPosition(545, 10);
        buttonContinue.setSize(100, 70);
        buttonContinue.addListener(new ChangeListener() {


            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.setScreen(new FightMenuDisplay(game, player));

            }
        });

        font = new BitmapFont();


        stage.addActor(image);
        stage.addActor(buttonContinue);
    }


    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
        batch.begin();
        font.draw(batch, str, 300, 380);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

}
