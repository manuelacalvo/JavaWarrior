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

public class FightMenuDisplay extends ApplicationAdapter {
    Stage stage;
    TextButton buttonPotion;
    TextButton buttonRest;
    TextButton buttonQuit;
    TextButton buttonFight;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    GameController gameController;


    public FightMenuDisplay(GameController gameController)
    {
        this.gameController = gameController;
    }



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
        buttonPotion = new TextButton("Use Potions or Scroll", textButtonStyle);
        buttonPotion.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                gameController.fightMode();
            }
        });
        buttonRest = new TextButton("Rest", textButtonStyle);
        buttonRest.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button rest Pressed");

            }
        });
        buttonQuit = new TextButton("Quit", textButtonStyle);
        buttonQuit.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button quit Pressed");

            }
        });
        buttonFight = new TextButton("Next Fight", textButtonStyle);
        buttonFight.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button 4 Pressed");

            }
        });
        table.add(buttonPotion).width(200).height(50);
        table.add(buttonRest).width(200).height(60);
        table.add(buttonQuit).width(200).height(70);
        table.add(buttonFight).width(200).height(80);

        stage.addActor(table);
    }


    @Override
    public void render() {
        super.render();

        stage.draw();
    }


}
