package Data;

import Fighter_LVL.Treasure;

public class Scroll extends Treasure {

  public Scroll(String name, int actionPoint){
    super(name, actionPoint);
  }

  @Override
  public String toString() {

    String def = "";
    if (actionPoint > 0){
      def = "You used the scroll and double your strengths.";
    } else if (actionPoint == 0) {
      def = "You used the scroll and nothing happened" + '\n' + "Haaaaa that's just a piece of paper without any magic.";
    } else {
      def = "You used the scroll and died" + '\n' + "Mama told you to not accept stuff from everybody.";
    }

    return def;
  }
}