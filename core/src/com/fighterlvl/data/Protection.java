package com.fighterlvl.data;

import com.fighterlvl.warrior.Armor;

public class Protection extends Armor {

  public Protection (String type, String legend, int protection){
    super(type, legend, protection);
  }

  @Override
  public String toString() {

    String def = "You changed your armor to the one called " + type + '\n' + "\" " + legend + " \".";

    return def;
  }
}
