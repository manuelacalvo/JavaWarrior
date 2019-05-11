package com.fighterlvl.warrior;


public class Armor {

  private   String name;
  private int type;
  private int protection;



  public Armor(String name, int type, int protection){
    this.protection = protection;
    this.name = name;
    this.type = type;

  }


  public int getProtection() {
    return protection;
  }

  public String getName() {
    return name;
  }

  public int getType()
  {
    return  type;
  }

  public void setProtection(int protection) {
    this.protection = protection;
  }

  public void setType(int type) {
    this.type = type;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString()
  {
    String def = " ";

    if(type == 1)
    {
      def = "You changed your armor to the one called " + type + "'\n'";

    }
    if(type == 2)
    {
      def = "You changed your shield to the one called " + type +  " \n";
    }
    return  def;
  }
}

