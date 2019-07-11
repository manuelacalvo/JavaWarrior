package com.shopmanagement.CollectionDisplay;

import com.Display.SelectFighterDisplay;
import com.adventuregames.MyGame;
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
    private MyGame game;
    private Image shop;
    private ImageButton sword1;
    private ImageButton dagger;
    private ImageButton sword2;
    private ImageButton superSword;
    private ImageButton sword3;
    private ImageButton leatherArmor;
    private ImageButton chainMail;
    private ImageButton shield;
    private ImageButton chainMail2;
    private ImageButton leatherArmor2;
    private ImageButton heavyShield;
    private ImageButton potion;
    private ImageButton scroll;
    private ImageTextButton buttonContinue;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle;
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
    private Image gold12;
    private Image gold13;
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
    private TextArea price12Gold;
    private TextArea price13Gold;
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
    private Table price12Table;
    private Table price13Table;
    private Player player;


    public CollectionWeaponDisplay(MyGame aGame, final Player player, final Collection coll) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        Table table=new Table();
        Table table2 =new Table();
        Table table3 = new Table();
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
                game.setScreen(new SelectFighterDisplay(game, player, coll));
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

        //TODO
        Texture f1 = new Texture(Gdx.files.internal(coll.getWeaponVector().get(0).getRelativePathPicture()));
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(f1));
        sword1 = new ImageButton(drawable);
        sword1.setSize(30,40);
        Skin skin2 = new Skin(Gdx.files.internal("core/assets/graphics/ui/uiskin/uiskin.json"));
        price1Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(0).getPrice()), skin2);

        Texture f2 = new Texture(Gdx.files.internal(coll.getWeaponVector().get(1).getRelativePathPicture()));
        Drawable drawable2 = new TextureRegionDrawable(new TextureRegion(f2));
        dagger = new ImageButton(drawable2);
        dagger.setSize(30,40);
        price2Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(1).getPrice()), skin2);

        Texture f3 = new Texture(Gdx.files.internal(coll.getWeaponVector().get(2).getRelativePathPicture()));
        Drawable drawable3 = new TextureRegionDrawable(new TextureRegion(f3));
        sword2 = new ImageButton(drawable3);
        sword2.setSize(30,40);
        price3Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(2).getPrice()), skin2);

        Texture f4 = new Texture(Gdx.files.internal("core/assets/graphics/items/sword.jpg"));
        Drawable drawable4 = new TextureRegionDrawable(new TextureRegion(f4));
        superSword = new ImageButton(drawable4);
        superSword.setSize(30,40);
        price4Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(3).getPrice()), skin2);

        Texture f5 = new Texture(Gdx.files.internal(coll.getWeaponVector().get(4).getRelativePathPicture()));
        Drawable drawable5 = new TextureRegionDrawable(new TextureRegion(f5));
        sword3 = new ImageButton(drawable5);
        sword3.setSize(30,40);
        price5Gold = new TextArea(Integer.toString(coll.getWeaponVector().get(4).getPrice()), skin2);


        Texture f6 = new Texture(Gdx.files.internal(coll.getArmorVector().get(0).getRelativePathPicture()));
        Drawable drawable6 = new TextureRegionDrawable(new TextureRegion(f6));
        leatherArmor = new ImageButton(drawable6);
        leatherArmor.setSize(30,40);
        price6Gold = new TextArea(Integer.toString(coll.getArmorVector().get(0).getPrice()), skin2);



        Texture f7 = new Texture(Gdx.files.internal(coll.getArmorVector().get(1).getRelativePathPicture()));
        Drawable drawable7 = new TextureRegionDrawable(new TextureRegion(f7));
        chainMail = new ImageButton(drawable7);
        chainMail.setSize(30,40);
        price7Gold = new TextArea(Integer.toString(coll.getArmorVector().get(1).getPrice()), skin2);//


        Texture f8 = new Texture(Gdx.files.internal(coll.getArmorVector().get(2).getRelativePathPicture()));
        Drawable drawable8 = new TextureRegionDrawable(new TextureRegion(f8));
        shield = new ImageButton(drawable8);
        shield.setSize(30,40);
        price8Gold = new TextArea(Integer.toString(coll.getArmorVector().get(2).getPrice()), skin2);//


        Texture f9 = new Texture(Gdx.files.internal(coll.getArmorVector().get(3).getRelativePathPicture()));
        Drawable drawable9 = new TextureRegionDrawable(new TextureRegion(f9));
        chainMail2 = new ImageButton(drawable9);
        chainMail2.setSize(30,40);
        price9Gold = new TextArea(Integer.toString(coll.getArmorVector().get(3).getPrice()), skin2);

        Texture f10 = new Texture(Gdx.files.internal(coll.getArmorVector().get(4).getRelativePathPicture()));
        Drawable drawable10 = new TextureRegionDrawable(new TextureRegion(f10));
        leatherArmor2 = new ImageButton(drawable10);
        leatherArmor2.setSize(30,40);
        price10Gold = new TextArea(Integer.toString(coll.getArmorVector().get(4).getPrice()), skin2);

        Texture f11 = new Texture(Gdx.files.internal(coll.getArmorVector().get(5).getRelativePathPicture()));
        Drawable drawable11 = new TextureRegionDrawable(new TextureRegion(f11));
        heavyShield = new ImageButton(drawable11);
        heavyShield.setSize(30,40);
        price11Gold = new TextArea(Integer.toString(coll.getArmorVector().get(5).getPrice()), skin2);

        Texture f12 = new Texture(Gdx.files.internal("core/assets/graphics/items/potion.png"));
        Drawable drawable12 = new TextureRegionDrawable(new TextureRegion(f12));
        potion= new ImageButton(drawable12);
        potion.setSize(30,40);
        price12Gold = new TextArea(Integer.toString(coll.getTreasureVector().get(0).getPrice()), skin2);

        Texture f13 = new Texture(Gdx.files.internal("core/assets/graphics/items/scroll.jpg"));
        Drawable drawable13 = new TextureRegionDrawable(new TextureRegion(f13));
        scroll = new ImageButton(drawable13);
        scroll.setSize(30,40);
        price13Gold = new TextArea(Integer.toString(coll.getTreasureVector().get(1).getPrice()), skin2);


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
        gold9 = new Image(drawableGold);
        gold10 = new Image(drawableGold);
        gold11 = new Image(drawableGold);
        gold12 = new Image(drawableGold);
        gold13 = new Image(drawableGold);




        sword1.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyWeapon(coll.getWeaponVector().get(0));
            }
        });
        
        TextTooltip tipw1 = new TextTooltip(coll.getWeaponVector().get(0).toString(), skin2);
        tipw1.setInstant(true);
        sword1.addListener(tipw1);

        dagger.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyWeapon(coll.getWeaponVector().get(1));
            }
        });
        TextTooltip tipw2 = new TextTooltip(coll.getWeaponVector().get(1).toString(), skin2);
        tipw2.setInstant(true);
        dagger.addListener(tipw2);

        sword2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyWeapon(coll.getWeaponVector().get(2));
            }
        });
        TextTooltip tipw3 = new TextTooltip(coll.getWeaponVector().get(2).toString(), skin2);
        tipw3.setInstant(true);
        sword2.addListener(tipw3);

        superSword.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str =  coll.buyWeapon(coll.getWeaponVector().get(3));
            }
        });
        TextTooltip tipw4 = new TextTooltip(coll.getWeaponVector().get(3).toString(), skin2);
        tipw4.setInstant(true);
        superSword.addListener(tipw4);

        sword3.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str =  coll.buyWeapon(coll.getWeaponVector().get(4));
            }
        });
        TextTooltip tipw5 = new TextTooltip(coll.getWeaponVector().get(4).toString(), skin2);
        tipw5.setInstant(true);
        sword3.addListener(tipw5);

        leatherArmor.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyArmor(coll.getArmorVector().get(0));
            }
        });
        TextTooltip tipa1 = new TextTooltip(coll.getArmorVector().get(0).toString(), skin2);
        tipa1.setInstant(true);
        leatherArmor.addListener(tipa1);

        chainMail.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str = coll.buyArmor(coll.getArmorVector().get(1));
            }
        });
        TextTooltip tipa2 = new TextTooltip(coll.getArmorVector().get(1).toString(), skin2);
        tipa2.setInstant(true);
        chainMail.addListener(tipa2);

        shield.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str =  coll.buyArmor(coll.getArmorVector().get(2));
            }
        });
        TextTooltip tipa3 = new TextTooltip(coll.getArmorVector().get(2).toString(), skin2);
        tipa3.setInstant(true);
        shield.addListener(tipa3);

        chainMail2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str =  coll.buyArmor(coll.getArmorVector().get(3));
            }
        });
        TextTooltip tipa4 = new TextTooltip(coll.getArmorVector().get(3).toString(), skin2);
        tipa4.setInstant(true);
        chainMail2.addListener(tipa4);

        leatherArmor2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str =  coll.buyArmor(coll.getArmorVector().get(4));
            }
        });
        TextTooltip tipa5 = new TextTooltip(coll.getArmorVector().get(4).toString(), skin2);
        tipa5.setInstant(true);
        leatherArmor2.addListener(tipa5);

        heavyShield.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str =  coll.buyArmor(coll.getArmorVector().get(5));
            }
        });
        TextTooltip tipa6 = new TextTooltip(coll.getArmorVector().get(5).toString(), skin2);
        tipa6.setInstant(true);
        heavyShield.addListener(tipa6);

        potion.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str =  coll.buyTreasure(coll.getTreasureVector().get(0));
            }
        });
        TextTooltip tipp = new TextTooltip(coll.getTreasureVector().get(0).toString(), skin2);
        tipp.setInstant(true);
        potion.addListener(tipp);

        scroll.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                str =  coll.buyTreasure(coll.getTreasureVector().get(1));
            }
        });
        TextTooltip tips = new TextTooltip(coll.getArmorVector().get(1).toString(), skin2);
        tips.setInstant(true);
        scroll.addListener(tips);



        table.add(sword1).size(100, 100);
        table.add(dagger).size(100, 100);
        table.add(sword2).size(100, 100);
        table.add(superSword).size(100, 100);
        table.add(sword3).size(100, 100);
        table.setPosition(315, 425);

        table2.add(leatherArmor).size(100, 100);
        table2.add(chainMail).size(100, 100);
        table2.add(shield).size(100, 100);
        table2.add(chainMail2).size(100, 100);
        table2.add(leatherArmor2).size(100, 100);
        table2.add(heavyShield).size(100, 100);
        table2.setPosition(315, 270);

        table3.add(potion).size(100, 100);
        table3.add(scroll).size(60, 60);
        table3.setPosition(315, 150);

        price1Table = new Table();
        price1Table.add(gold1).size(30, 30);
        price1Table.add(price1Gold).size(30,30);
        price1Table.setPosition(120,350);

        price2Table = new Table();
        price2Table.add(gold2).size(30, 30);
        price2Table.add(price2Gold).size(30,30);
        price2Table.setPosition(210,350);

        price3Table = new Table();
        price3Table.add(gold3).size(30, 30);
        price3Table.add(price3Gold).size(30,30);
        price3Table.setPosition(310,350);

        price4Table = new Table();
        price4Table.add(gold4).size(30, 30);
        price4Table.add(price4Gold).size(30,30);
        price4Table.setPosition(410,350);

        price5Table = new Table();
        price5Table.add(gold5).size(30, 30);
        price5Table.add(price5Gold).size(30,30);
        price5Table.setPosition(500,350);

        price6Table = new Table();
        price6Table.add(gold6).size(30, 30);
        price6Table.add(price6Gold).size(30,30);
        price6Table.setPosition(80,200);

        price7Table = new Table();
        price7Table.add(gold7).size(30, 30);
        price7Table.add(price7Gold).size(30,30);
        price7Table.setPosition(170,200);

        price8Table = new Table();
        price8Table.add(gold8).size(30, 30);
        price8Table.add(price8Gold).size(30,30);
        price8Table.setPosition(270,200);

        price9Table = new Table();
        price9Table.add(gold9).size(30, 30);
        price9Table.add(price9Gold).size(30,30);
        price9Table.setPosition(360,200);

        price10Table = new Table();
        price10Table.add(gold10).size(30, 30);
        price10Table.add(price10Gold).size(30,30);
        price10Table.setPosition(470,200);

        price11Table = new Table();
        price11Table.add(gold11).size(30, 30);
        price11Table.add(price11Gold).size(30,30);
        price11Table.setPosition(580,200);

        price12Table = new Table();
        price12Table.add(gold12).size(30, 30);
        price12Table.add(price12Gold).size(30,30);
        price12Table.setPosition(280,110);

        price13Table = new Table();
        price13Table.add(gold13).size(30, 30);
        price13Table.add(price13Gold).size(30,30);
        price13Table.setPosition(370,110);


        stage.addActor(shop);
        stage.addActor(buttonContinue);
        stage.addActor(table);
        stage.addActor(table2);
        stage.addActor(table3);
        stage.addActor(gold);
        stage.addActor(price1Table);
        stage.addActor(price2Table);
        stage.addActor(price3Table);
        stage.addActor(price4Table);
        stage.addActor(price5Table);
        stage.addActor(price6Table);
        stage.addActor(price7Table);
        stage.addActor(price8Table);
        stage.addActor(price9Table);
        stage.addActor(price10Table);
        stage.addActor(price11Table);
        stage.addActor(price12Table);
        stage.addActor(price13Table);

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
