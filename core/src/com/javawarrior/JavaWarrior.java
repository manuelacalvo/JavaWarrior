package com.javawarrior;

//import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.screen.GameScreen;

/*
public class JavaWarrior extends ApplicationAdapter{}

!!!!!!!!!!! IMPORTANT TO CHECK !!!!!!!!!!
*/

public class JavaWarrior extends Game {

	@Override
	public void create() {
		screen = new GameScreen(this);
		this.setScreen(screen);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
}
