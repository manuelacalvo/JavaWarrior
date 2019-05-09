package Data;

import Fighter_LVL.Treasure;

public class Potion extends Treasure {

  public Potion(String name, int actionPoint){
    super(name, actionPoint);
  }

  @Override
  public String toString() {

    String def = "";
    if (actionPoint > 0){
      def = "You used the potion and won " + actionPoint + " life points.";
    } else if (actionPoint == 0) {
      def = "You used the potion and nothing happened" + '\n' + "You shouldn't have shaken it that much.";
    } else {
      def = "You used the potion and lose " + actionPoint + " life points" + '\n' + "That is certainly because you didn't put it to the fridge yesterday.";
    }

    return def;
  }
}