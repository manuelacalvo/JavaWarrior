package com.fighterlvl.warrior;

import com.adventuregames.fight.FIGHT_PARTY;
import com.adventuregames.fight.event.*;

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
    private int maxHP;
    private int price;
    private boolean restOnce;
    private ArrayList<Attack> attacks;
    private String relativePathPicture;
    private String thumbnailPath;
/*<<<<<<< HEAD
    private FightEventPlayer eventPlayer;

    public Fighter(String name, Weapon weapon, Armor armor1,  ArrayList<Treasure> treasures, int hitPoints, int price, String relativePathPicture ) {
=======*/
    private FIGHT_PARTY party;

    private static FightEventPlayer eventPlayer;

    /*
    Deprecated : All Fighter should have a thumbnail
     */
    public Fighter(String name, Weapon weapon, Armor armor1,  ArrayList<Treasure> treasures, int hitPoints, int price, String relativePathPicture ) {
//>>>>>>> fl_dev
        this.name = name;
        this.weapon = weapon;
        this.armor1 = armor1;
        this.armor2 = null;
        this.treasures = treasures;
        this.hitPoints = hitPoints;
        this.maxHP = hitPoints;
        this.price = price;
        this.relativePathPicture = relativePathPicture;
        this.attacks = new ArrayList<>();
        this.attacks.add(new Attack());
        this.attacks.add(weapon.getAttack());
        this.party=FIGHT_PARTY.OPPONENT; // Default is OPPONENT
    }
    public Fighter(String name, Weapon weapon, Armor armor1,  ArrayList<Treasure> treasures, int hitPoints, int price, String thumbnailPath, FIGHT_PARTY eParty) {
        this(name, weapon, armor1, treasures, hitPoints, price, thumbnailPath);
        this.party=FIGHT_PARTY.PLAYER;
    }

    public String getName()
    {
        return  name;
    }



    public Weapon getWeapon() {
        return weapon;
    }

    private Armor getArmor1() {
        return armor1;
    }

    private Armor getArmor2() {
        return armor2;
    }

    public int getHitPoints(){
        return this.hitPoints;
    }
    public int getMaxHP(){
        return this.maxHP;
    }

    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    private boolean isRestOnce() {
        return restOnce;
    }

    private void setRestOnce(boolean restOnce) {
        this.restOnce = restOnce;
    }

    /**
     * Defense is the sum of armors points.
     * @return defense value
     */
    private int getDefense(){
        return (getArmor1()!=null ? getArmor1().getProtection():0)
                + (getArmor2()!=null ? getArmor2().getProtection():0);
    }
    public void setArmor1(Armor armor1) {
        this.armor1 = armor1;
    }
    public void setArmor2(Armor armor2) {
        this.armor2 = armor2;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setTreasures(ArrayList<Treasure> treasures) {
        this.treasures = treasures;
    }

    public int getPrice() {
        return price;
    }

    public String getRelativePathPicture() {
        return relativePathPicture;
    }

    private ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(Attack attack) {
        this.attacks.add(attack);
    }

    @Override
    public String toString() {
        return "Fighter{" +
                "name='" + name + '\'' +
                ", weapon=" + weapon +
                ", armor1=" + armor1 +
                ", armor2=" + armor2 +
                ", treasures=" + treasures +
                ", hitPoints=" + hitPoints +
                ", maxHP=" + maxHP +
                ", price=" + price +
                ", restOnce=" + restOnce +
                ", attacks=" + attacks +
                ", relativePathPicture='" + relativePathPicture + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", party=" + party +
                '}';
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

    private void takeDamage(int hitPoints)
    {
        changeHitPoints(-hitPoints);
    }

    private void gainLife(int hitPoints){
        changeHitPoints(hitPoints);
    }

    /**
     * Used internally to change hitpoints by takeDamage and gainLife
     * Modify maxHP Accordingly
     * @param delta amont of HP added
     */
    private void changeHitPoints(int delta){

        int previousHP = this.getHitPoints();
        setHitPoints(getHitPoints()+delta);
        if(this.getHitPoints()>this.maxHP) maxHP = getHitPoints();

        this.queueEvent(new HPChangeEvent(
                this.getParty(),
                previousHP,
                getHitPoints(),
                getMaxHP(),
                0.5f));
    }

    private void setHitPoints(int iHitPoints){
        this.hitPoints = iHitPoints>0 && iHitPoints<=this.hitPoints ? iHitPoints:0;
    }


    public FIGHT_PARTY getParty(){ return this.party; }
    public void setParty(FIGHT_PARTY eParty){ this.party=eParty; }

    /**
     * Tells if Fighter is alive
     * @return true if Alive
     */
    public boolean isAlive() { return getHitPoints()>0 ; }

    /**
     * One turn of a fight between two Fighters
     * @method fight
     */
    private void attack(Fighter enemy){
        outPutText(this.name + "'s Turn \r\n\tLife : " + this.getHitPoints());
        this.setRestOnce(false);
        //Different attacks based on weapon's attacks per turn
        if(this.isAlive() && enemy.isAlive()) {

            for (int i = 0; i < this.getWeapon().getAttacksPerTurn(); i++) {
                outPutText("Attack " + (i+1));

                int rand = randomNumberGenerator(1, 20);
                outPutText("\tRandom : " + rand+
                        "\n\t\tEnemy protection : " + enemy.getDefense());
                if (rand > enemy.getDefense()) {
                    int hitPower = randomNumberGenerator(getWeapon().getMinDamage(), getWeapon().getMaxDamage());
                    outPutText("\tHit : " + hitPower);
                    enemy.takeDamage(hitPower);
                    outPutText("Life of " + enemy.getName()+ " is " + enemy.getHitPoints()+"\r\n");
                } else {
                    //enemy.takesDamage(0);
                    outPutText("This attack failed !");
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
                    enemy.takeDamage(hitPower);
                    System.out.println("\tHit : " + hitPower);
                    System.out.println("Life of " + enemy.getName()+ " is " + enemy.getHitPoints()+"\r\n");
                } else {
                    enemy.takeDamage(0);
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
    public void fight(Fighter enemy)
    {
        while(this.isAlive() && enemy.isAlive())
        {
            this.attack(enemy);
            enemy.attack(this);
        }
        if(!this.isAlive())
        {
            outPutText("You loose");
        }
        if(!enemy.isAlive())
        {
            outPutText("You won");
            getEnnemyRessources(enemy);
        }
    }

    public void fightTurnAtack(Fighter enemy)
    {
        int i=1;
        while(this.isAlive() && enemy.isAlive())
        {
            this.fight_attacks(enemy);
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

    private void outPutText(String sText){
        System.out.println(sText);
        queueEvent(new TextEvent(sText, true));
    }

    private void getEnnemyWeapon(Fighter enemy)
    {
        Scanner keyboard = new Scanner(System.in);

        if(enemy.weapon.isTakeable() && this.weapon.isBetter(enemy.weapon))
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

    private void getEnnemyArmor(Fighter enemy)
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

    private void getEnnemyTreasure(Fighter enemy)
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
                this.gainLife(random);
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
      int treasureIndex = TREASURE_TYPE.POTION.ordinal();
      String str = " ";
      if(this.getTreasures().get(treasureIndex).getNumber()> 0) {
          this.getTreasures().get(treasureIndex).setNumber(this.getTreasures().get(0).getNumber() - 1);

          int random = randomNumberGenerator(1,10), min, max, hitPoint;
          if (random <= 3) // 30%
          {
              min = 5 ; max = 10;
          } else if (random <= 7) //40%
          {
              min = 11; max = 20;
          } else if (random <= 9) //20%
          {
              min = 21; max = 30;
          } else //10%
          {
              min = 1; max = 20;
          }
          hitPoint = this.randomNumberGenerator(min, max);
          this.takeDamage(-hitPoint);
          str = "You earn " + hitPoint + " points";

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
                this.gainLife(this.getHitPoints());

            } else if (random == 2) {
                str = "The scroll do nothing sorry";
            } else if (random == 3) {
                str = "The scroll kill you... Too bad!!";
                this.takeDamage(this.getHitPoints());


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


    public void setEventPlayer(FightEventPlayer oEventPlayer){
        eventPlayer = oEventPlayer;
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

