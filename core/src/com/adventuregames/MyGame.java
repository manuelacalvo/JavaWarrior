package com.adventuregames;

import com.Display.AbstractScreen;
import com.Display.GameDisplay;
import com.adventuregames.fight.FIGHT_PARTY;
import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;
import com.tools.SkinGenerator;

public class MyGame extends Game {

    private Player player;
    private Collection collection;
    private Class screenType=null;

    private boolean debug = false;

    private AssetManager assetManager;
    private Skin skin;

    /**
     * Standard constructor called from DesktopLauncher
     * @param oCollection : Game Objects
     */
    public MyGame(Collection oCollection){
        this.player=oCollection.getPlayer();
        this.collection=oCollection;
    }

    /**
     * Test&Debug constructor
     * @param oCollection : Game Objects
     * @param screenType type of the screen to debug
     */
    public MyGame(Collection oCollection, Class screenType){
        this(oCollection);
        this.debug=true;
        this.screenType=screenType;
    }

    public void create () {

        // INIT DEFAULT FIGHTER WITH PICTURE
        player.setFighter(collection.getFighterVector().firstElement());
        player.getFighter().setThumbnailPath("core/assets/graphics/fighter_picture/User.jpg");
        player.getFighter().setParty(FIGHT_PARTY.PLAYER);

        // INIT ASSET MANAGER
        initAssetManager();

        skin = SkinGenerator.generateSkin(assetManager);

        if (!debug) { //Standard case
            this.setScreen(new GameDisplay(this, player, collection));
        } else {
            // DEBUG
            if (screenType == FightScreen.class) {
                this.setScreen(new FightScreen(this));
            }
        }


    }

    private void initAssetManager(){
        String path_background = "core/assets/graphics/pictures/main_background.png";
        String path_SkinUI = "core/assets/graphics/ui/pokemon-ui/uipack.atlas";
        String path_fighterThumbnail = this.getPlayer().getFighter().getThumbnailPath();
        // TODO : add special caracters to pokefont
        String path_pokefont = "core/assets/font/pokemon/small_letters_font.fnt";

        assetManager = new AssetManager();
        assetManager.load(path_background, Texture.class);
        assetManager.load(path_SkinUI, TextureAtlas.class);
        assetManager.load("core/assets/graphics/ui/battle/battlepack.atlas", TextureAtlas.class);
        assetManager.load(path_fighterThumbnail, Texture.class);
        assetManager.load(path_pokefont, BitmapFont.class);
        assetManager.finishLoading();
    }

    public void render () {
        super.render();

        if(getScreen() instanceof AbstractScreen){
            ((AbstractScreen)getScreen()).update(Gdx.graphics.getDeltaTime());
        }
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getScreen().render(Gdx.graphics.getDeltaTime());
    }


    public void dispose () {
    }

    public Player getPlayer(){
        return this.player;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public boolean isDebug() {
        return debug;
    }

    public Skin getSkin(){
        return this.skin;
    }

    public Collection getCollection(){
        return this.collection;
    }
}

