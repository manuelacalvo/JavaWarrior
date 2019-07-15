package com.Display;

import com.adventuregames.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fighterlvl.warrior.Player;
import com.fighterlvl.warrior.TREASURE_TYPE;


public class PotionDisplay implements Screen{
    private Stage stage;
    private MyGame game;
    private BitmapFont font;
    private Batch batch;
    private String str;
    private ImageTextButton buttonPotion;
    private ImageTextButton buttonScroll;
    private ImageTextButton buttonContinue;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle2;
    private ImageTextButton.ImageTextButtonStyle textButtonStyle3;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private int choice = 0;
    private Image image;
    private Player player;
    private int nbPotion = 0;
    private  int nbScroll = 0;




    public PotionDisplay(MyGame aGame, final Player player) {
        this.game = aGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal("core/assets/graphics/Background/bg.png"));
        image = new Image(texture);
        image.setSize(stage.getWidth(), stage.getHeight());
        font = new BitmapFont();
        str = "Actual HitPoints : " + player.getFighter().getHitPoints();
        this.player = player;

        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/graphics/map/TilesetGame.atlas")); //
        skin.addRegions(buttonAtlas);
        textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("potion");
        textButtonStyle.down = skin.getDrawable("potion");
        buttonPotion = new ImageTextButton("Potion", textButtonStyle);
        buttonPotion.setPosition(50, 30);
        buttonPotion.getImageCell().size(50,50);
        buttonPotion.addListener(new ChangeListener() {


            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(nbPotion<player.getFighter().getTreasures().get(TREASURE_TYPE.POTION.ordinal()).getNumber()) {

                    str = player.getFighter().usePotion();
                    nbPotion++;
                }else  str = "You don't have any potion ";
            }
        });
        buttonPotion.setPosition(50, 300);
        buttonPotion.setSize(70,70);


        textButtonStyle2 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle2.font = font;
        textButtonStyle2.up = skin.getDrawable("scroll");
        textButtonStyle2.down = skin.getDrawable("scroll");

        buttonScroll = new ImageTextButton("Scroll", textButtonStyle2);
        buttonScroll.setPosition(50, 200);
        buttonScroll.setSize(70,70);
        buttonScroll.addListener(new ChangeListener() {


            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(nbScroll<player.getFighter().getTreasures().get(TREASURE_TYPE.SCROLL.ordinal()).getNumber()) {

                    str = player.getFighter().useScroll();
                    nbScroll++;
                }else  str = "You don't have any scroll ";
            }
        });

        textButtonStyle3 = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle3.font = font;
        textButtonStyle3.up = skin.getDrawable("continue");
        textButtonStyle3.down = skin.getDrawable("continue");

        buttonContinue = new ImageTextButton(" ", textButtonStyle3);
        buttonContinue.setPosition(545, 10);
        buttonContinue.setSize(100,70);
        buttonContinue.addListener(new ChangeListener() {


            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new FightMenuDisplay(game, player));
            }
        });


        stage.addActor(image);
        stage.addActor(buttonPotion);
        stage.addActor(buttonScroll);
        stage.addActor(buttonContinue);
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
    @Override
    public void pause() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
        batch.begin();
        font.draw(batch, str, 300, 380);
        batch.end();

        if(player.getFighter().getHitPoints() == 0)
        {
            game.setScreen(new GameDisplay(game, player, game.getCollection()));
        }


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


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }


}
