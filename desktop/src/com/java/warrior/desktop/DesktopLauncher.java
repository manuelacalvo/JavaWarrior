package com.java.warrior.desktop;

import com.adventuregames.GameController;
import com.adventuregames.GameDisplay;
import com.adventuregames.GameModel;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.adventuregames.GameDisplay;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;


public class DesktopLauncher {
	public static void main (String[] arg) {

		Player player = new Player("Manuela");
		Collection coll = new Collection(player);
		GameModel gameModel = new GameModel(coll);
		GameController gameController = new GameController(gameModel);



		//LwjglApplicationConfiguration config= new LwjglApplicationConfiguration();
		//new LwjglApplication(new GameDisplay(gameController), config);







	}
}
