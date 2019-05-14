package com.fighterlvl.warrior;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


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

    public String getName()
    {
        return  name;
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

    public void takesDamage( int points)
    {
        this.hitPoints -= points;
    }
    public boolean isAlive()
    {
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
        System.out.println("Turn of " + this.name);
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
                    System.out.println("Life of " + enemy.getName()+ " is " + enemy.getHitPoints());
                } else enemy.takesDamage(0);


            }
        }


    }
    public void fightTurn(Fighter enemy)
    {
        while(this.isAlive() && enemy.isAlive())
        {
            this.fight(enemy);
            enemy.fight(this);
        }
        if(!this.isAlive())
        {
            System.out.println("You loose");
        }
        if(!enemy.isAlive())
        {
            System.out.println("You won");
            getEnnemyRessources(enemy);
        }
    }

    public void getEnnemyWeapon(Fighter enemy)
    {
        Scanner keyboard = new Scanner(System.in);

        if(enemy.weapon.getTakeable() && this.weapon.isBetter(enemy.weapon))
        {
            System.out.println(" You won, do you want to take your enemy's weapon? (1: take it 0: don't take it");
            System.out.println(enemy.weapon.toString());
            int choice = keyboard.nextInt();

            if(choice == 1)
            {
                System.out.println(" You change your weapon with success");
                this.setWeapon(enemy.getWeapon());
            }
            if(choice == 2)
            {
                System.out.println(" You choose to keep your weapon");
            }
        }
    }

    public void getEnnemyArmor(Fighter enemy)
    {
        Scanner keyboard = new Scanner(System.in);

        if(enemy.armor1.getTakeable() && this.armor1.isBetter(enemy.armor1))
        {
            System.out.println(" You won, do you want to take your enemy's first armor? (1: take it 0: don't take it");
            System.out.println(enemy.armor1.toString());
            int choice = keyboard.nextInt();

            if(choice == 1)
            {
                System.out.println(" You change your first armor with success");
                this.setArmor1(enemy.getArmor1());
            }
            if(choice == 2)
            {
                System.out.println(" You choose to keep your first armor");
            }

            if(enemy.getArmor2() != null)
            {
                System.out.println(" Do you want to take your enemy's second armor? (1: take it 0: don't take it");
                System.out.println(enemy.armor2.toString());
                choice = keyboard.nextInt();

                if(choice == 1)
                {
                    System.out.println(" You change your second armor with success");
                    this.setArmor2(enemy.getArmor2());
                }
                if(choice == 2)
                {
                    System.out.println(" You choose to keep your second armor");
                }
            }
        }
    }

    public void getEnnemyTreasure(Fighter enemy)
    {

        for(int j= 0; j<this.getTreasures().size(); j++) {

            for (int i = 0; i < enemy.getTreasures().size(); i++) {

                if(this.getTreasures().get(j).getName() == enemy.getTreasures().get(i).getName())
                {
                    this.getTreasures().get(j).setNumber(this.getTreasures().get(j).getNumber() + 1);
                }
            }
        }

    }


    public void  getEnnemyRessources(Fighter enemy)
    {
        getEnnemyWeapon(enemy);
        getEnnemyArmor(enemy);
        getEnnemyTreasure(enemy);
    }

    public void takeARest(Fighter enemy)
    {
        int attack = randomNumberGenerator(0, 1);

            if(attack == 0)
            {
                int random = randomNumberGenerator(11, 20);
                this.setHitPoints(-random);
                System.out.println("You choose to rest and you regain " + random + "points");
            }else
            {
                System.out.println("You choose to rest but your enemy attacked you, you don't regain any points... ");
                enemy.fight(this);
            }

    }

    public void usePotion()
    {
        int random =  (int)(Math.random() * (10-1)) + 1;
        int hitPoint;
        if(random < 4) // 30%
        {
            hitPoint = this.randomNumberGenerator(5, 10);
            this.setHitPoints(-hitPoint);
            System.out.println("You earn " + hitPoint + " points");
        }
        else if(random < 8) //40%
        {
            hitPoint = this.randomNumberGenerator(11, 20);
            this.setHitPoints(-hitPoint);
            System.out.println("You earn " + hitPoint + " points");
        }
        else if(random < 10) //20%
        {
            hitPoint = this.randomNumberGenerator(21, 30);
            this.setHitPoints(-hitPoint);
            System.out.println("You earn " + hitPoint + " points");
        }
        else if(random < 11) //10%
        {
            hitPoint = this.randomNumberGenerator(1, 20);
            this.setHitPoints(hitPoint);
            System.out.println("You loose " + hitPoint + " points");
        }

    }

    public void useScroll()
    {
        int random = randomNumberGenerator(1,3);

        if(random == 1)
        {
            System.out.println("You double your points ");
            this.setHitPoints(-(this.getHitPoints()*2));

        }
        else if(random == 2)
        {
            System.out.println("The scroll do nothing sorry");
        }
        else if(random == 3)
        {
            System.out.println("The scroll kill you... Too bad!!");
            this.setHitPoints(0);

        }
    }

    public void takeItem()
    {
        int choice = 0;
        Scanner keyboard = new Scanner(System.in);

        if(this.treasures.get(0).getNumber() != 0) {
            System.out.println("You have " + this.treasures.get(0).getNumber() + " potions, do you want to use one? (1: take it 2: don't take it");
            choice =  keyboard.nextInt();

            if(choice == 1)
            {
                this.usePotion();
            }
        }

        if(this.treasures.get(1).getNumber() != 0) {
            System.out.println("You have " + this.treasures.get(1).getNumber() + " scroll, do you want to use one? (1: take it 2: don't take it");
            choice =  keyboard.nextInt();

            if(choice == 1)
            {
                this.useScroll();
            }
        }
    }

}
