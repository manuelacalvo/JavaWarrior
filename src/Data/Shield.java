package Data;

import Fighter_LVL.Armor;

public class Shield extends Armor {

  public Shield(String type, String legend, int protection){
    super(type, legend, protection);
  }

  @Override
  public String toString() {

    String def = "You changed your Shield to the one called " + type + '\n' + "\" " + legend + " \".";

    return def;
  }
}