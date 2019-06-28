package com.fighterlvl.warrior;


import java.util.ArrayList;

public class Player {
    private String name;
    private Fighter fighter;
    private Fighter ennemi;
    private ArrayList<Fighter> collectionFighter;
    private ArrayList<Weapon> collectionWeapon;
    private ArrayList<Armor> collectionArmor;
    private ArrayList<Treasure> collectionTreasure;
    private int money;

    public Player(String name)
    {
        this.name = name;
        this.fighter = null;
        this.collectionFighter = new ArrayList<Fighter>();
        this.collectionArmor = new ArrayList<Armor>();
        this.collectionTreasure = new ArrayList<Treasure>();
        this.collectionWeapon = new ArrayList<Weapon>();
        this.money = 6;
        this.ennemi = null;
    }

    public Player(String name, Fighter f, ArrayList<Fighter> collectionFighter, ArrayList<Weapon> collectionWeapon, ArrayList<Armor> collectionArmor, ArrayList<Treasure> collectionTreasure, int money)
    {
        this.name = name;
        this.fighter = f;
        this.collectionFighter = collectionFighter;
        this.collectionArmor = collectionArmor;
        this.collectionTreasure = collectionTreasure;
        this.collectionWeapon = collectionWeapon;
        this.money = money;
        this.ennemi = f;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public Fighter getEnnemi() {
        return ennemi;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public ArrayList<Fighter> getCollectionFighter() {
        return collectionFighter;
    }

    public ArrayList<Armor> getCollectionArmor() {
        return collectionArmor;
    }

    public ArrayList<Treasure> getCollectionTreasure() {
        return collectionTreasure;
    }

    public ArrayList<Weapon> getCollectionWeapon() {
        return collectionWeapon;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

    public void setCollectionFighter(Fighter f) {
        this.collectionFighter.add(f);
    }

    public void setCollectionArmor(Armor armor) {
        this.collectionArmor.add(armor);
    }

    public void setCollectionWeapon(Weapon weapon) {
        this.collectionWeapon.add(weapon);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCollectionTreasure(Treasure treasure) {
        this.collectionTreasure.add(treasure);
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void reducePrice(int price)
    {
        this.money -= price;
    }

    public void setEnnemi(Fighter ennemi) {
        this.ennemi = ennemi;
    }


}
