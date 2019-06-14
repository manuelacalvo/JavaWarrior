package com.adventuregames;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class MyGame extends Game {

    static public Skin gameSkin;

    public void create () {
        gameSkin = new Skin();
        this.setScreen(new GameDisplay(this));
    }

    public void render () {
        super.render();
    }


    public void dispose () {
    }
}

