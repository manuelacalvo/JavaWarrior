package Fighter_LVL;

import org.jetbrains.annotations.Contract;

public abstract class Treasure {

  protected String name;
  protected int actionPoint;

  @Contract(pure = true)
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