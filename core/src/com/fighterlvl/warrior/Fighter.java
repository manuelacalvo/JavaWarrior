package com.fighterlvl.warrior;

import java.util.ArrayList;
import java.util.Random;

public class Fighter {

    private String name;
    private Weapon weapon;
    private Armor armor1;
    private Armor armor2;
    private ArrayList<Treasure> treasures;
    private int hitPoints;
    private int generator;


    public Fighter(String name, Weapon weapon, Armor armor1,  ArrayList<Treasure> treasures, int hitPoints, int generator ) {
        this.name = name;
        this.weapon = weapon;
        this.armor1 = armor1;
        this.armor2 = null;
        this.treasures = treasures;
        this.hitPoints = hitPoints;
        this.generator = generator;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor1() {
        return armor1;
    }

    public Armor getArmor2() {
        return armor2;
    }

    public int getGenerator() {
        return generator;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setArmor1(Armor armor1) {
        this.armor1 = armor1;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setArmor2(Armor armor2) {
        this.armor2 = armor2;
    }

    public void setGenerator(int generator) {
        this.generator = generator;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setTreasures(ArrayList<Treasure> treasures) {
        this.treasures = treasures;
    }


    @Override
    public String toString() {
        return "Fighter{" + "weapon=" + weapon + ", armor 1 =" + armor1 + ", armor 2 =" + armor2 + ", treasures=" + treasures + ", hitPoints=" + hitPoints + '}';
    }


    /**
     * Get a random number between two bounds
     *
     * @param min The minimum value
     * @param max The maximum value
     * @method randomNumberGenerator
     */
    public int randomNumberGenerator(int min, int max) {
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

    public void takesDamage( int points) {
        this.hitPoints -= points;
    }

    public boolean isAlive() {
        boolean alive;
        if (getHitPoints()>0)
        {
            alive = true;
        } else alive = false;

        return alive;
    }

    /**
     * Engage a fight between 2 fighters
     * @method fight
     */

    public void fight(Fighter enemy){
        System.out.println("Tour de " + this.name);
        //Different attacks based on weapon's attacks per turn
        if(this.isAlive() && enemy.isAlive()) {

            for (int i = 0; i < this.getWeapon().getAttacksPerTurn(); i++) {
                System.out.println("Attack " + (i+1));

                int rand = randomNumberGenerator(1, 20);
                System.out.println("Random : " + rand + " Protection : " + enemy.getArmor1().getProtection());
                if (rand > enemy.getArmor1().getProtection()) {
                    int hitPower = randomNumberGenerator(getWeapon().getMinDamage(), getWeapon().getMaxDamage());
                    System.out.println("hit : " + hitPower);
                    enemy.takesDamage(hitPower);
                    System.out.println("Vie de " + enemy.getName()+ " est de " + enemy.getHitPoints());
                } else enemy.takesDamage(0);
            }
        }
    }

    public void fightTurn(Fighter enemy) {
        while(this.isAlive() && enemy.isAlive()) {
            this.fight(enemy);
            enemy.fight(this);
        }
        if(!this.isAlive()) {
            System.out.println("vous avez perdu");
        }
        if(!enemy.isAlive()) {
            System.out.println("vous avez gagné");
        }
    }
}
