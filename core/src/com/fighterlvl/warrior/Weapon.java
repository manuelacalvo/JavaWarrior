package com.fighterlvl.warrior;



public class Weapon {

  private String name;
  private int attacksPerTurn;
  private int minDamage;
  private int maxDamage;
  private  boolean takeable;


  public Weapon(String name, int attacksPerTurn, int minDamage, int maxDmamage, boolean takeable){
    this.name = name;
    this.takeable = takeable;
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

  public void setTakeable(boolean takeable) {
    this.takeable = takeable;
  }

  public boolean getTakeable()
  {
    return takeable;
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

  public double average()
  {
    double average;

    average = this.attacksPerTurn*((this.minDamage + this.maxDamage)/2);

    return  average;
  }

  public boolean isBetter(Weapon enemyWeapon)
  {
    boolean better;

        if(this.average() < enemyWeapon.average())
        {
          better = true;
        }else better = false;

    return better;
  }
}