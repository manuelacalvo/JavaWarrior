package com.adventuregames;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.fighterlvl.warrior.Player;


public class MyGame extends Game {

    static public Skin gameSkin;
    private Player player;

    public MyGame(Player player)
    {
        this.player = player;
    }

    public void create () {
        gameSkin = new Skin();
        this.setScreen(new GameDisplay(this, player));
    }

    public void render () {
        super.render();
    }


    public void dispose () {
    }
}

