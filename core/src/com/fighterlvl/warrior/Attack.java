package com.fighterlvl.warrior;


import java.io.Serializable;
import java.util.*;


public class Attack implements Serializable {
    private String name;
    private int random;
    private int minDamage;
    private int maxDamage;
    private String relationWith;



    public Attack()
    {
        this.name = "Basic Attack";
        this.minDamage = 1;
        this.maxDamage = 20;
        this.random = 0;
        this.relationWith = " ";
    }

    public  Attack(String name, int random, int minDamage, int maxDamage, String relationWith)
    {
        this.name = name;
        this.random = random;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.relationWith = relationWith;

    }

    public String toString()
    {
        return getName() + " : " + getMinDamage() + ", " + getMaxDamage();
    }
    public String getName() {
        return name;
    }

    public int getRandom() {
        return random;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public String getRelationWith() {
        return relationWith;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setRandom(int random) {
        this.random = random;
    }


    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public void setRelationWith(String relationWith) {
        this.relationWith = relationWith;
    }


    public int calculateImpact()
    {
       int impact = randomNumberGenerator(minDamage, maxDamage);

       return impact;

    }

    public int randomNumberGenerator(int min, int max) {

        Random rand = new Random();
        int span = max - min + 1;
        return (rand.nextInt(span) + min);

    }

}
