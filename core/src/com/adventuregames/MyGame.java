package com.adventuregames;

import com.Display.PlayerDisplay;
import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;
import com.tools.JWAssetManager;


public class MyGame extends Game {

    private Player player;
    private Collection collection;
    private Class screenType=null;

    private boolean debug;

    private JWAssetManager assetManager;

    /**
     * Standard constructor called from DesktopLauncher
     */
    public MyGame(){
        /* DO NOT use getInstance() here - results in error*/
        this.assetManager = null;
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

        /* INIT ASSETS */
        this.assetManager = JWAssetManager.getInstance();

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

