package com.Display.renderer;

import com.adventuregames.fight.FIGHT_PARTY;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FightRenderer {

    private AssetManager assetManager;

    private Texture playerTexture;
    private Texture enemyTexture;

    private TextureRegion background;
    private TextureRegion platform;

    public FightRenderer(AssetManager assetManager, String playerTexturePath, String enemyTexturePath){
        this.assetManager=assetManager;

        TextureAtlas atlas=assetManager.get("core/assets/graphics/ui/battle/battlepack.atlas", TextureAtlas.class);
        background = atlas.findRegion("background");
        platform = atlas.findRegion("platform");
        
        playerTexture = assetManager.get(playerTexturePath);
        enemyTexture = assetManager.get(enemyTexturePath);
    }

    public void render(SpriteBatch batch){
        int windowWidth = Gdx.graphics.getWidth();
        int windowHeight = Gdx.graphics.getHeight();

        /* BACKGROUND */
        batch.draw(background, 0, 0, windowWidth, windowHeight);

        /* Render both fighters */
        if(playerTexture != null){
            batch.draw(
                    playerTexture,
                    windowWidth/5,
                    windowHeight/2,
                    100,
                    100,
                    0,
                    0,
                    100,
                    100,
                    true,
                    false
            );
        }
        if(enemyTexture !=  null){
            batch.draw(enemyTexture,
                    windowWidth*3/5,
                    windowHeight/2,
                    100,
                    100,
                    0,
                    0,
                    100,
                    100,
                    false,
                    false);
        }
    }

    public void updatePlayerTexturePath(FIGHT_PARTY eParty, String texturePath) {
        if(eParty==FIGHT_PARTY.OPPONENT)
            this.enemyTexture = assetManager.get(texturePath);
        else
            this.playerTexture = assetManager.get(texturePath);
    }
}
