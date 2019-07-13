package com.fighterlvl.warrior;


import java.io.Serializable;

public class Armor implements Serializable {

  private String name;
  private int type;
  private boolean takeable;
  private int protection;
  private int price;
  private String relativePathPicture;

  public Armor()
  {
    this.protection = 0;
    this.name = null;
    this.type = 0;
    this.takeable = false;
    this.price = 0;
    this.relativePathPicture = "core/assets/graphics/items/no.png";

  }

  public Armor(String name, int type, boolean takeable, int protection, int price, String relativePathPicture){
    this.protection = protection;
    this.name = name;
    this.type = type;
    this.takeable = takeable;
    this.price = price;
    this.relativePathPicture = relativePathPicture;

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
  public String toString()
  {
    return  "Name : " + this.getName() + '\n' + "Protection : " + this.getProtection();
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

