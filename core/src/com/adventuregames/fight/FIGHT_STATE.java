package com.adventuregames.fight;

import java.io.Serializable;

public enum FIGHT_STATE implements Serializable {
    WAITING, // FIGHT NOT ENGAGED
    READY_TO_ACTION,//While the fight is engaged
    SELECT_NEW_FIGHTER,
    SELECT_ACTION, //choose what to do after victory
    DEACTIVATED, // Do nothing
    ;
}