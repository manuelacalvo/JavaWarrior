package com.fighterlvl.warrior;


import java.io.*;
import java.util.ArrayList;

public class Player implements Serializable {
    private String name;
    private Fighter fighter;
    private Fighter ennemi;
    private ArrayList<Fighter> collectionFighter;
    private ArrayList<Weapon> collectionWeapon;
    private ArrayList<Armor> collectionArmor;
    private ArrayList<Treasure> collectionTreasure;
    private int money;
    private int nbFights;

    public Player(String name)
    {
        this.name = name;
        this.fighter = null;
        this.collectionFighter = new ArrayList<Fighter>();
        this.collectionArmor = new ArrayList<Armor>();
        this.collectionTreasure = new ArrayList<Treasure>();
        this.collectionWeapon = new ArrayList<Weapon>();
        this.money = 12;
        this.ennemi = null;
        this.nbFights = 0;
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
        this.nbFights = 0;
    }

    public Player(Player player)
    {
        this.name = player.getName();
        this.fighter = player.getFighter();
        this.collectionFighter =player.getCollectionFighter();
        this.collectionArmor = player.getCollectionArmor();
        this.collectionTreasure = player.getCollectionTreasure();
        this.collectionWeapon = player.getCollectionWeapon();
        this.money = player.getMoney();
        this.ennemi = null;
        this.nbFights = 0;
    }

    public void setNbFights(int nbFights) {
        this.nbFights = nbFights;
    }

    public int getNbFights() {
        return nbFights;
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

    public void selectFighter(Fighter fighter, Weapon weapon, Armor armor1, Armor armor2)
    {
        setFighter(fighter);
        getFighter().setWeapon(weapon);
        getFighter().setArmor1(armor1);
        getFighter().setArmor2(armor2);
    }


    public void save()
    {

        try {
            FileOutputStream fos;
            fos = new FileOutputStream(getName().hashCode() + ".txt");

            ObjectOutputStream oos;
            oos = new ObjectOutputStream(fos);

            oos.writeObject(this);

            oos.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }



}
