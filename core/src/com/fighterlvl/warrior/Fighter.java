package com.fighterlvl.warrior;

import java.util.Random;

public class Fighter extends AbstractItem {

    private Weapon weapon;
    private Armor armor;
    private Treasure treasures;
    private int hitPoints;

    /**
     * @param weapon
     * @param armor
     * @param treasures
     * @param hitPoints
     */
    public Fighter(Weapon weapon, Armor armor, Treasure treasures, int hitPoints) {
        this.weapon = weapon;
        this.armor = armor;
        this.treasures = treasures;
        this.hitPoints = hitPoints;
    }

    @Override
    public String toString() {
        return "Fighter{" + "weapon=" + weapon + ", armor=" + armor + ", treasures=" + treasures + ", hitPoints=" + hitPoints + '}';
    }

    /**
     * Get a random number between two bounds
     *
     * @param min The minimum value
     * @param max The maximum value
     * @method randomNumberGenerator
     */
    private int randomNumberGenerator(int min, int max) {
        //Maths.random VS Random.next()
        //https://stackoverflow.com/questions/738629/math-random-versus-random-nextintint
        Random rand = new Random();
        int span = max - min + 1;
        return (rand.nextInt(span) + min);

        /* TEST :
        for(int i=0;i<100;i++){
            System.out.println(f1.randomNumberGenerator(14,18));
        }
        */
    }

    /**
     * Engage a fight between 2 fighters
     * @method fight
     */
    /*
    public void fight(Fighter enemy){

        //Different attacks based on weapon's attacks per turn

        do{
            int rand = randomNumberGenerator(1, 20);
            if(rand>enemy.getDefense()){
                int hitPower = randomNumberGenerator(weapon.getMinDamage(), weapon.getMaxDamage());
                enemy.damage(hitPower);
            }
            else

        }while(notDead);
    }
    /*

     */
}
