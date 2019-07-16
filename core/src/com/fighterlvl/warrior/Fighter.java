package com.fighterlvl.warrior;

import com.adventuregames.fight.FIGHT_PARTY;
import com.adventuregames.fight.event.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

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
    private Boolean timeToChoseAttack;
    private Boolean hadChoseAttack;
    private int choiceAttack;
    private FIGHT_PARTY party;
    private static FightEventPlayer eventPlayer ;

    private String str;


    //public static Fighter placeHolderFighter = new Fighter("???",null,null,null,0,0, JWAssetManager.path_fighterMystery,FIGHT_PARTY.OPPONENT);

    public Fighter(Fighter fighter)
    {
        this.name = fighter.getName();
        this.weapon = fighter.getWeapon();
        this.armor1 = fighter.getArmor1();
        this.armor2 = fighter.getArmor2();
        this.treasures = fighter.getTreasures();
        this.hitPoints = fighter.getHitPoints();
        this.maxHP = hitPoints;
        this.price = getPrice();
        this.str = " ";
        this.timeToChoseAttack = false;
        this.relativePathPicture = fighter.getRelativePathPicture();
        this.attacks = fighter.getAttacks();
        this.party=fighter.getParty(); // Default is OPPONENT
    }
    public Fighter(String name, Weapon weapon, Armor armor1,  ArrayList<Treasure> treasures, int hitPoints, int price, String relativePathPicture ) {
        this.name = name;
        this.weapon = weapon;
        this.armor1 = armor1;
        this.armor2 = null;
        this.treasures = treasures;
        this.hitPoints = hitPoints;
        this.maxHP = hitPoints;
        this.price = price;
        this.str = " ";
        this.timeToChoseAttack = false;
        this.relativePathPicture = relativePathPicture;
        this.attacks = new ArrayList<>();
        this.attacks.add(new Attack());
        this.hadChoseAttack = false;
        if(weapon!=null){
            this.attacks.add(weapon.getAttack());
        }
        this.party=FIGHT_PARTY.OPPONENT; // Default is OPPONENT
    }
    public Fighter(String name, Weapon weapon, Armor armor1,  ArrayList<Treasure> treasures, int hitPoints, int price, String relativePathPicture, FIGHT_PARTY eParty) {
        this(name, weapon, armor1, treasures, hitPoints, price, relativePathPicture);
        this.party=eParty;
    }

    public Fighter copyFigther(Fighter fighter)
    {
        Fighter fighterTest = new Fighter(fighter.getName(),fighter.getWeapon(), fighter.getArmor1(), fighter.getTreasures(), fighter.getHitPoints(), fighter.getPrice(), fighter.getRelativePathPicture(), fighter.getParty());
        return fighterTest;
    }

    public String getStr() {
        return str;
    }

    public String getName()
    {
        return  name;
    }


    public Weapon getWeapon() { return weapon; }
    public Armor getArmor1() { return armor1; }
    public Armor getArmor2() { return armor2; }
    public int getHitPoints(){
        return this.hitPoints;
    }
    public int getMaxHP(){
        return this.maxHP;
    }

    public Boolean getTimeToChoseAttack() {
        return timeToChoseAttack;
    }

    public Boolean getHadChoseAttack() {
        return hadChoseAttack;
    }

    public void setHadChoseAttack(Boolean hadChoseAttack) {
        this.hadChoseAttack = hadChoseAttack;
    }

    public void setChoiceAttack(int choiceAttack) {
        this.choiceAttack = choiceAttack;
    }



    public ArrayList<Treasure> getTreasures() { return treasures; }

    private boolean isRestOnce() { return restOnce; }

    private void setRestOnce(boolean restOnce) { this.restOnce = restOnce; }


    /**
     * Defense is the sum of armors points.
     * @return defense value
     */
    private int getDefense(){
        return (getArmor1()!=null ? getArmor1().getProtection():0)
                + (getArmor2()!=null ? getArmor2().getProtection():0);
    }
    public void setArmor1(Armor armor1) { this.armor1 = armor1; }
    public void setArmor2(Armor armor2) { this.armor2 = armor2; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    public void setTreasures(ArrayList<Treasure> treasures) { this.treasures = treasures; }

    public int getPrice() { return price; }

    public String getRelativePathPicture() { return relativePathPicture; }

    public ArrayList<Attack> getAttacks() { return attacks; }
    public void setAttacks(Attack attack) { this.attacks.add(attack); }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", hitPoints=" + hitPoints;
    }

    /**
     * Get a random number between two bounds
     *
     * @param min The minimum value
     * @param max The maximum value
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

    private void takeDamage(int hitPoints) { changeHitPoints(-hitPoints); }
    private void gainLife(int hitPoints){ changeHitPoints(hitPoints); }

    /**
     * Used internally to change hitpoints by takeDamage and gainLife
     * Modify maxHP Accordingly with EVENT
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



    public void setHitPoints(int iHitPoints){
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

    public void fightAttackBegin()
    {
        outPutText(this.name + "'s Turn \r\n\tLife : " + this.getHitPoints());


        outPutText( "Choose One Attack");

    }

    public void fight_attacks(Fighter enemy){
        outPutText(this.name + "'s Turn \r\n\tLife : " + this.getHitPoints());
        this.setRestOnce(false);
                int rand = getAttacks().get(this.choiceAttack).calculateImpact();
                outPutText("\tAttack : " + getAttacks().get(this.choiceAttack).toString() + "\nRand : " + rand+
                        "\nEnemy protection : " + enemy.getDefense());
                if (rand > enemy.getArmor1().getProtection()) {
                    int hitPower = randomNumberGenerator(getWeapon().getMinDamage(), getWeapon().getMaxDamage());
                    enemy.takeDamage(hitPower);
                    outPutText("\tHit : " + hitPower);
                    outPutText("Life of " + enemy.getName()+ " is " + enemy.getHitPoints()+"\r\n");
                } else {
                    enemy.takeDamage(0);
                    outPutText("This attack failed !");
                }

        System.out.println("Player : " + this.getName() + " isAlive : " + this.isAlive());

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
            enemy.attack(this); // TODO : if enemy is dead the turn should stop without him attacking
        }
        if(!this.isAlive())
        {
            outPutText("You loose");
        }
        if(!enemy.isAlive())
        {
            outPutText("You won");
            //getEnnemyRessources(enemy); FIXME Interraction
        }
    }

    /**
     * Displays a Victory/Defeat Message
     * @param enemy
     */
    public void fightTurnAttack(Fighter enemy)
    {
        if(!this.isAlive())
        {
          outPutText( "You loose");
        }
        if(!enemy.isAlive())
        {
            outPutText("You won");
            //getEnnemyRessources(enemy);
        }
    }

    /**
     * Output text to both Console and GUI
     * @param sText Text to display
     */
    private void outPutText(String sText){
        System.out.println(sText);
        queueEvent(new TextEvent(sText, true));
    }


    public void getEnnemyWeapon(Fighter enemy)
    {
                this.setWeapon(enemy.getWeapon());
    }

    public void getEnnemyArmor1(Fighter enemy) {

        this.setArmor1(enemy.getArmor1());
    }

    public void getEnnemyArmor2(Fighter enemy)
    {
                    this.setArmor2(enemy.getArmor2());

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
            this.gainLife(hitPoint);
            str = "You earn " + hitPoint + " points";

            str += "\n" + "Hit points" + this.hitPoints;
        }
        else str = "You don't have any potions ";

        return str;
    }

    public String useScroll()
    {
        String str = " ";
        int treasureIndex = TREASURE_TYPE.SCROLL.ordinal();
        if(this.getTreasures().get(treasureIndex).getNumber() > 0) {
            this.getTreasures().get(treasureIndex).setNumber(this.getTreasures().get(treasureIndex).getNumber() - 1);
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


    public static void setEventPlayer(FightEventPlayer oEventPlayer){
        eventPlayer = oEventPlayer;
    }
    @Override
    public void queueEvent(FightEvent event) {
        eventPlayer.queueEvent(event);
    }





}

