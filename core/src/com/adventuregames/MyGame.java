package com.adventuregames;

import com.Display.PlayerDisplay;
import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;


public class MyGame extends Game {

    private Player player;
    private Collection collection;
    private Class screenType=null;

    private boolean debug;

    private AssetManager assetManager;

    /**
     * Standard constructor called from DesktopLauncher

     */
    public MyGame(){

    }

    /**
     * Test&Debug constructo
     * @param screenType type of the screen to debug
     */
    public MyGame(Class screenType){
        this();
        this.screenType=screenType;
    }

    public void create () {



        // INIT ASSET MANAGER
        initAssetManager();


        if (screenType == null) { //Standard case
            this.setScreen(new PlayerDisplay(this, collection));
        } else {
            // DEBUG
            this.debug=true;
            if (screenType == FightScreen.class) {
                this.setScreen(new FightScreen(this));
            }
        }
    }

    private void initAssetManager(){
       /* String path_background = "core/assets/graphics/pictures/main_background.png";
        String path_SkinUI = "core/assets/graphics/ui/pixthulhu-ui/pixthulhu-ui.json";
        //String path_fighterThumbnail = this.getPlayer().getFighter().getThumbnailPath();

        assetManager = new AssetManager();
        assetManager.load(path_background, Texture.class);
        assetManager.load(path_SkinUI, Skin.class);
        assetManager.load(path_fighterThumbnail, Texture.class);
        assetManager.finishLoading();*/
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
}

