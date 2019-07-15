package com.Display;

import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Player;
import com.javawarrior.JWGame;

public class FightMenuDisplay implements Screen {
    private Stage stage;
    private JWGame game;
    private ImageTextButton buttonPotion;
    private ImageTextButton buttonRest;
    private ImageTextButton buttonQuit;
    private ImageTextButton buttonFight;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private int choice = 0;
    private Image image;
    private Sound sound;




    public FightMenuDisplay(JWGame aGame, final Player player) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
        Table table=new Table();
        table.setSize(stage.getWidth(),stage.getHeight());
        Gdx.input.setInputProcessor(stage);
        Texture texture = new Texture(Gdx.files.internal("core/assets/graphics/pictures/main_background.png"));
        image = new Image(texture);
        image.setSize(stage.getWidth(), stage.getHeight());
        font = new BitmapFont();
        skin = new Skin();

        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/graphics/map/TilesetGame.atlas")); //
        skin.addRegions(buttonAtlas);
        textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("partyCancelSel");
        textButtonStyle.down = skin.getDrawable("partyCancelSel");
        buttonPotion = new ImageTextButton("Potion/Scroll", textButtonStyle);
        buttonPotion.addListener(new ChangeListener() {


            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new PotionDisplay(game, player));
            }
        });



        buttonRest = new ImageTextButton("Rest", textButtonStyle);
        buttonRest.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new RestDisplay(game, player));


            }
        });
        buttonQuit = new ImageTextButton("Quit",textButtonStyle);
        buttonQuit.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new GameDisplay(game, player, null));

            }
        });
        buttonFight = new ImageTextButton("Next Fight",textButtonStyle);
        buttonFight.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.getCollection().getPlayer().setNbFights( game.getCollection().getPlayer().getNbFights()+1);
                Fighter enemy = new Fighter(game.getCollection().getFighterVector().get(game.getCollection().getPlayer().getNbFights()+1));
                player.setEnnemi(enemy);
                game.setScreen(new FightScreen(game, 0, false));


            }
        });


        table.add(buttonPotion);
        table.add(buttonRest);
        table.add(buttonQuit);
        table.add(buttonFight);

        stage.addActor(image);
        stage.addActor(table);
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
