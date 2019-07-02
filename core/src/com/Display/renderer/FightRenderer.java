package com.Display.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
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

        /* BACKGROUND */
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if(playerTexture != null){
            batch.draw(
                    playerTexture,
                    15,
                    45,
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
    }
}
