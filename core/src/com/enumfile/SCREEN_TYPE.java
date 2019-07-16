package com.enumfile;

import com.screen.*;

public enum SCREEN_TYPE {
    GAME(GameScreen.class),
    LOADING(LoadingScreen.class),
    CHARACTERSEL(CharacterSelScreen.class),
    ANIMATE(AnimationScreen .class);

    private final Class<? extends AbstractScreen> screenClass;

    SCREEN_TYPE(final Class<? extends AbstractScreen> screenClass){
        this.screenClass = screenClass;
    }

    public Class<? extends AbstractScreen> getScreenClass() {
        return screenClass;
    }
}
