package com.adventuregames;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.shopmanagement.CollectionDisplay;

public class GameDisplay implements Screen {
    private Stage stage;
    private Game game;
    private TextButton buttonFightMode;
    private TextButton buttonMapMode;
    private TextButton buttonConnectedMode;
    private TextButton buttonShop;
    private TextButtonStyle textButtonStyle;
    private BitmapFont font;
    private int choice = 0;
    private Image shop;
    private Image image;
    private  Image iB1;
    private Image iB2;
    private Image iB3;

    public GameDisplay(Game game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());


        Table table=new Table();
        table.setSize(stage.getWidth(),stage.getHeight());
        Texture texture = new Texture(Gdx.files.internal("main_background.png"));
        image = new Image(texture);
        Texture texture1 = new Texture(Gdx.files.internal("Graphics/Pictures/partyCancelSel.png"));
        iB1 = new Image(texture1);
        iB2 = new Image(texture1);
        iB3 = new Image(texture1);
        Texture textureShop = new Texture(Gdx.files.internal("Graphics/Icons/item725.png"));
        shop = new Image(textureShop);


        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        buttonFightMode = new TextButton("Fight Mode", textButtonStyle);
        buttonFightMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 1;
                game.setScreen(new FightMenuDisplay(game));
            }
        });
        buttonMapMode = new TextButton("Adventure Mode", textButtonStyle);
        buttonMapMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 2;
            }
        });

        buttonConnectedMode = new TextButton("Connected Mode", textButtonStyle);
        buttonConnectedMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 3;
            }
        });

        buttonShop = new TextButton("Shop", textButtonStyle);
        buttonShop.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 4;
                game.setScreen(new CollectionDisplay(game));
            }
        });


        buttonFightMode.setPosition(250, 250);
        iB1.setSize(buttonFightMode.getWidth()+30,buttonFightMode.getHeight()+30);

        buttonMapMode.setPosition(400, 250);
        iB2.setSize(buttonMapMode.getWidth()+30,buttonMapMode.getHeight()+30);

        buttonConnectedMode.setPosition(550, 250);
        iB3.setSize(buttonConnectedMode.getWidth()+30,buttonConnectedMode.getHeight()+30);

        buttonShop.setPosition(10, 10);
        shop.setSize(buttonShop.getWidth()+30,buttonShop.getHeight()+30);


        table.add(buttonFightMode).width(0).height(50);
        table.add(buttonMapMode).width(250).height(50);
        table.add(buttonConnectedMode).width(0).height(50);


        iB1.setPosition(buttonFightMode.getX()-105, buttonFightMode.getY()-35);
        iB2.setPosition(buttonMapMode.getX()-147, buttonMapMode.getY()-35);
        iB3.setPosition(buttonConnectedMode.getX()-170, buttonConnectedMode.getY()-35);



        stage.addActor(image);
        stage.addActor(iB1);
        stage.addActor(iB2);
        stage.addActor(iB3);
        stage.addActor(shop);
        stage.addActor(table);
        stage.addActor(buttonShop);

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


    public int getChoice(){
        return choice;
    }

}