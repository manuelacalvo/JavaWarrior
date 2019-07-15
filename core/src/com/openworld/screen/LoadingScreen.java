package com.openworld.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;
import com.openworld.enumfile.SCREEN_TYPE;
import com.javawarrior.JavaWarrior;

public class LoadingScreen extends AbstractScreen{

    private final AssetManager assetManager;
    private OrthographicCamera gameCamera;

    private float latency = .05f;
    private SpriteBatch batch;
    private TextureAtlas splashAtlas;
    private TextureRegion currentFrame;
    private Animation runningAnimation;
    private float time = 0f;
    private float origine_x, origine_y;
    private BitmapFont font;
    private String PlayerGender;

    public LoadingScreen(JavaWarrior context) {
        super(context);
        this.assetManager = context.getAssetManager();
        this.gameCamera = context.getGameCamera();
        this.PlayerGender = context.setPlayerGender("F");

        //TODO set screen to choose character gender
        assetManager.load("RessourcesTileset/Hero" + PlayerGender + ".atlas", TextureAtlas.class);
        font = new BitmapFont(Gdx.files.internal("Ressources/Font/arcade/arcade.fnt"));
        batch = new SpriteBatch();
        splashAtlas = new TextureAtlas("assets/splash.atlas");
        Array<TextureAtlas.AtlasRegion> runningFrames = splashAtlas.findRegions("Font");
        runningAnimation = new Animation(latency, runningFrames, Animation.PlayMode.LOOP);
        TextureRegion firstTexture = runningFrames.first();
        origine_x = (Gdx.graphics.getWidth()  - firstTexture.getRegionWidth())/2;
        origine_y = (Gdx.graphics.getHeight() - firstTexture.getRegionHeight())/2;
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        time += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) runningAnimation.getKeyFrame(time);
        batch.begin();
        batch.draw(currentFrame, origine_x, origine_y);
        font.draw(batch, "PRESS ENTER", Gdx.graphics.getWidth()/2 - 90, 18);
        batch.end();
        if ((Gdx.input.isKeyPressed(Input.Keys.ENTER) || (Gdx.input.isKeyPressed(Input.Keys.DEL))) && assetManager.update()){
            //TODO : Add a animation smooth to go to the Game screen
            context.setScreen(SCREEN_TYPE.GAME);
        }
    }
    @Override
    public void resize(final int width,final int height) {
        this.gameCamera.viewportHeight = height;
        this.gameCamera.viewportWidth = width;
        this.gameCamera.update();
    }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() { }
    @Override
    public void dispose() { splashAtlas.dispose(); }
}
