package com.shopmanagement;

import com.shopmanagement.CollectionDisplay.CollectionFighterDisplay;
import com.fighterlvl.warrior.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Collection {
    private Player player;
    private Vector<Fighter> fighterVector;
    private Vector<Weapon> weaponVector;
    private Vector<Armor> armor1Vector ;
    private Vector<Armor> armor2Vector ;
    private Vector<Treasure> treasureVector ;
    private ArrayList<Attack> fighterAttack;
    private ArrayList<Attack> weaponAttack;


    public Collection( Player player)
    {
        this.player = player;
        fighterVector =  new Vector<Fighter>();
        weaponVector = new Vector<Weapon>();
        armor1Vector = new Vector<Armor>();
        armor2Vector = new Vector<Armor>();
        treasureVector= new Vector<Treasure>();
        fighterAttack = new ArrayList<>();
        weaponAttack = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public Vector<Fighter> getFighterVector() {
        return fighterVector;
    }

    public Vector<Armor> getArmor1Vector() {
        return armor1Vector;
    }

    public Vector<Armor> getArmor2Vector() {
        return armor2Vector;
    }

    public Vector<Weapon> getWeaponVector() {
        return weaponVector;
    }

    public Vector<Treasure> getTreasureVector() {
        return treasureVector;
    }

    public ArrayList<Attack> getFighterAttack() {
        return fighterAttack;
    }

    public ArrayList<Attack> getWeaponAttack() {
        return weaponAttack;
    }

    public void setTreasureVector(Vector<Treasure> treasureVector) {
        this.treasureVector = treasureVector;
    }

    public void setWeaponVector(Vector<Weapon> weaponVector) {
        this.weaponVector = weaponVector;
    }

    public void setFighterVector(Vector<Fighter> fighterVector) {
        this.fighterVector = fighterVector;
    }

    public void setArmor1Vector(Vector<Armor> armorVector) {
        this.armor1Vector = armorVector;
    }

    public void setArmor2Vector(Vector<Armor> armorVector) {
        this.armor2Vector = armorVector;
    }

    public void setFighterAttack(Attack fighterAttack) {
        this.fighterAttack.add( fighterAttack);
    }

    public void setWeaponAttack(Attack weaponAttack) {
        this.weaponAttack.add(weaponAttack);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setArmor1Vector(Armor armor) {
        this.armor1Vector.add(armor);
    }

    public void setArmor2Vector(Armor armor) {
        this.armor2Vector.add(armor);
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

        File file = new File("core/assets/data/fighters.csv");

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
            if(weapon.isTakeable()) {

                setWeaponVector(weapon);

            }
            this.readWeaponsAttacks();
            this.loadWeaponAttacks();
            Armor armor1 = new Armor(fighters.get(i).get("Armor1"), 1, Boolean.parseBoolean(fighters.get(i).get("Armor1Tackable")),Integer.parseInt(fighters.get(i).get("Protection1")), Integer.parseInt(fighters.get(i).get("Armor1Price")), fighters.get(i).get("Armor1Picture"));
            if(armor1.getTakeable())
            {
                setArmor1Vector(armor1);

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
                    setArmor2Vector(armor2);


                }


            }


            setFighterVector(f);
        }

        this.readFightersAttacks();
        this.loadFighterAttack();

        Treasure potion = new Treasure("potion", 1, 1, 15);
        Treasure scroll = new Treasure("scroll", 2, 1, 25);

        setTreasureVector(potion);
        setTreasureVector(scroll);
        Fighter fighter = new Fighter(getFighterVector().get(0));
        player.setCollectionFighter(fighter);
        player.setCollectionWeapon(getWeaponVector().get(0));
        player.setCollectionArmor1(getArmor1Vector().get(0));


    }


    public String buyFighter(Fighter f)
    {
        String str = "";

        if(getPlayer().getMoney()>= f.getPrice())
        {
            str = "You buy a " + f.getName();
            getPlayer().reducePrice(f.getPrice());
            Fighter f2 = new Fighter(f);
            getPlayer().setCollectionFighter(f2);
        }
        else str = "You can't buy it you don't have enough money";

        return  str;
    }

    public String buyArmor1(Armor armor)
    {
        String str = "";
        if(getPlayer().getMoney()>= armor.getPrice())
        {
            str = "You buy a " + armor.getName();
            getPlayer().reducePrice(armor.getPrice());
            getPlayer().setCollectionArmor1(armor);
        }
        else str = "You can't buy it you don't have enough money";

        return  str;
    }

    public String buyArmor2(Armor armor)
    {
        String str = "";
        if(getPlayer().getMoney()>= armor.getPrice())
        {
            str = "You buy a " + armor.getName();
            getPlayer().reducePrice(armor.getPrice());
            getPlayer().setCollectionArmor2(armor);
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

    public void readFightersAttacks()
    {
        File file = new File("core/assets/data/fightersAttack.csv");

        Scanner read_f = null;
        try {
            read_f = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("pas de fichier");

        }
        read_f.useDelimiter(Pattern.compile("\n"));
        String[] attributeNames;
        if(read_f.hasNextLine()){
            String ligne = read_f.nextLine();
            attributeNames = ligne.split(";");
        } else {
            return;
        }


        List<Map<String, String>> weaponsAttack = new ArrayList<>();

        while (read_f.hasNextLine()) {
            String[] attributes = read_f.nextLine().split(";");
            Map<String, String> tmpWeaponAttack = new HashMap<>();
            if(attributeNames.length < attributes.length){
                System.err.println("GROSS ERREUR");
                return;
            }
            for(int i = 0; i < attributeNames.length; i++){
                tmpWeaponAttack.put(attributeNames[i], attributes[i]);
            }
            weaponsAttack.add(tmpWeaponAttack);

        }

        read_f.close();

        generateFighterAttack(weaponsAttack);




    }

    public void readWeaponsAttacks()
    {

        File file = new File("core/assets/data/weapon_attack.csv");

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

        List<Map<String, String>> weaponsAttack = new ArrayList<>();

        while (read_f.hasNextLine()) {
            String[] attributes = read_f.nextLine().split(";");
            Map<String, String> tmpWeaponAttack = new HashMap<>();
            if(attributeNames.length < attributes.length){
                System.err.println("GROSS ERREUR");
                return;
            }
            for(int i = 0; i < attributeNames.length; i++){
                tmpWeaponAttack.put(attributeNames[i], attributes[i]);
            }
            weaponsAttack.add(tmpWeaponAttack);

        }

        read_f.close();

        generateWeaponAttack(weaponsAttack);



    }

    public void generateFighterAttack(List<Map<String, String>> fightersAttacks)
    {

        for(int i=0; i<fightersAttacks.size(); i++)
        {

            Attack fighterAttack = new Attack(fightersAttacks.get(i).get("Name"), 0 ,Integer.parseInt(fightersAttacks.get(i).get("MinDamage")),Integer.parseInt(fightersAttacks.get(i).get("MaxDamage")), fightersAttacks.get(i).get("RelationWith"));
            setFighterAttack(fighterAttack);

        }



    }

    public void generateWeaponAttack(List<Map<String, String>> weaponsAttacks)
    {

        for(int i=0; i<weaponsAttacks.size(); i++)
        {

            Attack weaponAttack = new Attack(weaponsAttacks.get(i).get("Name"), 0 ,Integer.parseInt(weaponsAttacks.get(i).get("MinDamage")),Integer.parseInt(weaponsAttacks.get(i).get("MaxDamage")), weaponsAttacks.get(i).get("RelationWith"));
            setWeaponAttack(weaponAttack);

        }


    }

    public void loadWeaponAttacks() {
        for (int i = 0; i < this.getWeaponVector().size(); i++) {

            for (int j = 0; j < this.weaponAttack.size(); j++) {
                if (weaponAttack.get(j).getRelationWith().equalsIgnoreCase(getWeaponVector().get(i).getName())) {

                    getWeaponVector().get(i).setAttack(weaponAttack.get(j));
                }
            }


        }
    }


        public void loadFighterAttack()
        {

            for(int i=0; i<this.getFighterVector().size(); i++) {
                for (int j = 0; j < this.fighterAttack.size(); j++) {

                    if (fighterAttack.get(j).getRelationWith().equalsIgnoreCase(getFighterVector().get(i).getName())) {
                        getFighterVector().get(i).setAttacks(fighterAttack.get(j));
                    }
                }
            }
        }






    public void shopOpen()
    {
        this.loadFighters();

        Fighter fighter = new Fighter(this.getFighterVector().get(0));
        player.setFighter(fighter);


    }

}
