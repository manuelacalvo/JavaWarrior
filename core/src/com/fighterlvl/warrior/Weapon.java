package com.fighterlvl.warrior;



public class Weapon {

  private String name;
  private int attacksPerTurn;
  private int minDamage;
  private int maxDamage;
  private  boolean takeable;
  private int price;


  public Weapon(String name, int attacksPerTurn, int minDamage, int maxDmamage, boolean takeable, int price){
    this.name = name;
    this.takeable = takeable;
    this.attacksPerTurn = attacksPerTurn;
    this.minDamage = minDamage;
    this.maxDamage = maxDmamage;
    this.price = price;
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