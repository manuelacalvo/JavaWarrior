package com.shopmanagement;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.fighterlvl.warrior.Player;

public class CollectionDisplay  extends ApplicationAdapter {

    private Stage stage;
    private Image shop;
    private ImageButton fighter1;
    private int choice;


    @Override
    public void create() {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        Texture textureShop = new Texture("shop.jpg");
        shop = new Image(textureShop);
        shop.setSize(stage.getWidth(), stage.getHeight());




        Texture f1 = new Texture(Gdx.files.internal("fighter_picture/Orc.jpg"));
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(f1));
        fighter1 = new ImageButton(drawable);



        fighter1.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 1;
            }
        });




        stage.addActor(shop);
        stage.addActor(fighter1);

    }


    @Override
    public void dispose() {

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
        //this.choice = choice;
    }
}

