package com.java.warrior.desktop;

import com.adventuregames.fight.FIGHT_PARTY;
import com.adventuregames.fight.FightScreen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Player;
import com.fighterlvl.warrior.Treasure;
import com.javawarrior.JWGame;
import com.shopmanagement.Collection;

import java.util.ArrayList;

/**
 * This class is used to test FighterScreen Class with test player and fighter
 */
public class FighterScreenTest {

    public static void main(String[] args){


        Player player = new Player("Testor");
        Collection collec = new Collection(player);
        collec.loadFighters();

         player.setFighter(new Fighter("FighterN1",
                collec.getWeaponVector().firstElement(),
                collec.getArmor1Vector().firstElement(),
                new ArrayList<Treasure>(),
                30,0,"core/assets/graphics/fighter_picture/User.jpg",
                 FIGHT_PARTY.PLAYER));

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new JWGame(FightScreen.class), config);
    }
}
