package Fighter_LVL;


public abstract class Armor extends AbstractItem {

  protected String type;
  protected String legend;
  protected int protection;


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

