package com.fighterlvl.warrior;

import com.adventuregames.fight.event.FightEvent;
import com.adventuregames.fight.event.FightEventPlayer;
import com.adventuregames.fight.event.FightEventQueuer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Fighter implements FightEventQueuer, Serializable {

    private String name;
    private Weapon weapon;
    private Armor armor1;
    private Armor armor2;
    private ArrayList<Treasure> treasures;
    private int hitPoints;
    private int price;
    private boolean restOnce;
    private ArrayList<Attack> attacks;
    private String relativePathPicture;
    private String thumbnailPath;
    private FightEventPlayer eventPlayer;

    public Fighter(String name, Weapon weapon, Armor armor1,  ArrayList<Treasure> treasures, int hitPoints, int price, String relativePathPicture ) {
        this.name = name;
        this.weapon = weapon;
        this.armor1 = armor1;
        this.armor2 = null;
        this.treasures = treasures;
        this.hitPoints = hitPoints;
        this.price = price;
        this.relativePathPicture = relativePathPicture;
        this.attacks = new ArrayList<>();
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

    public int getHitPoints() {
        return hitPoints;
    }

    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    public boolean isRestOnce() {
        return restOnce;
    }

    public void setRestOnce(boolean restOnce) {
        this.restOnce = restOnce;
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

    public void setHitPoints(int hitPoints) {
        this.hitPoints -= hitPoints;
    }

    public void setTreasures(ArrayList<Treasure> treasures) {
        this.treasures = treasures;
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

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(Attack attack) {
        this.attacks.add(attack);
    }

    @Override
    public String toString() {
        return "Name :"+ this.getName() + '\n' + "HitPoints : " + this.getHitPoints();
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
        this.setHitPoints(points);
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
     * One turn of a fight between two Fighters
     * @method fight
     */
    public void fight(Fighter enemy){
        System.out.println(this.name + "'s Turn \r\n\tLife : " + this.getHitPoints());
        this.setRestOnce(false);
        //Different attacks based on weapon's attacks per turn
        if(this.isAlive() && enemy.isAlive()) {

            for (int i = 0; i < this.getWeapon().getAttacksPerTurn(); i++) {
                System.out.println("Attack " + (i+1));

                int rand = randomNumberGenerator(1, 20);
                System.out.println("\tRandom : " + rand+
                        "\t\tEnemy protection : " + enemy.getArmor1().getProtection());
                if (rand > enemy.getArmor1().getProtection()) {
                    int hitPower = randomNumberGenerator(getWeapon().getMinDamage(), getWeapon().getMaxDamage());
                    enemy.takesDamage(hitPower);
                    System.out.println("\tHit : " + hitPower);
                    System.out.println("Life of " + enemy.getName()+ " is " + enemy.getHitPoints()+"\r\n");
                } else {
                    enemy.takesDamage(0);
                    System.out.println("This attack failed !");
                }
            }
            System.out.println("=====================================================");
        }
    }

    public void fight_attacks(Fighter enemy){
        System.out.println(this.name + "'s Turn \r\n\tLife : " + this.getHitPoints());
        this.setRestOnce(false);
        //Different attacks based on weapon's attacks per turn
        if(this.isAlive() && enemy.isAlive()) {

            for (int i = 0; i < this.getWeapon().getAttacksPerTurn(); i++) {
                System.out.println("Attack " + (i+1));


                int rand = impactAttack();
                System.out.println("\tRandom : " + rand+
                        "\t\tEnemy protection : " + enemy.getArmor1().getProtection());
                if (rand > enemy.getArmor1().getProtection()) {
                    int hitPower = randomNumberGenerator(getWeapon().getMinDamage(), getWeapon().getMaxDamage());
                    enemy.takesDamage(hitPower);
                    System.out.println("\tHit : " + hitPower);
                    System.out.println("Life of " + enemy.getName()+ " is " + enemy.getHitPoints()+"\r\n");
                } else {
                    enemy.takesDamage(0);
                    System.out.println("This attack failed !");
                }
            }
            System.out.println("=====================================================");
        }
    }

    public int impactAttack()
    {
        System.out.println("You have " + getAttacks().size() + " attacks available : ");
        Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        int random = 0;
        for(int i=0; i<getAttacks().size(); i++)
        {
            System.out.println(i+". " + getAttacks().get(i). toString());
        }

        choice = keyboard.nextInt();

        random = getAttacks().get(choice).calculateImpact();

        return random;

    }
    /**
     * Engage the fight between 2 Fighters
     * @param enemy
     */
    public void fightTurn(Fighter enemy)
    {
        while(this.isAlive() && enemy.isAlive())
        {
            this.fight(enemy);
            enemy.fight_attacks(this);
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

        if(enemy.armor1.getTakeable() && this.armor1.isBetter(enemy.armor1)) {
            System.out.println(" You won, do you want to take your enemy's first armor? (1: take it 0: don't take it");
            System.out.println(enemy.armor1.toString());
            int choice = keyboard.nextInt();

            if (choice == 1) {
                System.out.println(" You change your first armor with success");
                this.setArmor1(enemy.getArmor1());
            }
            if (choice == 2) {
                System.out.println(" You choose to keep your first armor");
            }
        }
            if(enemy.getArmor2() != null && enemy.armor2.getTakeable() && this.armor2.isBetter(enemy.armor2))
            {

                System.out.println(" Do you want to take your enemy's second armor? (1: take it 0: don't take it");
                System.out.println(enemy.armor2.toString());
                int choice = keyboard.nextInt();

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

    public String takeARest(Fighter enemy)
    {
        String str = "";

        if(!this.isRestOnce()) {
            int attack = randomNumberGenerator(0, 1);

            if (attack == 0) {
                int random = randomNumberGenerator(11, 20);
                this.setHitPoints(-random);
                str = "You choose to rest and you regain " + random + "points";

            } else {
                str = " You choose to rest but your enemy attacked you, you don't regain any points... ";
                enemy.fight(this);
                str += "\n" + "You have " + hitPoints + " life points";
                this.setRestOnce(true);
            }
        }

        return str;
    }

    public String usePotion()
    {
        String str = " ";
        if(this.getTreasures().get(0).getNumber()> 0) {
            this.getTreasures().get(0).setNumber(this.getTreasures().get(0).getNumber() - 1);
            int random = (int) (Math.random() * (10 - 1)) + 1;
            int hitPoint;
            if (random < 4) // 30%
            {
                hitPoint = this.randomNumberGenerator(5, 10);
                this.setHitPoints(-hitPoint);
                str = "You earn " + hitPoint + " points";
            } else if (random < 8) //40%
            {
                hitPoint = this.randomNumberGenerator(11, 20);
                this.setHitPoints(-hitPoint);
                str = "You earn " + hitPoint + " points";
            } else if (random < 10) //20%
            {
                hitPoint = this.randomNumberGenerator(21, 30);
                this.setHitPoints(-hitPoint);
                str = "You earn " + hitPoint + " points";
            } else if (random < 11) //10%
            {
                hitPoint = this.randomNumberGenerator(1, 20);
                this.setHitPoints(hitPoint);
                str = "You loose " + hitPoint + " points";
            }

            str += "\n" + "Hit points" + this.hitPoints;
        }
        else str = "You don't have any potions ";

        return str;

    }

    public String useScroll()
    {
        String str = " ";

        if(this.getTreasures().get(1).getNumber() > 0) {
            this.getTreasures().get(1).setNumber(this.getTreasures().get(1).getNumber() - 1);
            int random = randomNumberGenerator(1, 3);

            if (random == 1) {
                str = "You double your points ";
                this.setHitPoints(-(this.getHitPoints() * 2));

            } else if (random == 2) {
                str = "The scroll do nothing sorry";
            } else if (random == 3) {
                str = "The scroll kill you... Too bad!!";
                this.setHitPoints(this.getHitPoints());


            }
        }
        else str = "You don't have scrolls";

        return  str;
    }

    public void takeItem()
    {
        int choice = 0;
        Scanner keyboard = new Scanner(System.in);

        while(this.treasures.get(0).getNumber() != 0 && choice != 2 && this.isAlive()) {
            System.out.println("You have " + this.treasures.get(0).getNumber() + " potions, do you want to use one? (1: take it 2: don't take it");
            choice =  keyboard.nextInt();

            if(choice == 1)
            {
                this.usePotion();
            }

        }
        choice = 0;
        while(this.treasures.get(1).getNumber() != 0 && choice != 2 && this.isAlive()) {
            System.out.println("You have " + this.treasures.get(1).getNumber() + " scroll, do you want to use one? (1: take it 2: don't take it");
            choice =  keyboard.nextInt();

            if(choice == 1)
            {
                this.useScroll();
            }
        }
    }



    public void setEventPlayer(FightEventPlayer pEventPlayer){
        this.eventPlayer = pEventPlayer;
    }
    @Override
    public void queueEvent(FightEvent event) {
        eventPlayer.queueEvent(event);
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }
    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}