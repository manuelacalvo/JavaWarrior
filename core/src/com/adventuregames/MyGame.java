package com.adventuregames;

import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.fighterlvl.warrior.Player;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class MyGame extends Game {

    private Player player;
    private Class screenType=null;

    private AssetManager assetManager;

    /**
     * Standard constructor called from DesktopLauncher
     * @param pPlayer
     */
    public MyGame(Player pPlayer){
        this.player=pPlayer;
    }

    /**
     * Test&Debug constructor
     * @param pPlayer
     * @param screenType
     */
    public MyGame(Player pPlayer, Class screenType){
        this(pPlayer);
        this.screenType=screenType;
    }

    public void create () {

        // INIT ASSET MANAGER
        String path_background = "core/assets/graphics/pictures/main_background.png";
        String path_SkinUI = "core/assets/graphics/ui/pixthulhu-ui/pixthulhu-ui.json";
        String path_fighterThumbnail = this.getPlayer().getFighter().getThumbnailPath();

        assetManager = new AssetManager();
        assetManager.load(path_background, Texture.class);
        assetManager.load(path_SkinUI, Skin.class);
        assetManager.load(path_fighterThumbnail, Texture.class);
        assetManager.finishLoading();

        if(screenType==null){ //Standard case
            this.setScreen(new GameDisplay(this));
        }else{
            // When debuging a Screen
            if(screenType == FightScreen.class){
                this.setScreen(new FightScreen(this,true));
            }
        }
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

}

