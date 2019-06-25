package com.adventuregames;

import com.Display.GameDisplay;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;


public class MyGame extends Game {

    static public Skin gameSkin;
    private Player player;
    private Collection coll;


    public MyGame(Player player, Collection coll)
    {
        this.player = player;
        this.coll = coll;
    }


    public void create () {
        gameSkin = new Skin();
        this.setScreen(new GameDisplay(this, player, coll));
    }

    public void render () {
        super.render();
    }


    public void dispose () {
    }
}

