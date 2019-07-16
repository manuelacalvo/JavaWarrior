package com.Display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.connection.Client;
import com.connection.Server;
import com.fighterlvl.warrior.Player;
import com.javawarrior.JWGame;
import com.shopmanagement.Collection;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerClientDisplay implements Screen {
    private Stage stage;
    private JWGame game;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private ImageTextButton buttonServer;
    private ImageTextButton buttonClient;
    private ImageTextButton showIP;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Image image;
    private Collection coll;
    private Player player;
    private String ip;
    private TextField ipSet;


    public ServerClientDisplay(JWGame aGame, final Player player, final Collection coll) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
        Table table=new Table();
        table.setFillParent(true);
        this.coll = coll;
        this.player = player;
        Skin skin2 = new Skin(Gdx.files.internal("uiskin.json"));
        ipSet = new TextField("enter the IP Adresse", skin2);
        Texture texture = new Texture(Gdx.files.internal("core/assets/graphics/pictures/main_background.png"));
        image = new Image(texture);
        ip = " ";
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
                        System.out.println("Connecting...");
                        Server server = new Server(game, player);
                        server.go();

                    }
                }
        );
        buttonClient = new ImageTextButton("Player Two", textButtonStyle);
        buttonClient.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Connecting...");
                Client client = new Client(game, player);
                client.go(ipSet.getText());

            }
        });
        showIP = new ImageTextButton("IP Address", textButtonStyle);
        showIP.addListener(
                new ChangeListener() {
                    @Override
                    public void changed (ChangeEvent event, Actor actor) {
                        try {

                            showIP.setText(InetAddress.getLocalHost().getHostAddress());
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );


        table.add(buttonServer);
        table.add(buttonClient);
        table.row();
        table.add(showIP);
        table.add(ipSet);


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
