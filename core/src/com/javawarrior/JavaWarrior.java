package com.javawarrior;

//import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.screen.GameScreen;

/*
public class JavaWarrior extends ApplicationAdapter{}

!!!!!!!!!!! IMPORTANT TO CHECK !!!!!!!!!!
*/

public class JavaWarrior extends Game {

	private GameScreen screen;

	/*
	Manage Tileset
	*/
	private AssetManager assetManager;

	@Override
	public void create() {
		assetManager = new AssetManager();
		/*
		manage Tileset instead of tile
		*/
		assetManager.load("RessourcesTileset/textures.atlas", TextureAtlas.class);
		assetManager.finishLoading();
		screen = new GameScreen(this);
		this.setScreen(screen);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}
}
