package com.adventuregames.fight;

public enum FIGHT_STATE{
    WAITING, // FIGHT NOT ENGAGED
    FIGHTING, //While the fight is engaged
    SELECT_ACTION, //choose what to do after victory
    DEACTIVATED // Do nothing
    ;
}