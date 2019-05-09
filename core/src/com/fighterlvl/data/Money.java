package com.fighterlvl.data;

import com.fighterlvl.warrior.Treasure;

public class Money extends Treasure {

  public Money(String name, int actionPoint){
    super(name, actionPoint);
  }

  @Override
  public String toString() {

    String def = "You won "+ actionPoint + " " + name +".";

    return def;
  }
}
