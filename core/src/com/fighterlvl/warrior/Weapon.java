package com.fighterlvl.warrior;

public class Weapon {

  private String name;
  private int attacksPerTurn;
  private int minDamage;
  private int maxDamage;


  public Weapon(String name, int attacksPerTurn, int minDamage, int maxDmamage) {
    this.name = name;

    this.attacksPerTurn = attacksPerTurn;
    this.minDamage = minDamage;
    this.maxDamage = maxDmamage;
  }


  public void setName(String name) {
    this.name = name;
  }

  public void setAttacksPerTurn(int attacksPerTurn) {
    this.attacksPerTurn = attacksPerTurn;
  }

  public void setMaxDamage(int maxDamage) {
    this.maxDamage = maxDamage;
  }

  public void setMinDamage(int minDamage) {
    this.minDamage = minDamage;
  }

  public String getName() {
    return name;
  }

  public int getAttacksPerTurn() {
    return attacksPerTurn;
  }

  public int getMaxDamage() {
    return maxDamage;
  }

  public int getMinDamage() {
    return minDamage;
  }

  @Override
  public String toString() {
    String def = " this is a weapon";
    return def;
  }
}