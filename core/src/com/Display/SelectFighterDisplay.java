package com.Display;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
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
import com.fighterlvl.warrior.Armor;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Player;
import com.javawarrior.JWGame;
import com.shopmanagement.Collection;


public class SelectFighterDisplay implements Screen {
    private Stage stage;
    private JWGame game;
    private Image shop;
    private Image fighter;
    private Image weapon;
    private Image armor1;
    private Image armor2;
    private ImageButton arrowf1, arrowf2,arroww1,arroww2,arrowa11,arrowa12,arrowa21,arrowa22;
    private ImageTextButton buttonContinue;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Skin skin;
    private String str;
    private TextureAtlas buttonAtlas;
    private Table fighterTable,weaponTable,armor1Table, armor2Table;
    private Table table;
    private Player player;
    private int i, j, k, l;
    private Texture f1, f2, f3, f4;
    private Drawable drawable, drawable2, drawable3, drawable4, drawable5, drawable6, drawable7;
    private String f4Name;

    public SelectFighterDisplay(JWGame aGame, final Player aplayer, final Collection coll) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
        this.player = aplayer;
        Gdx.input.setInputProcessor(stage);

        Texture textureShop = new Texture(Gdx.files.internal("core/assets/graphics/Background/bg.png"));
        shop = new Image(textureShop);
        shop.setSize(stage.getWidth(), stage.getHeight());
        i=0;

        str = "Design your fighter";

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
                Armor armor = new Armor();
                if(player.getCollectionArmor2().size() != 0)
                {
                   armor = player.getCollectionArmor2().get(l);
                }
                Fighter fighter = new Fighter(player.getCollectionFighter().get(i));
                player.selectFighter(fighter, player.getCollectionWeapon().get(j), player.getCollectionArmor1().get(k),armor);
                game.setScreen(new GameDisplay(game, player, coll));
            }
        });



        f1 = new Texture(player.getCollectionFighter().get(i).getRelativePathPicture());
        drawable = new TextureRegionDrawable(new TextureRegion(f1));
        fighter = new Image(drawable);
        fighter.setSize(30,40);


        f2 = new Texture(player.getCollectionWeapon().get(j).getRelativePathPicture());
        drawable2 = new TextureRegionDrawable(new TextureRegion(f2));
        weapon = new Image(drawable2);
        weapon.setSize(30,40);


        f3 = new Texture(player.getCollectionArmor1().get(k).getRelativePathPicture());
        drawable3 = new TextureRegionDrawable(new TextureRegion(f3));
        armor1 = new Image(drawable3);
        armor1.setSize(30,40);

        f4Name = " ";
        if(player.getCollectionArmor2().size() !=0)
        {
            player.getCollectionArmor2().get(l).getRelativePathPicture();
        }
        else f4Name = "core/assets/graphics/items/no.png";

        f4 = new Texture("core/assets/graphics/items/no.png");
        drawable4 = new TextureRegionDrawable(new TextureRegion(f4));
        armor2 = new Image(drawable4);
        armor2.setSize(30,40);


        Texture f5 = new Texture(Gdx.files.internal("core/assets/graphics/buttons/arrow_double_left.jpg"));
        drawable5 = new TextureRegionDrawable(new TextureRegion(f5));
        arrowf1 = new ImageButton(drawable5);
        arroww1 = new ImageButton(drawable5);
        arrowa11 = new ImageButton(drawable5);
        arrowa21 = new ImageButton(drawable5);


        Texture f7 = new Texture(Gdx.files.internal("core/assets/graphics/buttons/arrow_double_right.jpg"));
        drawable7 = new TextureRegionDrawable(new TextureRegion(f7));
        arrowf2 = new ImageButton(drawable7);
        arroww2 = new ImageButton(drawable7);
        arrowa12 = new ImageButton(drawable7);
        arrowa22 = new ImageButton(drawable7);

        arrowf1.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(i>0)
                {
                    i--;
                }
            }
        });

        arrowf2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(i<player.getCollectionFighter().size()-1)
                {
                    i++;
                }

            }
        });

        arroww1.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(j>0)
                {
                    j--;
                }
            }
        });

        arroww2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(j<player.getCollectionWeapon().size()-1)
                {
                    j++;
                }
            }
        });

        arrowa11.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(k>0)
                {
                    k--;
                }
            }
        });

        arrowa12.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(k<player.getCollectionArmor1().size()-1)
                {
                    k++;
                }
            }
        });

        arrowa21.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                if(l>0)
                {
                    l--;
                }

            }
        });

        arrowa22.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(l<player.getCollectionArmor2().size()-1)
                {
                    l++;
                }
            }
        });


        fighterTable= new Table();
        fighterTable.add(arrowf1).size(100, 100);
        fighterTable.add(fighter).size(100, 100);
        fighterTable.add(arrowf2).size(100, 100);


        weaponTable= new Table();
        weaponTable.add(arroww1).size(100, 100);
        weaponTable.add(weapon).size(100, 100);
        weaponTable.add(arroww2).size(100, 100);


        armor1Table= new Table();
        armor1Table.add(arrowa11).size(100, 100);
        armor1Table.add(armor1).size(100, 100);
        armor1Table.add(arrowa12).size(100, 100);


        armor2Table= new Table();
        armor2Table.add(arrowa21).size(100, 100);
        armor2Table.add(armor2).size(100, 100);
        armor2Table.add(arrowa22).size(100, 100);


        table = new Table();
        table.add(fighterTable);
        table.row();
        table.add(weaponTable);
        table.row();
        table.add(armor1Table);
        table.row();
        table.add(armor2Table);
        table.setPosition(325,250);

        stage.addActor(shop);
        stage.addActor(buttonContinue);
        stage.addActor(table);


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
        f1 = new Texture(player.getCollectionFighter().get(i).getRelativePathPicture());
        drawable = new TextureRegionDrawable(new TextureRegion(f1));
        fighter.setDrawable(drawable);

        f2 = new Texture(player.getCollectionWeapon().get(j).getRelativePathPicture());
        drawable2 = new TextureRegionDrawable(new TextureRegion(f2));
        weapon.setDrawable(drawable2);

        f3 = new Texture(player.getCollectionArmor1().get(k).getRelativePathPicture());
        drawable3 = new TextureRegionDrawable(new TextureRegion(f3));
        armor1.setDrawable(drawable3);

        f4 = new Texture(player.getCollectionArmor1().get(l).getRelativePathPicture());
        drawable4 = new TextureRegionDrawable(new TextureRegion(f4));
        armor2.setDrawable(drawable4);


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
