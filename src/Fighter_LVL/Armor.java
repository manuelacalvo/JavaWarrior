package Fighter_LVL;

import org.jetbrains.annotations.Contract;

public abstract class Armor {

  protected String type;
  protected String legend;
  protected int protection;

  @Contract(pure = true)
  public Armor(String type, String legend, int protection){
    this.protection = protection;
    this.legend = legend;
    this.type = type;
  }

  public String getLegend() {
    return legend;
  }

  public void setLegend(String legend) {
    this.legend = legend;
  }

  public int getProtection() {
    return protection;
  }

  public String getName() {
    return type;
  }

  public void setProtection(int protection) {
    this.protection = protection;
  }

  public void setName(String type) {
    this.type = type;
  }

  @Override
  public abstract String toString();
}

