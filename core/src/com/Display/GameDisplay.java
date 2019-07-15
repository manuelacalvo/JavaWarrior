package com.Display;



import com.adventuregames.MyGame;
import com.adventuregames.fight.FightAttackDisplay;
import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;
import com.shopmanagement.CollectionDisplay.CollectionFighterDisplay;

public class GameDisplay implements Screen {
    private Stage stage;
    private MyGame game;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private ImageTextButton buttonFightMode;
    private ImageTextButton buttonMapMode;
    private ImageTextButton buttonConnectedMode;
    private ImageTextButton buttonShop;
    private ImageTextButton quit;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle;
    private ImageTextButton.ImageTextButtonStyle textButtonStyleShop;
    private BitmapFont font;
    private Image image;
    private Collection coll;
    private Player player;
    private Sound sound;


    public GameDisplay(MyGame aGame, final Player player, final Collection coll) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
        Table table=new Table();
        table.setFillParent(true);
        this.coll = coll;
        this.player = player;
        Texture texture = new Texture(Gdx.files.internal("core/assets/graphics/pictures/main_background.png"));
        image = new Image(texture);

        sound=Gdx.audio.newSound(Gdx.files.internal("core/assets/sound/The_Witcher.mp3"));

        sound.play();
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/graphics/map/TilesetGame.atlas")); //
        skin.addRegions(buttonAtlas);
        textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("partyCancelSel");
        textButtonStyle.down = skin.getDrawable("partyCancelSel");

        textButtonStyleShop = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyleShop.font = font;
        textButtonStyleShop.up = skin.getDrawable("item725");
        textButtonStyleShop.down = skin.getDrawable("item725");

        buttonFightMode = new ImageTextButton("Fight", textButtonStyle);
        buttonFightMode.addListener(
                new ChangeListener() {
                    @Override
                    public void changed (ChangeEvent event, Actor actor) {
                        sound.stop();
                        coll.getFighterVector().get(1).setHitPoints(3);
                        player.setEnnemi(game.getCollection().getFighterVector().get(game.getCollection().getPlayer().getNbFights()+1));

                        game.setScreen(new FightScreen(game, 0));

                    }
                }
        );
        buttonMapMode = new ImageTextButton("Adventure", textButtonStyle);
        buttonMapMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                sound.stop();
                coll.getFighterVector().get(1).setHitPoints(3);
                player.setEnnemi(game.getCollection().getFighterVector().get(1));

                game.setScreen(new FightScreen(game, 1));

            }
        });

        buttonConnectedMode = new ImageTextButton("MultiPlayer", textButtonStyle);
        buttonConnectedMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                sound.stop();
                game.setScreen(new ServerClientDisplay(game, player, coll));


            }
        });

        quit = new ImageTextButton("Quit", textButtonStyle);
        quit.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                player.save();
                sound.stop();
                game.setScreen(new PlayerDisplay(game));
            }
        });
        quit.setPosition(500,10);

        buttonShop = new ImageTextButton("Shop", textButtonStyleShop);
        buttonShop.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                game.setScreen(new CollectionFighterDisplay(game, player, coll));

            }
        });

        buttonShop.setPosition(10, 10);


        table.add(buttonFightMode);
        table.add(buttonMapMode);
        table.add(buttonConnectedMode);


        stage.addActor(image);
        stage.addActor(table);
        stage.addActor(buttonShop);
        stage.addActor(quit);

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

    void gameLoop()
    {
        Collection coll = game.getCollection();

        for(int i=1; i< coll.getFighterVector().size(); i++)
        {

            player.setEnnemi(coll.getFighterVector().get(i));
            player.getFighter().fight(player.getEnnemi());
            if(player.getFighter().isAlive())
            {
                game.setScreen(new FightMenuDisplay(game, player));
            }
        }
        if(!player.getFighter().isAlive())        {
            System.out.println(" You are dead. You've got \" + fighter.getHitPoints() + \" life points and you've made \" + nbFights + \" fights\");");
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }




}