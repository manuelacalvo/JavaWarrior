package com.adventuregames;

import com.Display.GameDisplay;
import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
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

    private boolean debug;

    private AssetManager assetManager;
    private Skin skin;

    /**
     * Standard constructor called from DesktopLauncher
     * @param pPlayer Player
     */
    public MyGame(Player pPlayer, Collection pCollection){
        this.player=pPlayer;
        this.collection=pCollection;
    }

    /**
     * Test&Debug constructor
     * @param pPlayer Player
     * @param screenType type of the screen to debug
     */
    public MyGame(Player pPlayer, Collection pCollection, Class screenType){
        this(pPlayer,pCollection);
        this.screenType=screenType;
    }

    public void create () {

        // INIT DEFAULT FIGHTER WITH PICTURE
        player.setFighter(collection.getFighterVector().firstElement());
        player.getFighter().setThumbnailPath("core/assets/graphics/fighter_picture/User.jpg");

        // INIT ASSET MANAGER
        initAssetManager();

        skin = SkinGenerator.generateSkin(assetManager);

        if (screenType == null) { //Standard case
            this.setScreen(new GameDisplay(this, player, collection));
        } else {
            // DEBUG
            this.debug=true;
            if (screenType == FightScreen.class) {
                this.setScreen(new FightScreen(this));
            }
        }


    }

    private void initAssetManager(){
        String path_background = "core/assets/graphics/pictures/main_background.png";
        String path_SkinUI = "core/assets/graphics/ui/pokemon-ui/uipack.atlas";
        String path_fighterThumbnail = this.getPlayer().getFighter().getThumbnailPath();
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

