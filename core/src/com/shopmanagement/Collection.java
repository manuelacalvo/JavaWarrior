package com.shopmanagement;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fighterlvl.warrior.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

public class Collection {
    private Player player;
    private Vector<Fighter> fighterVector;
    private Vector<Weapon> weaponVector;
    private Vector<Armor> armorVector ;
    private Vector<Treasure> treasureVector ;
    private CollectionDisplay collectionDisplay;


    public Collection( Player player)
    {
        this.player = player;
        fighterVector =  new Vector<Fighter>();
        weaponVector = new Vector<Weapon>();
        armorVector = new Vector<Armor>();
        treasureVector= new Vector<Treasure>();
        //this.collectionDisplay = new CollectionDisplay();
    }

    public Player getPlayer() {
        return player;
    }

    public Vector<Fighter> getFighterVector() {
        return fighterVector;
    }

    public Vector<Armor> getArmorVector() {
        return armorVector;
    }

    public Vector<Weapon> getWeaponVector() {
        return weaponVector;
    }

    public Vector<Treasure> getTreasureVector() {
        return treasureVector;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setArmorVector(Armor armor) {
        this.armorVector.add(armor);
    }

    public void setFighterVector(Fighter fighter) {
        this.fighterVector.add(fighter);
    }

    public void setWeaponVector(Weapon weapon) {
        this.weaponVector.add(weapon);
    }

    public void setTreasureVector(Treasure treasure) {
        this.treasureVector.add(treasure);
    }

    public void loadFighters() {

        File file = new File("fighters.txt");

        Scanner read_f = null;
        try {
            read_f = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("pas de fichier");

        }
        read_f.useDelimiter(Pattern.compile("; "));

        while (read_f.hasNextLine()) {

            ArrayList<Treasure> treasures = new ArrayList<Treasure>();


            String fighterName = read_f.next();
            int hitPoints = Integer.parseInt(read_f.next());
            int priceFigter = Integer.parseInt(read_f.next());
            String weaponName = read_f.next();
            int attacksPerTurn = Integer.parseInt(read_f.next());
            int minDamage = Integer.parseInt(read_f.next());
            int maxDamage = Integer.parseInt(read_f.next());
            boolean takeableWeapon = Boolean.parseBoolean(read_f.next());
            int priceWeapon = Integer.parseInt(read_f.next());
            String nameArmor1 = read_f.next();
            int protection1 = Integer.parseInt(read_f.next());
            boolean takeable1 = Boolean.parseBoolean(read_f.next());
            int priceArmor1 = Integer.parseInt(read_f.next());
            String nameArmor2 = read_f.next();
            int protection2 = Integer.parseInt(read_f.next());
            boolean takeable2 = Boolean.parseBoolean(read_f.next());
            int priceArmor2 = Integer.parseInt(read_f.next());
            int potionNumber = Integer.parseInt(read_f.next());
            int scrollNumber = Integer.parseInt(read_f.next());
            int goldNumber = Integer.parseInt(read_f.next());
            int silverNumber = Integer.parseInt(read_f.next());



            Weapon weapon = new Weapon(weaponName,attacksPerTurn,minDamage,maxDamage, takeableWeapon, priceWeapon);
            setWeaponVector(weapon);
            Armor armor1 = new Armor(nameArmor1, 1, takeable1,protection1, priceArmor1);
            setArmorVector(armor1);



            Treasure potion = new Treasure("potion", 1, potionNumber, 15);
            treasures.add(potion);


            Treasure scroll = new Treasure("scroll", 2, scrollNumber, 25);
            treasures.add(scroll);

            Treasure gold = new Treasure("gold", 3, goldNumber, 0);
            treasures.add(gold);

            Treasure silver = new Treasure("silver", 4, silverNumber, 0);
            treasures.add(silver);


            Fighter f = new Fighter(fighterName, weapon, armor1, treasures, hitPoints, priceFigter);


            if(nameArmor2 != "null")
            {
                Armor armor2= new Armor(nameArmor2, 2, takeable2, protection2, priceArmor2);
                f.setArmor2(armor2);
                setArmorVector(armor2);

            }


            setFighterVector(f);

        }
        read_f.close();

        Treasure potion = new Treasure("potion", 1, 1, 15);
        Treasure scroll = new Treasure("scroll", 2, 1, 25);

        setTreasureVector(potion);
        setTreasureVector(scroll);

    }


    public void openShop()
    {
        int choice = 0;


        while(choice == 0) {

            choice = collectionDisplay.getChoice();

            switch (choice) {
                case 1:

                    buyFighter(fighterVector.get(1));

                    break;

                case 2:

                    buyFighter(fighterVector.get(2));
                    break;

                case 3:

                    buyFighter(fighterVector.get(3));
                    break;

                case 4 :

                    buyFighter(fighterVector.get(4));
                    break;

                case 5 :

                    buyFighter(fighterVector.get(5));
                    break;

            }
        }

    }
    public void buyFighter(Fighter f)
    {
        if(getPlayer().getMoney()> f.getPrice())
        {
            System.out.println("You buy a new fighter");
            getPlayer().setMoney(-f.getPrice());
            getPlayer().setCollectionFighter(f);
        }
    }

    public void buyArmor(Armor armor)
    {
        if(getPlayer().getMoney()> armor.getPrice())
        {
            System.out.println("You buy a new armor");
            getPlayer().setMoney(-armor.getPrice());
            getPlayer().setCollectionArmor(armor);
        }
    }

    public void buyWeapon(Weapon weapon)
    {
        if(getPlayer().getMoney()> weapon.getPrice())
        {
            System.out.println("You buy a new weapon");
            getPlayer().setMoney(-weapon.getPrice());
            getPlayer().setCollectionWeapon(weapon);
        }
    }

    public void buyTreasure(Treasure treasure)
    {
        if(getPlayer().getMoney()> treasure.getPrice())
        {
            System.out.println("You buy a new treasure");
            getPlayer().setMoney(-treasure.getPrice());
            getPlayer().setCollectionTreasure(treasure);
        }
    }
}
