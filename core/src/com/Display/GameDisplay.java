package com.Display;



import com.adventuregames.MyGame;
import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.connection.Server;
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


    public GameDisplay(MyGame aGame, final Player player, final Collection coll) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
        Table table=new Table();
        table.setFillParent(true);
        this.coll = coll;
        this.player = player;
        Texture texture = new Texture(Gdx.files.internal("core/assets/graphics/pictures/main_background.png"));
        image = new Image(texture);

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
                       game.setScreen(new FightScreen(game));
                    }
                }
        );
        buttonMapMode = new ImageTextButton("Adventure", textButtonStyle);
        buttonMapMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                gameLoopAttack();
            }
        });

        buttonConnectedMode = new ImageTextButton("MultiPlayer", textButtonStyle);
        buttonConnectedMode.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Connected...");
                Server server = new Server(game, player);
                server.go();

                    /* Client client = new Client(game, player);
                            client.go();*/
            }
        });

        quit = new ImageTextButton("Quit", textButtonStyle);
        quit.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                player.save();
                System.exit(0);
            }
        });
        quit.setPosition(800,10);

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

    public void gameLoopAttack()
    {
        for(int i=1; i< coll.getFighterVector().size(); i++)
        {
            if(player.getFighter().isAlive()) {
                player.setNbFights(player.getNbFights()+1);
                coll.getFighterVector().get(0).fightTurnAtack(coll.getFighterVector().get(i));



            }
        }
        if(!player.getFighter().isAlive())
        {
            System.out.println(" You are dead. You've got " + player.getFighter().getHitPoints() + " life points and you've made " + player.getNbFights() + " fights");
        }
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