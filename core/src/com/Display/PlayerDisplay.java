package com.Display;


import com.adventuregames.MyGame;
import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.connection.Server;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;
import com.shopmanagement.CollectionDisplay.CollectionFighterDisplay;

import java.io.*;

public class PlayerDisplay implements Screen {
    private Stage stage;
    private MyGame game;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private ImageTextButton Enter;
    private TextField userName;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Image image;
    private Collection coll;



    public PlayerDisplay(MyGame aGame, final Collection coll) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
        Table table = new Table();
        table.setFillParent(true);
        this.coll = coll;
        Texture texture = new Texture(Gdx.files.internal("core/assets/graphics/pictures/main_background.png"));
        image = new Image(texture);
        Skin skin2 = new Skin(Gdx.files.internal("uiskin.json"));
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/graphics/map/TilesetGame.atlas")); //
        skin.addRegions(buttonAtlas);
        textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("partyCancelSel");
        textButtonStyle.down = skin.getDrawable("partyCancelSel");

        userName = new TextField("Enter your name",skin2);


        Enter = new ImageTextButton("Next", textButtonStyle);
        Enter.addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        Player player  = (Player) load(userName.getText());
                        Collection coll = new Collection(player);
                        coll.shopOpen();
                        game.setScreen(new GameDisplay(aGame, player, coll));
                    }
                }
        );

        table.add(Enter);
        table.add(userName);
        stage.addActor(image);
        stage.addActor(table);


    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }


    public Object load(String name) {
        FileInputStream fis;
        ObjectInputStream ois;
        Object obj = new Object();


        try {
            File file = new File(name.hashCode() + ".txt");
            if(file.createNewFile()){
                System.out.println(" You are a new User, your acount is created with success");
                obj = new Player(name);
            }else {

                fis = new FileInputStream(file);


                ois = new ObjectInputStream(fis);

                obj = ois.readObject();
                ois.close();

                System.out.println("Your account is load you can play");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;

    }

}