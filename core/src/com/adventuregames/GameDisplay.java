package com.adventuregames;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GameDisplay extends ApplicationAdapter {
    Stage stage;
    TextButton buttonFightMode;
    TextButton buttonMapMode;
    TextButton buttonConnectedMode;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    int choiceMainMenu;


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
        //textButtonStyle.up = skin.getDrawable("up-button");
        //textButtonStyle.down = skin.getDrawable("down-button");
        //textButtonStyle.checked = skin.getDrawable("checked-button");
        buttonFightMode = new TextButton("Fight Mode", textButtonStyle);
        buttonFightMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                choiceMainMenu = 1;
            }
        });
        buttonMapMode = new TextButton("Adventure Mode", textButtonStyle);
        buttonMapMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button 2 Pressed");
                choiceMainMenu = 2;
            }
        });
        buttonConnectedMode = new TextButton("Connected Mode", textButtonStyle);
        buttonConnectedMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button 3 Pressed");
                choiceMainMenu = 3;
            }
        });
        table.add(buttonFightMode).width(200).height(50);
        table.add(buttonMapMode).width(115).height(50);
        table.add(buttonConnectedMode).width(110).height(50);

        stage.addActor(table);
    }

    @Override
    public void render() {
        super.render();
        stage.draw();
    }

    public int getChoiceMainMenu() {
        return choiceMainMenu;
    }

    public void setChoiceMainMenu(int choiceMainMenu) {
        this.choiceMainMenu = choiceMainMenu;
    }
}