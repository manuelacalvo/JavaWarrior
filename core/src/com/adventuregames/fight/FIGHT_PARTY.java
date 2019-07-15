package com.adventuregames.fight;

import java.io.Serializable;

public enum FIGHT_PARTY implements Serializable {
    PLAYER,
    OPPONENT,
    ;

    public static FIGHT_PARTY getOpposite(FIGHT_PARTY party) {
        switch(party) {
            case PLAYER:
                return OPPONENT;
            case OPPONENT:
                return PLAYER;
            default:
                return null;
        }
    }
}