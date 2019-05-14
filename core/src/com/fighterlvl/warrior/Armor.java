package com.fighterlvl.warrior;


public class Armor {

  private   String name;
  private int type;
  private boolean takeable;
  private int protection;



  public Armor(String name, int type, boolean takeable, int protection){
    this.protection = protection;
    this.name = name;
    this.type = type;
    this.takeable = takeable;

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

  public void setTakeable (boolean takeable)
  {
      this.takeable = takeable;
  }
  public boolean getTakeable()
  {
      return takeable;
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

    public boolean isBetter(Armor enemyArmor)
    {
        boolean better;

        if(this.protection < enemyArmor.protection)
        {
            better = true;
        }else better = false;

        return better;
    }
}

