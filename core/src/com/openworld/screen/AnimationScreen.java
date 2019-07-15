package com.openworld.screen;

import com.Display.AbstractScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.Display.SCREEN_TYPE;
import com.javawarrior.JWGame;

public class AnimationScreen extends AbstractScreen {

    public AnimationScreen(JWGame context) {
        super(context);
    }
    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
        { getGame().setScreen(SCREEN_TYPE.LOADING); }
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

    @Override
    public void update(float delta) {

    }
}
