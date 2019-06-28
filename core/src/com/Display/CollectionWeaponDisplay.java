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

public class CollectionWeaponDisplay implements Screen {

    private Stage stage;
    private Game game;
    private Image shop;
    private ImageButton sword1;
    private ImageButton leather_armor;
    private ImageButton dagger;
    private ImageButton chain_mail;
    private ImageButton sword2;
    private ImageButton shield;
    private ImageButton superSword;
    private ImageButton chainMail2;
    private ImageTextButton buttonContinue;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle;
    private int choice;
    private BitmapFont font;
    private Batch batch;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private Image gold;
    private Image gold1;
    private Image gold2;
    private Image gold3;
    private Image gold4;
    private Image gold5;
    private Image gold6;
    private Image gold7;
    private Image gold8;
    private Image gold9;
    private Image gold10;
    private Image gold11;
    private String strGold;
    private String str;
    private TextArea price1Gold;
    private TextArea price2Gold;
    private TextArea price3Gold;
    private TextArea price4Gold;
    private TextArea price5Gold;
    private TextArea price6Gold;
    private TextArea price7Gold;
    private TextArea price8Gold;
    private TextArea price9Gold;
    private TextArea price10Gold;
    private TextArea price11Gold;
    private Table price1Table;
    private Table price2Table;
    private Table price3Table;
    private Table price4Table;
    private Table price5Table;
    private Table price6Table;
    private Table price7Table;
    private Table price8Table;
    private Table price9Table;
    private Table price10Table;
    private Table price11Table;
    private Player player;


    public CollectionWeaponDisplay(Game aGame, final Player player, final Collection coll) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        Table table=new Table();
        Table table2 =new Table();
        this.player = player;

        Gdx.input.setInputProcessor(stage);

        Texture textureShop = new Texture("core/assets/graphics/pictures/shop.jpg");
        shop = new Image(textureShop);
        shop.setSize(stage.getWidth(), stage.getHeight());


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

        Texture f1 = new Texture(Gdx.files.internal("core/assets/graphics/items/sword.jpg"));
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(f1));
        sword1 = new ImageButton(drawable);
        sword1.setSize(30,40);
        Skin skin2 = new Skin(Gdx.files.internal("uiskin.json"));
        price1Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(0).getPrice()), skin2);//
        //price1Gold.setDisabled(true);




        Texture f2 = new Texture(Gdx.files.internal("core/assets/graphics/items/leather_armor.jpg"));
        Drawable drawable2 = new TextureRegionDrawable(new TextureRegion(f2));
        leather_armor = new ImageButton(drawable2);
        leather_armor.setSize(30,40);
        price2Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(1).getPrice()), skin2);//


        Texture f3 = new Texture(Gdx.files.internal("core/assets/graphics/items/dagger.jpg"));
        Drawable drawable3 = new TextureRegionDrawable(new TextureRegion(f3));
        dagger = new ImageButton(drawable3);
        dagger.setSize(30,40);
        price3Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(2).getPrice()), skin2);//


        Texture f4 = new Texture(Gdx.files.internal("core/assets/graphics/items/chain_mail.jpg"));
        Drawable drawable4 = new TextureRegionDrawable(new TextureRegion(f4));
        chain_mail = new ImageButton(drawable4);
        chain_mail.setSize(30,40);
        price4Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(3).getPrice()), skin2);//


        Texture f5 = new Texture(Gdx.files.internal("core/assets/graphics/items/sword.jpg"));
        Drawable drawable5 = new TextureRegionDrawable(new TextureRegion(f5));
        sword2 = new ImageButton(drawable5);
        sword2.setSize(30,40);
        price5Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(4).getPrice()), skin2);//


        Texture f6 = new Texture(Gdx.files.internal("core/assets/graphics/items/shield.jpg"));
        Drawable drawable6 = new TextureRegionDrawable(new TextureRegion(f6));
        shield = new ImageButton(drawable6);
        shield.setSize(30,40);
        price6Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(5).getPrice()), skin2);//


        Texture f7 = new Texture(Gdx.files.internal("core/assets/graphics/items/sword.jpg"));
        Drawable drawable7 = new TextureRegionDrawable(new TextureRegion(f7));
        superSword = new ImageButton(drawable7);
        superSword.setSize(30,40);
        price7Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(6).getPrice()), skin2);


        Texture f8 = new Texture(Gdx.files.internal("core/assets/graphics/items/chain_mail.jpg"));
        Drawable drawable8 = new TextureRegionDrawable(new TextureRegion(f8));
        chainMail2 = new ImageButton(drawable8);
        chainMail2.setSize(30,40);
        price8Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(7).getPrice()), skin2);


        Texture textureGold  = new Texture(Gdx.files.internal("core/assets/graphics/items/gold.PNG"));
        Drawable drawableGold = new TextureRegionDrawable(new TextureRegion(textureGold));
        gold = new Image(drawableGold);
        gold.setPosition(240, 10);
        gold.setSize(50,50);
        gold1 = new Image(drawableGold);
        gold2= new Image(drawableGold);
        gold3 = new Image(drawableGold);
        gold4 = new Image(drawableGold);
        gold5 = new Image(drawableGold);
        gold6 = new Image(drawableGold);
        gold7 = new Image(drawableGold);
        gold8 = new Image(drawableGold);



        sword1.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyFighter(coll.getFighterVector().get(1));
            }
        });
        leather_armor.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyFighter(coll.getFighterVector().get(2));
            }
        });
        dagger.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyFighter(coll.getFighterVector().get(3));
            }
        });
        chain_mail.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str =  coll.buyFighter(coll.getFighterVector().get(4));
            }
        });
        sword2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str =  coll.buyFighter(coll.getFighterVector().get(5));
            }
        });
        shield.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyFighter(coll.getFighterVector().get(6));
            }
        });
        superSword.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyFighter(coll.getFighterVector().get(7));
            }
        });
        chainMail2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str =  coll.buyFighter(coll.getFighterVector().get(8));
            }
        });




        table.add(sword1).size(100, 100);
        table.add(leather_armor).size(100, 100);
        table.add(dagger).size(100, 100);
        table.add(chain_mail).size(100, 100);
        table.setPosition(315, 425);

        table2.add(sword2).size(100, 100);
        table2.add(shield).size(100, 100);
        table2.add(superSword).size(100, 100);
        table2.add(chainMail2).size(100, 100);
        table2.setPosition(315, 225);

        price1Table = new Table();
        price1Table.add(gold1).size(30, 30);
        price1Table.add(price1Gold).size(30,30);
        price1Table.setPosition(170,350);

        price2Table = new Table();
        price2Table.add(gold2).size(30, 30);
        price2Table.add(price2Gold).size(30,30);
        price2Table.setPosition(270,350);

        price3Table = new Table();
        price3Table.add(gold3).size(30, 30);
        price3Table.add(price3Gold).size(30,30);
        price3Table.setPosition(380,350);

        price4Table = new Table();
        price4Table.add(gold4).size(30, 30);
        price4Table.add(price4Gold).size(30,30);
        price4Table.setPosition(480,350);

        price5Table = new Table();
        price5Table.add(gold5).size(30, 30);
        price5Table.add(price5Gold).size(30,30);
        price5Table.setPosition(170,170);

        price6Table = new Table();
        price6Table.add(gold6).size(30, 30);
        price6Table.add(price6Gold).size(30,30);
        price6Table.setPosition(270,170);

        price7Table = new Table();
        price7Table.add(gold7).size(30, 30);
        price7Table.add(price7Gold).size(30,30);
        price7Table.setPosition(365,170);

        price8Table = new Table();
        price8Table.add(gold8).size(30, 30);
        price8Table.add(price8Gold).size(30,30);
        price8Table.setPosition(475,170);



        stage.addActor(shop);
        stage.addActor(buttonContinue);
        stage.addActor(table);
        stage.addActor(table2);
        stage.addActor(gold);
        stage.addActor(price1Table);
        stage.addActor(price2Table);
        stage.addActor(price3Table);
        stage.addActor(price4Table);
        stage.addActor(price5Table);
        stage.addActor(price6Table);
        stage.addActor(price7Table);
        stage.addActor(price8Table);


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
        strGold = Integer.toString(player.getMoney());
        stage.act();
        stage.draw();
        batch.begin();
        font.draw(batch, strGold, 300, 40);
        font.draw(batch, str, 250, 80 );
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

}
