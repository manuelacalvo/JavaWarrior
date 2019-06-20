package com.java.warrior.desktop;


import com.adventuregames.FightController;
import com.adventuregames.MyGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;


public class DesktopLauncher {
	public static void main (String[] arg) {

		Player player = new Player("Manuela");
		Collection coll = new Collection(player);
		FightController fightController = new FightController(coll);
		fightController.shopOpen();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGame(), config);


	}
}
