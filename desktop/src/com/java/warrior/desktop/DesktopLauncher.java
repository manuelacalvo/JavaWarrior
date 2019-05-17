package com.java.warrior.desktop;

import com.adventuregames.GameController;
import com.adventuregames.GameDisplay;
import com.adventuregames.GameModel;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.adventuregames.GameDisplay;
import com.java.warrior.JavaWarrior;
import com.shopmanagement.Collection;


public class DesktopLauncher {
	public static void main (String[] arg) {


		Collection coll = new Collection();
		GameModel gameModel = new GameModel(coll);
		GameController gameController = new GameController(gameModel);



		LwjglApplicationConfiguration config= new LwjglApplicationConfiguration();
		new LwjglApplication(new GameDisplay(gameController), config);






		System.out.println("dehors");
	}
}
