package com.java.warrior.desktop;

import com.adventuregames.GameController;
import com.adventuregames.FightController;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;


public class DesktopLauncher {
	public static void main (String[] arg) {

		Player player = new Player("Manuela");
		Collection coll = new Collection(player);
		FightController fightController = new FightController(coll);
		GameController gameController = new GameController(fightController);

		gameController.choiceMainMenu();









	}
}
