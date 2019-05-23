package com.adventuregames;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GameDisplay extends ApplicationAdapter {
    private Stage stage;
    private TextButton buttonFightMode;
    private TextButton buttonMapMode;
    private TextButton buttonConnectedMode;
    private TextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private int choice = 0;





    @Override
    public void create() {
        stage = new Stage();
        Table table=new Table();
        table.setSize(800,480);
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/ui-blue.atlas")); //
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        buttonFightMode = new TextButton("Fight Mode", textButtonStyle);
        buttonFightMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                //gameController.fightMode();
                choice = 1;
                //Gdx.app.exit();

            }
        });
        buttonMapMode = new TextButton("Adventure Mode", textButtonStyle);
        buttonMapMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button 2 Pressed");
                choice = 2;


            }
        });
        buttonConnectedMode = new TextButton("Connected Mode", textButtonStyle);
        buttonConnectedMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button 3 Pressed");
                choice = 3;


            }
        });
        table.add(buttonFightMode).width(200).height(50);
        table.add(buttonMapMode).width(115).height(50);
        table.add(buttonConnectedMode).width(110).height(50);

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        font.dispose();
        skin.dispose();
        buttonAtlas.dispose();
        buttonFightMode.clear();
        buttonMapMode.clear();
        buttonConnectedMode.clear();
        stage.clear();
        stage.dispose();
    }
    @Override
    public void pause() {
    }

    @Override
    public void render() {
        super.render();


            stage.draw();

    }

    public int getChoice(){
        return choice;
    }

}