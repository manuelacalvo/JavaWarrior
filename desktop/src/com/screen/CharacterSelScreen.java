package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.enumfile.SCREEN_TYPE;
import com.javawarrior.JavaWarrior;

public class CharacterSelScreen extends AbstractScreen {
    private boolean Begin;
    private SpriteBatch batch;
    private Texture texture1;
    private Texture texture2;
    private BitmapFont font;
    private TextureAtlas splashAtlas;

    public CharacterSelScreen(JavaWarrior context) {
        super(context);
        Begin = false;
        batch = new SpriteBatch();
        texture1 = new Texture(Gdx.files.internal("Ressources/Tileset/dp_01.png"));
        texture2 = new Texture(Gdx.files.internal("Ressources/Tileset/dp_02.png"));
        font = new BitmapFont(Gdx.files.internal("Ressources/Font/arcade/arcade.fnt"));
        splashAtlas = new TextureAtlas("Ressources/Splash/splash.atlas");
        Array<TextureAtlas.AtlasRegion> runningFrames = splashAtlas.findRegions("Font");
    }

    @Override
    public void show() { }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                context.setPlayerGender("F");
                Begin = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                context.setPlayerGender("M");
                Begin = true;
            }
            if (Begin == true){
                getApp().setScreen(SCREEN_TYPE.LOADING);
        }
        batch.begin();
        batch.draw(texture1, 60, 40);
        batch.draw(texture2, 300, 40);
        font.draw(batch, "PRESS LEFT", 80, 32);
        font.draw(batch, "PRESS RIGHT", 340, 32);
        batch.end();
    }

    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() { }
    @Override
    public void dispose() { }
}
