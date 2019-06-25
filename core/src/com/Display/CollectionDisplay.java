package com.Display;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;


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
    private ImageTextButton buttonContinue;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle;
    private int choice;
    private BitmapFont font;
    private Batch batch;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private Image gold;
    private  Image silver;
    private String strGold;
    private String strSilver;
    private String str;
    private TextArea price1;
    private Table price1Gold;






    public CollectionDisplay(Game aGame, Player player, Collection coll) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        Table table=new Table();
        Table table2 =new Table();


        Gdx.input.setInputProcessor(stage);

        Texture textureShop = new Texture("core/assets/graphics/pictures/shop.jpg");
        shop = new Image(textureShop);
        shop.setSize(stage.getWidth(), stage.getHeight());

        strGold = "20";
        strSilver  = "50";
        str = "Welcome to the shop";

        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/graphics/map/TilesetGame.atlas")); //
        skin.addRegions(buttonAtlas);
        textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("continue");
        textButtonStyle.down = skin.getDrawable("continue");
        buttonContinue = new ImageTextButton(" ", textButtonStyle);
        buttonContinue.setPosition(545, 10);
        buttonContinue.setSize(100,70);
        buttonContinue.addListener(new ChangeListener() {


            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new GameDisplay(game, player, coll));
            }
        });

        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/graphics/map/TilesetGame.atlas")); //
        skin.addRegions(buttonAtlas);
        textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("continue");
        textButtonStyle.down = skin.getDrawable("continue");

        Texture f1 = new Texture(Gdx.files.internal("core/assets/graphics/fighter_picture/Orc.jpg"));
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(f1));
        fighter1 = new ImageButton(drawable);
        fighter1.setSize(30,40);
        Skin skin2 = new Skin(Gdx.files.internal("uiskin.json"));
        price1 = new TextArea("5", skin2); //Integer.toString(coll.getFighterVector().get(1).getPrice())
        price1.setPosition(300, 200);


        Texture f2 = new Texture(Gdx.files.internal("core/assets/graphics/fighter_picture/Nest of snakes.jpg"));
        Drawable drawable2 = new TextureRegionDrawable(new TextureRegion(f2));
        fighter2 = new ImageButton(drawable2);
        fighter2.setSize(30,40);

        Texture f3 = new Texture(Gdx.files.internal("core/assets/graphics/fighter_picture/Troll.jpg"));
        Drawable drawable3 = new TextureRegionDrawable(new TextureRegion(f3));
        fighter3 = new ImageButton(drawable3);
        fighter3.setSize(30,40);

        Texture f4 = new Texture(Gdx.files.internal("core/assets/graphics/fighter_picture/Berserker.jpg"));
        Drawable drawable4 = new TextureRegionDrawable(new TextureRegion(f4));
        fighter4 = new ImageButton(drawable4);

        Texture f5 = new Texture(Gdx.files.internal("core/assets/graphics/fighter_picture/Ninja.jpg"));
        Drawable drawable5 = new TextureRegionDrawable(new TextureRegion(f5));
        fighter5 = new ImageButton(drawable5);

        Texture f6 = new Texture(Gdx.files.internal("core/assets/graphics/fighter_picture/Dragon.jpg"));
        Drawable drawable6 = new TextureRegionDrawable(new TextureRegion(f6));
        fighter6 = new ImageButton(drawable6);

        Texture f7 = new Texture(Gdx.files.internal("core/assets/graphics/fighter_picture/Doppleganger.jpg"));
        Drawable drawable7 = new TextureRegionDrawable(new TextureRegion(f7));
        fighter7 = new ImageButton(drawable7);

        Texture f8 = new Texture(Gdx.files.internal("core/assets/graphics/fighter_picture/Wizard.jpg"));
        Drawable drawable8 = new TextureRegionDrawable(new TextureRegion(f8));
        fighter8 = new ImageButton(drawable8);

        Texture textureGold  = new Texture(Gdx.files.internal("core/assets/graphics/items/gold.PNG"));
        Drawable drawableGold = new TextureRegionDrawable(new TextureRegion(textureGold));
        gold = new Image(drawableGold);
        gold.setPosition(350,30);
        gold.setSize(50, 50);

        Texture textureSilver  = new Texture(Gdx.files.internal("core/assets/graphics/items/silver.PNG"));
        Drawable drawableSilver = new TextureRegionDrawable(new TextureRegion(textureSilver));
        silver = new Image(drawableSilver);
        silver.setPosition(350,30);
        silver.setSize(50, 50);



        fighter1.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyFighter(coll.getFighterVector().get(1));
            }
        });
        fighter2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyFighter(coll.getFighterVector().get(2));
            }
        });
        fighter3.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyFighter(coll.getFighterVector().get(3));
            }
        });
        fighter4.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
               str =  coll.buyFighter(coll.getFighterVector().get(4));
            }
        });
        fighter5.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
               str =  coll.buyFighter(coll.getFighterVector().get(5));
            }
        });
        fighter6.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
               str = coll.buyFighter(coll.getFighterVector().get(6));
            }
        });
        fighter7.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyFighter(coll.getFighterVector().get(7));
            }
        });
        fighter8.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
               str =  coll.buyFighter(coll.getFighterVector().get(8));
            }
        });




        table.add(fighter1).size(100, 100);
        table.add(fighter2).size(100, 100);
        table.add(fighter3).size(100, 100);
        table.add(fighter4).size(100, 100);
        table.setPosition(315, 425);

        table2.add(fighter5).size(100, 100);
        table2.add(fighter6).size(100, 100);
        table2.add(fighter7).size(100, 100);
        table2.add(fighter8).size(100, 100);
        table2.setPosition(315, 225);

        price1Gold = new Table();
        //price1Gold.add(gold);
        price1Gold.add(price1);
        price1Gold.setPosition(200,100);

        stage.addActor(shop);
        stage.addActor(buttonContinue);
        stage.addActor(table);
        stage.addActor(table2);
        stage.addActor(price1Gold);
        stage.addActor(silver);



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
        batch.begin();
        font.draw(batch, strGold, gold.getX() + 60, gold.getY() + 20);
        font.draw(batch, strSilver, silver.getX() + 60, silver.getY() + 20);
        font.draw(batch, str, price1Gold.getX() +50, price1Gold.getY() - 10 );
        //font.draw(batch, price1, 155, 375 );
        batch.end();
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

