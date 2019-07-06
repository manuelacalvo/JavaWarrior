package com.shopmanagement;

import com.Display.CollectionFighterDisplay;
import com.fighterlvl.warrior.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Collection {
    private Player player;
    private Vector<Fighter> fighterVector;
    private Vector<Weapon> weaponVector;
    private Vector<Armor> armorVector ;
    private Vector<Treasure> treasureVector ;
    private CollectionFighterDisplay collectionDisplay;


    public Collection( Player player)
    {
        this.player = player;
        fighterVector =  new Vector<Fighter>();
        weaponVector = new Vector<Weapon>();
        armorVector = new Vector<Armor>();
        treasureVector= new Vector<Treasure>();
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

        File file = new File("fighters.csv");

        Scanner read_f = null;
        try {
            read_f = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("pas de fichier");

        }
        read_f.useDelimiter(Pattern.compile("\n"));
        String[] attributeNames;
        if(read_f.hasNextLine()){
            attributeNames = read_f.nextLine().split(";");
        } else {
            return;
        }

        List<Map<String, String>> fighters = new ArrayList<>();

        while (read_f.hasNextLine()) {
            String[] attributes = read_f.nextLine().split(";");
            Map<String, String> tmpFighter = new HashMap<>();
            if(attributeNames.length < attributes.length){
                System.err.println("GROSS ERREUR");
                return;
            }
            for(int i = 0; i < attributeNames.length; i++){
                tmpFighter.put(attributeNames[i], attributes[i]);
            }
            fighters.add(tmpFighter);

        }

        read_f.close();

        generateFighter(fighters);



    }

    public void generateFighter(List<Map<String, String>> fighters)
    {
        ArrayList<Treasure> treasures = new ArrayList<Treasure>();
        for(int i=0; i<fighters.size(); i++)
        {

            Weapon weapon = new Weapon(fighters.get(i).get("Weapon"),Integer.parseInt(fighters.get(i).get("AttackPerTurn")),Integer.parseInt(fighters.get(i).get("MinDamage")),Integer.parseInt(fighters.get(i).get("MaxDamage")), Boolean.parseBoolean(fighters.get(i).get("WeaponTackable")), Integer.parseInt(fighters.get(i).get("WeaponPrice")), fighters.get(i).get("WeaponPicture"));
            if(weapon.getTakeable()) {
                setWeaponVector(weapon);

            }
            Armor armor1 = new Armor(fighters.get(i).get("Armor1"), 1, Boolean.parseBoolean(fighters.get(i).get("Armor1Tackable")),Integer.parseInt(fighters.get(i).get("Protection1")), Integer.parseInt(fighters.get(i).get("Armor1Price")), fighters.get(i).get("Armor1Picture"));
            if(armor1.getTakeable())
            {
                setArmorVector(armor1);

            }


            Treasure potion = new Treasure("potion", 1, Integer.parseInt(fighters.get(i).get("Potion")), 15);
            treasures.add(potion);


            Treasure scroll = new Treasure("scroll", 2, Integer.parseInt(fighters.get(i).get("Scroll")), 25);
            treasures.add(scroll);

            Treasure gold = new Treasure("gold", 3, Integer.parseInt(fighters.get(i).get("Gold")), 0);
            treasures.add(gold);


            Fighter f = new Fighter(fighters.get(i).get("Name"), weapon, armor1, treasures, Integer.parseInt(fighters.get(i).get("HitPoints")), Integer.parseInt(fighters.get(i).get("FighterPrice")), fighters.get(i).get("FighterPicture"));


            if(fighters.get(i).get("Armor2") != "null")
            {
                Armor armor2= new Armor(fighters.get(i).get("Armor2"), 2, Boolean.parseBoolean(fighters.get(i).get("Armor2Tackable")), Integer.parseInt(fighters.get(i).get("Protection2")), Integer.parseInt(fighters.get(i).get("Armor2Price")), fighters.get(i).get("Armor2Picture"));
                f.setArmor2(armor2);
                if(armor2.getTakeable())
                {
                    setArmorVector(armor2);


                }


            }


            setFighterVector(f);
        }
        Treasure potion = new Treasure("potion", 1, 1, 15);
        Treasure scroll = new Treasure("scroll", 2, 1, 25);

        setTreasureVector(potion);
        setTreasureVector(scroll);

        player.setCollectionFighter(getFighterVector().get(0));
        player.setCollectionWeapon(getWeaponVector().get(0));
        player.setCollectionArmor(getArmorVector().get(0));
    }


    public String buyFighter(Fighter f)
    {
        String str = "";

        if(getPlayer().getMoney()>= f.getPrice())
        {
            str = "You buy a " + f.getName();
            getPlayer().reducePrice(f.getPrice());
            getPlayer().setCollectionFighter(f);
        }
        else str = "You can't buy it you don't have enough money";

        return  str;
    }

    public String buyArmor(Armor armor)
    {
        String str = "";
        if(getPlayer().getMoney()>= armor.getPrice())
        {
            str = "You buy a " + armor.getName();
            getPlayer().reducePrice(armor.getPrice());
            getPlayer().setCollectionArmor(armor);
        }
        else str = "You can't buy it you don't have enough money";

        return  str;
    }

    public String buyWeapon(Weapon weapon)
    {
        String str = "";
        if(getPlayer().getMoney()>= weapon.getPrice())
        {
           str = "You buy a " + weapon.getName();
            getPlayer().reducePrice(weapon.getPrice());
            getPlayer().setCollectionWeapon(weapon);
        }
        else str = "You can't buy it you don't have enough money";

        return str;
    }

    public String buyTreasure(Treasure treasure)
    {
        String str = "";
        if(getPlayer().getMoney()>= treasure.getPrice())
        {
            str = "You buy a " + treasure.getName();
            getPlayer().reducePrice(treasure.getPrice());
            getPlayer().setCollectionTreasure(treasure);
        }
        else str = "You can't buy it you don't have enough money";

        return str;
    }
}
