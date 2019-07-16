package com.Display;

import com.openworld.screen.AnimationScreen;
import com.openworld.screen.GameScreen;
import com.openworld.screen.LoadingScreen;
import com.badlogic.gdx.Screen;

public enum SCREEN_TYPE {
    GAME(GameScreen.class),
    LOADING(LoadingScreen.class),
    ANIMATE(AnimationScreen.class),
    ENTER_PLAYER(PlayerDisplay.class),
    CHARACTERSEL(AnimationScreen.CharacterSelScreen.class);

    private final Class<? extends Screen> screenClass;

    SCREEN_TYPE(final Class<? extends Screen> screenClass){
        this.screenClass = screenClass;
    }

    public Class<? extends Screen> getScreenClass() {
        return screenClass;
    }
}
