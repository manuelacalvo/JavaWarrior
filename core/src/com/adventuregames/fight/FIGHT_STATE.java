package com.adventuregames.fight;

public enum FIGHT_STATE{
    WAITING, // FIGHT NOT ENGAGED
    READY_TO_ACTION,//While the fight is engaged
    SELECT_NEW_FIGHTER,
    SELECT_ACTION, //choose what to do after victory
    DEACTIVATED, // Do nothing
    ;
}