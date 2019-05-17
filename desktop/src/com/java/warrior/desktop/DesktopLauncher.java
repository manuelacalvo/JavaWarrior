package com.java.warrior.desktop;

import com.adventuregames.GameDisplay;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.adventuregames.GameDisplay;
import com.java.warrior.JavaWarrior;


public class DesktopLauncher {
	public static void main (String[] arg) {



		LwjglApplicationConfiguration config= new LwjglApplicationConfiguration();
		new LwjglApplication(new GameDisplay(), config);
		//new LwjglApplication(new JavaWarrior(), config);
	}
}
