package com.fighterlvl.warrior;



public abstract class Treasure extends AbstractItem {

  protected String name;
  protected int actionPoint;


  public Treasure(String name, int actionPoint){
    this.actionPoint = actionPoint;
    this.name = name;
  }

  public int getActionPoint() {
    return actionPoint;
  }

  public String getName() {
    return name;
  }

  public void setActionPoint(int actionPoint) {
    this.actionPoint = actionPoint;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public abstract String toString();
}