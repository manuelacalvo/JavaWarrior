package com.adventuregames;

import com.Display.PlayerDisplay;
import com.Display.AbstractScreen;
import com.Display.GameDisplay;
import com.adventuregames.fight.FIGHT_PARTY;
import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;
import com.tools.JWAssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tools.SkinGenerator;

public class MyGame extends Game {

    private Player player;
    private Collection collection;
    private Class screenType=null;

    private boolean debug = false;

    private JWAssetManager assetManager;
    private Skin skin;

    /**
     * Standard constructor called from DesktopLauncher
     */
    public MyGame(){
        /* DO NOT use getInstance() here - results in error*/
        this.assetManager = null;
    }

    /**
     * Test&Debug constructor
     * @param screenType type of the screen to debug
     */
    public MyGame(Class screenType){
        this.debug=true;
        this.screenType=screenType;
    }

    public void create () {

        /* INIT ASSETS */
        this.assetManager = JWAssetManager.getInstance();

        skin = SkinGenerator.generateSkin(assetManager);

        if (!debug) { //Standard case
            this.setScreen(new PlayerDisplay(this, collection));
        } else {
            // DEBUG
            if (screenType == FightScreen.class) {
                this.setScreen(new FightScreen(this));
            }
        }
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

