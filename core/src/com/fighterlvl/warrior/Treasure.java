package com.fighterlvl.warrior;

import java.io.Serializable;

public class Treasure implements Serializable {


  private String name;
  private int type;
  private int number;
  private int actionPoint;
  private int price;

  public Treasure(String name, int type, int number, int price) {


    this.name = name;
    this.number = number;
    this.type = type;
    this.actionPoint = 0;
    this.price = price;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getType() {
    return type;
  }

  public int getNumber() {
    return number;
  }

  public void setType(int type) {
    this.type = type;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public int getActionPoint() {
    return actionPoint;
  }

  public void setActionPoint(int actionPoint) {
    this.actionPoint = actionPoint;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return "Name : " + this.getName();
  }
}
