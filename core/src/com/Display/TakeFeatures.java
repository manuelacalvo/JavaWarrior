package com.Display;

import com.adventuregames.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fighterlvl.warrior.Player;

public class TakeFeatures implements Screen {
    private Stage stage;
    private MyGame game;
    private ImageTextButton buttonWeapon;
    private ImageTextButton buttonArmor1;
    private ImageTextButton buttonArmor2;
    private ImageTextButton buttonContinue;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private Image image;
    private String str;




    public TakeFeatures(MyGame aGame, final Player player) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
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

        buttonWeapon = new ImageTextButton("Take weapon", textButtonStyle);
        buttonWeapon.addListener(new ChangeListener() {


            @Override
            public void changed (ChangeEvent event, Actor actor) {
                player.getFighter().getEnnemyWeapon(player.getEnnemi());

            }
        });

        buttonArmor1 = new ImageTextButton("take Armor 1", textButtonStyle);
        buttonArmor1.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                player.getFighter().getEnnemyArmor1(player.getEnnemi());



            }
        });
        buttonArmor2 = new ImageTextButton("take Armor 2",textButtonStyle);
        buttonArmor2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                player.getFighter().getEnnemyArmor2(player.getEnnemi());

            }
        });
        buttonContinue = new ImageTextButton("Continue",textButtonStyle);
        buttonContinue.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                player.getFighter().getEnnemyTreasure(player.getEnnemi());
                player.takeMoney();
                game.setScreen(new FightMenuDisplay(game, player));


            }
        });



        Table weaponTable = new Table();
        weaponTable.row();
        weaponTable.add(buttonWeapon);
        if(player.getEnnemi().getWeapon().isTakeable() && !player.getFighter().getWeapon().isBetter(player.getEnnemi().getWeapon()))
        {
            weaponTable.setVisible(true);
        }else
        {
            weaponTable.setVisible(false);
        }

        Table armor1Table = new Table();
        armor1Table.add(buttonArmor1);
        if(player.getEnnemi().getArmor1().getTakeable() && !player.getFighter().getArmor1().isBetter(player.getFighter().getArmor1())) {
                armor1Table.setVisible(true);
        }else
        {
            armor1Table.setVisible(false);

        }


        Table table = new Table();
        table.add(weaponTable);
        table.add(armor1Table);
        table.setPosition(300, 300);

        if(!player.getEnnemi().getArmor2().getName().equals("null")) {

            Table armor2Table = new Table();
            armor2Table.add(buttonArmor2);
            if (player.getEnnemi().getArmor2() != null && player.getEnnemi().getArmor2().getTakeable() && !player.getFighter().getArmor2().isBetter(player.getEnnemi().getArmor2())) {

                armor2Table.setVisible(true);

            } else
            {

                armor2Table.setVisible(false);
            }
            table.add(armor2Table);
        }


        stage.addActor(image);
        stage.addActor(table);
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

