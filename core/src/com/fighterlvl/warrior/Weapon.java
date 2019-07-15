package com.fighterlvl.warrior;


import java.io.Serializable;

public class Weapon implements Serializable {

  private String name;
  private int attacksPerTurn;
  private int minDamage;
  private int maxDamage;
  private boolean takeable;
  private int price;
  private String relativePathPicture;
  private Attack attack;


  public Weapon(String name, int attacksPerTurn, int minDamage, int maxDmamage, boolean takeable, int price, String relativePathPicture){
    this.name = name;
    this.takeable = takeable;
    this.attacksPerTurn = attacksPerTurn;
    this.minDamage = minDamage;
    this.maxDamage = maxDmamage;
    this.price = price;
    this.relativePathPicture = relativePathPicture;

  }

  public Attack getAttack() {
    return attack;
  }

  public void setAttack(Attack attack) {
    this.attack = attack;
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

  public boolean isTakeable()
  {
    return takeable;
  }

  int getAttacksPerTurn() {
    return attacksPerTurn;
  }

  int getMaxDamage() {
    return maxDamage;
  }

  int getMinDamage() {
    return minDamage;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getRelativePathPicture() {
    return relativePathPicture;
  }

  public void setRelativePathPicture(String relativePathPicture) {
    this.relativePathPicture = relativePathPicture;
  }

  @Override
  public String toString() {
    return "Weapon{" +
            "name='" + name + '\'' +
            ", attacksPerTurn=" + attacksPerTurn +
            ", minDamage=" + minDamage +
            ", maxDamage=" + maxDamage +
            ", takeable=" + takeable +
            ", price=" + price +
            '}';
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