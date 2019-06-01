package com.adventuregames;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class FightMenuDisplay extends ApplicationAdapter {
    private Stage stage;
    private TextButton buttonPotion;
    private TextButton buttonRest;
    private TextButton buttonQuit;
    private TextButton buttonFight;
    private TextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private int choice = 0;
    private Image image;
    private  Image iB1;
    private  Image iB2;
    private  Image iB3;
    private  Image iB4;






    @Override
    public void create() {
        stage = new Stage();
        Table table=new Table();
        table.setSize(stage.getWidth(),stage.getHeight());
        Gdx.input.setInputProcessor(stage);
        Texture texture = new Texture(Gdx.files.internal("main_background.png"));
        image = new Image(texture);
        image.setSize(stage.getWidth(), stage.getHeight());
        Texture texture1 = new Texture(Gdx.files.internal("Graphics/Pictures/partyCancelSel.png"));
        iB1 = new Image(texture1);
        iB2 = new Image(texture1);
        iB3 = new Image(texture1);
        iB4 = new Image(texture1);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/ui-blue.atlas")); //
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        buttonPotion = new TextButton("Use Potions or Scroll", textButtonStyle);
        buttonPotion.getLabel().setFontScale(2);
        buttonPotion.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 1;

            }
        });
        buttonRest = new TextButton("Rest", textButtonStyle);
        buttonRest.getLabel().setFontScale(2);
        buttonRest.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 2;


            }
        });
        buttonQuit = new TextButton("Quit", textButtonStyle);
        buttonQuit.getLabel().setFontScale(2);
        buttonQuit.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 3;


            }
        });
        buttonFight = new TextButton("Next Fight", textButtonStyle);
        buttonFight.getLabel().setFontScale(2);
        buttonFight.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 4;


            }
        });


        iB1.setSize(buttonPotion.getLabel().getPrefWidth()+50,buttonPotion.getLabel().getPrefHeight()+50);
        iB2.setSize(buttonRest.getLabel().getPrefWidth()+30,buttonRest.getLabel().getPrefHeight()+30);
        iB3.setSize(buttonQuit.getLabel().getPrefWidth()+30,buttonQuit.getLabel().getPrefHeight()+30);
        iB4.setSize(buttonFight.getLabel().getPrefWidth()+30,buttonFight.getLabel().getPrefHeight()+30);


        table.add(buttonPotion).width(200).height(50);
        table.add(buttonRest).width(200).height(60);
        table.add(buttonQuit).width(200).height(70);
        table.add(buttonFight).width(200).height(80);

        iB1.setPosition(500, 485);
        iB2.setPosition(820, 495);
        iB3.setPosition(1020, 495);
        iB4.setPosition(1180, 495);

        stage.addActor(image);
        stage.addActor(iB1);
        stage.addActor(iB2);
        stage.addActor(iB3);
        stage.addActor(iB4);
        stage.addActor(table);
    }


    @Override
    public void dispose() {
        font.dispose();
        skin.dispose();
        buttonAtlas.dispose();
        buttonPotion.clear();
        buttonRest.clear();
        buttonQuit.clear();
        buttonFight.clear();
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

    public void setChoice(int choice) {
        this.choice = choice;
    }
}
