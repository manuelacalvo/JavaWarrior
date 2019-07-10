package com.fighterlvl.warrior;

public class Treasure {

  private String name;
  private int type;
  private int number;
  private int actionPoint;
  private int price;

  public Treasure(String name, int type, int number, int price){

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
  public String toString()
  {
    String def = " ";

    if(this.type == 1)
    {
      if (actionPoint > 0){
        def = "You used the potion and won " + actionPoint + " life points.";
      } else if (actionPoint == 0) {
        def = "You used the potion and nothing happened" + '\n' + "You shouldn't have shaken it that much.";
      } else {
        def = "You used the potion and lose " + actionPoint + " life points" + '\n' + "That is certainly because you didn't put it to the fridge yesterday.";
      }

    }
    if(this.type == 2)
    {
      if (actionPoint > 0){
        def = "You used the scroll and double your strengths.";
      } else if (actionPoint == 0) {
        def = "You used the scroll and nothing happened" + '\n' + "Haaaaa that's just a piece of paper without any magic.";
      } else {
        def = "You used the scroll and died" + '\n' + "Mama told you to not accept stuff from everybody.";
      }
    }
    if(this.type == 3 || this.type == 4)
    {
      def = "You won "+ number + " " + name +".";
    }
    return def;
  }
}