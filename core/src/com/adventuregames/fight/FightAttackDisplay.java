package com.adventuregames.fight;


import com.Display.GameDisplay;
import com.badlogic.gdx.Screen;
import com.adventuregames.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Player;
import com.sun.corba.se.impl.copyobject.FallbackObjectCopierImpl;
import com.ui.DialogueBox;

public class FightAttackDisplay implements Screen {

    private Stage stage;
    private MyGame game;
    private ImageTextButton attack1;
    private ImageTextButton attack2;
    private ImageTextButton attack3;
    private Fighter fighter;
    private Fighter enemy;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private int choice = 0;
    private Image image;
    private Table tableAttack;
    private String text;
    private SpriteBatch batch;






    public FightAttackDisplay(MyGame aGame,Fighter fighter,  Fighter enemy, boolean connected) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
        tableAttack=new Table();
        this.fighter = fighter;
        this.enemy = enemy;
        batch = new SpriteBatch();
        tableAttack.setSize(stage.getWidth(),stage.getHeight());
        Gdx.input.setInputProcessor(stage);
        Texture texture = new Texture(Gdx.files.internal("core/assets/graphics/pictures/main_background.png"));
        image = new Image(texture);
        image.setSize(stage.getWidth(), stage.getHeight());
        font = new BitmapFont();
        skin = new Skin();




        text = fighter.getName() + "'s Turn + Life : " + fighter.getHitPoints() + "\nChoose your attack";




        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/graphics/map/TilesetGame.atlas")); //
        skin.addRegions(buttonAtlas);
        textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("partyCancelSel");
        textButtonStyle.down = skin.getDrawable("partyCancelSel");
        attack1 = new ImageTextButton(fighter.getAttacks().get(0).toString(), textButtonStyle);
        attack1.addListener(new ChangeListener() {


            @Override
            public void changed (ChangeEvent event, Actor actor) {
                fighter.setChoiceAttack(0);
                game.setScreen(new FightScreen(game, 2, connected));


                dispose();
            }
        });

        attack2 = new ImageTextButton(fighter.getAttacks().get(1).toString(), textButtonStyle);
        attack2.addListener(new ChangeListener() {


            @Override
            public void changed (ChangeEvent event, Actor actor) {
                fighter.setChoiceAttack(1);
                game.setScreen(new FightScreen(game, 2, connected));

            }
        });
        attack3 = new ImageTextButton(fighter.getAttacks().get(2).toString(), textButtonStyle);
        attack3.addListener(new ChangeListener() {


            @Override
            public void changed (ChangeEvent event, Actor actor) {
                fighter.setChoiceAttack(2);
                game.setScreen(new FightScreen(game, 2, connected));

            }
        });


        Skin skin2 = new Skin(Gdx.files.internal("core/assets/graphics/ui/uiskin/uiskin.json"));

        tableAttack.add(attack1);
        tableAttack.row();
        tableAttack.add(attack2);
        tableAttack.row();
        tableAttack.add(attack3);
        tableAttack.setPosition(0,100);




        stage.addActor(image);
        stage.addActor(tableAttack);






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

        text = fighter.getStr();
        stage.draw();
        batch.begin();
        font.setColor(Color.BLACK);
        font.draw(batch, text, 200, 175);

        batch.end();

        if(fighter.getTimeToChoseAttack())
        {
            tableAttack.setVisible(true);
        }


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