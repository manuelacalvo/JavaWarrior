package com.adventuregames.fight;

public enum FIGHT_PARTY {
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