package com.enumfile;

import com.screen.AbstractScreen;
import com.screen.AnimationScreen;
import com.screen.GameScreen;
import com.screen.LoadingScreen;

public enum SCREEN_TYPE {
    GAME(GameScreen.class),
    LOADING(LoadingScreen.class),
    ANIMATE(AnimationScreen .class);

    private final Class<? extends AbstractScreen> screenClass;

    SCREEN_TYPE(final Class<? extends AbstractScreen> screenClass){
        this.screenClass = screenClass;
    }

    public Class<? extends AbstractScreen> getScreenClass() {
        return screenClass;
    }
}
