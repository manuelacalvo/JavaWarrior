package com.Display;

import com.adventuregames.MyGame;
import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {

    private MyGame game;

    public AbstractScreen(MyGame game) {
        this.game = game;
    }

    @Override
    public abstract void dispose();

    @Override
    public abstract void hide();

    @Override
    public abstract void pause();

    public abstract void update(float delta);

    @Override
    public abstract void render(float delta);

    @Override
    public abstract void resize(int width, int height);

    @Override
    public abstract void resume();

    @Override
    public abstract void show();

    public MyGame getGame() {
        return game;
    }

}