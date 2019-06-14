package com.shopmanagement;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class CollectionDisplay  implements Screen {

    private Stage stage;
    private Game game;
    private Image shop;
    private ImageButton fighter1;
    private ImageButton fighter2;
    private ImageButton fighter3;
    private ImageButton fighter4;
    private ImageButton fighter5;
    private ImageButton fighter6;
    private ImageButton fighter7;
    private ImageButton fighter8;
    private int choice;



    public CollectionDisplay(Game game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);

        Texture textureShop = new Texture("shop.jpg");
        shop = new Image(textureShop);
        shop.setSize(stage.getWidth(), stage.getHeight());




        Texture f1 = new Texture(Gdx.files.internal("fighter_picture/Orc.jpg"));
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(f1));
        fighter1 = new ImageButton(drawable);

        Texture f2 = new Texture(Gdx.files.internal("fighter_picture/Nest of snakes.jpg"));
        Drawable drawable2 = new TextureRegionDrawable(new TextureRegion(f2));
        fighter2 = new ImageButton(drawable2);

        Texture f3 = new Texture(Gdx.files.internal("fighter_picture/Troll.jpg"));
        Drawable drawable3 = new TextureRegionDrawable(new TextureRegion(f3));
        fighter3 = new ImageButton(drawable3);

        Texture f4 = new Texture(Gdx.files.internal("fighter_picture/Berserker.jpg"));
        Drawable drawable4 = new TextureRegionDrawable(new TextureRegion(f4));
        fighter4 = new ImageButton(drawable4);

        Texture f5 = new Texture(Gdx.files.internal("fighter_picture/Ninja.jpg"));
        Drawable drawable5 = new TextureRegionDrawable(new TextureRegion(f5));
        fighter5 = new ImageButton(drawable5);

        Texture f6 = new Texture(Gdx.files.internal("fighter_picture/Dragon.jpg"));
        Drawable drawable6 = new TextureRegionDrawable(new TextureRegion(f6));
        fighter6 = new ImageButton(drawable6);

        Texture f7 = new Texture(Gdx.files.internal("fighter_picture/Doppleganger.jpg"));
        Drawable drawable7 = new TextureRegionDrawable(new TextureRegion(f7));
        fighter7 = new ImageButton(drawable7);

        Texture f8 = new Texture(Gdx.files.internal("fighter_picture/Wizard.jpg"));
        Drawable drawable8 = new TextureRegionDrawable(new TextureRegion(f8));
        fighter8 = new ImageButton(drawable8);





        fighter1.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 1;
                System.out.println("test");
            }
        });
        fighter2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 2;
                System.out.println("test");
            }
        });
        fighter3.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 3;
                System.out.println("test");
            }
        });
        fighter4.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 4;
                System.out.println("test");
            }
        });
        fighter5.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 5;
                System.out.println("test");
            }
        });
        fighter6.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 6;
                System.out.println("test");
            }
        });
        fighter7.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 7;
                System.out.println("test");
            }
        });
        fighter8.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                choice = 8;
                System.out.println("test");
            }
        });


        fighter1.setPosition(20, 800);
        fighter2.setPosition(80, 800);
        fighter3.setPosition(20, 600);
        fighter4.setPosition(80, 600);
        fighter5.setPosition(20, 400);
        fighter6.setPosition(80, 400);
        fighter7.setPosition(20, 200);
        fighter8.setPosition(80, 200);


        stage.addActor(shop);
        stage.addActor(fighter1);
        stage.addActor(fighter2);
        stage.addActor(fighter3);
        stage.addActor(fighter4);
        stage.addActor(fighter5);
        stage.addActor(fighter6);
        stage.addActor(fighter7);
        stage.addActor(fighter8);


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
    public void resume() {

    }

    @Override
    public void hide() {

    }
     public int getChoice(){
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }
}

