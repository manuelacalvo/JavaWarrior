package com.shopmanagement;

import com.fighterlvl.warrior.Armor;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Treasure;
import com.fighterlvl.warrior.Weapon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

public class Collection {

    private Vector<Fighter> fighterVector = new Vector<Fighter>();
    private Vector<Weapon> weaponVector = new Vector<Weapon>();
    private Vector<Armor> armorVector = new Vector<Armor>();
    private Vector<Treasure> treasureVector = new Vector<Treasure>();

    public void loadFighter() {

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
            System.out.print(fighterName);
            int hitPoints = Integer.parseInt(read_f.next());
            System.out.print(hitPoints);
            String weaponName = read_f.next();
            System.out.print(weaponName);
            int attacksPerTurn = Integer.parseInt(read_f.next());
            System.out.print(attacksPerTurn);
            int minDamage = Integer.parseInt(read_f.next());
            System.out.print(minDamage);
            int maxDamage = Integer.parseInt(read_f.next());
            System.out.print(maxDamage);
            String nameArmor1 = read_f.next();
            System.out.print(nameArmor1);
            int protection1 = Integer.parseInt(read_f.next());
            System.out.print(protection1);
            String nameArmor2 = read_f.next();
            System.out.print(nameArmor2);
            int protection2 = Integer.parseInt(read_f.next());
            System.out.print(protection2);
            int potionNumber = Integer.parseInt(read_f.next());
            System.out.print(potionNumber);
            int scrollNumber = Integer.parseInt(read_f.next());
            System.out.print(scrollNumber);
            int goldNumber = Integer.parseInt(read_f.next());
            System.out.print(goldNumber);
            int silverNumber = Integer.parseInt(read_f.next());
            System.out.println(silverNumber);


            Weapon weapon = new Weapon(weaponName,attacksPerTurn,minDamage,maxDamage);
            Armor armor1 = new Armor(nameArmor1, 1, protection1);


            if(potionNumber != 0) {
                Treasure potion = new Treasure("potion", 1, potionNumber);
                treasures.add(potion);
            }

            if(scrollNumber != 0) {
                Treasure scroll = new Treasure("scroll", 2, scrollNumber);
                treasures.add(scroll);
            }

            if(goldNumber != 0) {
                Treasure gold = new Treasure("gold", 3, goldNumber);
                treasures.add(gold);
            }

            if(silverNumber != 0) {
                Treasure silver = new Treasure("silver", 4, potionNumber);
                treasures.add(silver);
            }

            Fighter f = new Fighter(fighterName, weapon, armor1, treasures, hitPoints, 0);
            if(nameArmor2 != "null") {
                Armor armor2= new Armor(nameArmor2, 2, protection2);
                f.setArmor2(armor2);
            }

            // sauvegarde dans une liste  Nom_Prenom
            fighterVector.add(f);
        }
        read_f.close();
    }

    public  void combatTest() {
        fighterVector.get(0).fightTurn(fighterVector.get(1));
    }
}
