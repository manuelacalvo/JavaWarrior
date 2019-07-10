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
import com.connection.Client;
import com.connection.Server;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;
import com.shopmanagement.CollectionDisplay.CollectionFighterDisplay;

public class ServerClientDisplay implements Screen {
    private Stage stage;
    private MyGame game;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private ImageTextButton buttonServer;
    private ImageTextButton buttonClient;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Image image;
    private Collection coll;
    private Player player;


    public ServerClientDisplay(MyGame aGame, final Player player, final Collection coll) {
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

        buttonServer = new ImageTextButton("Player One", textButtonStyle);
        buttonServer.addListener(
                new ChangeListener() {
                    @Override
                    public void changed (ChangeEvent event, Actor actor) {
                        System.out.println("Connected...");
                        Server server = new Server(game, player);
                        server.go();
                    }
                }
        );
        buttonClient = new ImageTextButton("Player Two", textButtonStyle);
        buttonClient.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                 Client client = new Client(game, player);
                            client.go();
            }
        });


        table.add(buttonServer);
        table.add(buttonClient);


        stage.addActor(image);
        stage.addActor(table);


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
