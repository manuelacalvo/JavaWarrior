package com.idea;

import com.javawarrior.JavaWarrior;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {

		/*
		Here we create the screen.
		we add a title, a screen size and enable vSyn* (specific to a special amount of screen).
		*/
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "Game";
        config.height = 400;
        config.width = 600;
        config.vSyncEnabled = true;
        new LwjglApplication(new JavaWarrior(), config);
    }
}