package com.openworld.screen;

import com.javawarrior.JavaWarrior;
import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {

  protected final JavaWarrior context;

  public AbstractScreen(JavaWarrior app) { this.context = app; }

  @Override
  public abstract void show();
  @Override
  public abstract void render(float delta);
  @Override
  public abstract void resize(final int width,final int height);
  @Override
  public abstract void pause();
  @Override
  public abstract void resume();
  @Override
  public abstract void hide();
  @Override
  public abstract void dispose();
}
