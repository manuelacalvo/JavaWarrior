package com.Display;

import com.openworld.screen.AnimationScreen;
import com.openworld.screen.GameScreen;
import com.openworld.screen.LoadingScreen;

public enum SCREEN_TYPE {
    GAME(GameScreen.class),
    LOADING(LoadingScreen.class),
    ANIMATE(AnimationScreen.class);

    private final Class<? extends AbstractScreen> screenClass;

    SCREEN_TYPE(final Class<? extends AbstractScreen> screenClass){
        this.screenClass = screenClass;
    }

    public Class<? extends AbstractScreen> getScreenClass() {
        return screenClass;
    }
}
