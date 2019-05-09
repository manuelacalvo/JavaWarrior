package com.fighterlvl.warrior;



public class Weapon extends AbstractItem {

  private String name;
  private String type;
  private int attacksPerTurn;
  private int minDamage;
  private int maxDamage;


  public Weapon(String name,String type, int attacksPerTurn, int minDamage, int maxDmamage){
    this.name = name;
    this.type = type;
    this.attacksPerTurn = attacksPerTurn;
    this.minDamage = minDamage;
    this.maxDamage = maxDmamage;
  }

  public void setType(String type) {
    this.type = type;
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

  public String getType() {
    return type;
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