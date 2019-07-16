package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.enumfile.SCREEN_TYPE;
import com.javawarrior.JavaWarrior;

public class AnimationScreen extends AbstractScreen{

    public AnimationScreen(JavaWarrior context) {
        super(context);
    }
    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
        { getApp().setScreen(SCREEN_TYPE.GAME); }
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
