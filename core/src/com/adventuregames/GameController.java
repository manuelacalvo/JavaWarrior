package com.adventuregames;

import com.fighterlvl.warrior.Fighter;
import com.shopmanagement.Collection;

import java.util.Scanner;

public class GameController {
    private Collection coll;
    private Fighter fighter;
    private boolean quitFight;
    private int nbFights;

    public GameController(Collection coll) {
        this.coll = coll;
        this.quitFight = false;
        this.fighter = null;
        this.nbFights = 0;
    }

    public Collection getColl() {
        return coll;
    }

    public boolean getQuitFight()
    {
        return quitFight;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

    public int getNbFights() {
        return nbFights;
    }

    public void setNbFights(int nbFights) {
        this.nbFights = nbFights;
    }

    public void setColl(Collection coll) {
        this.coll = coll;
    }

    public void setQuitFight(boolean quitFight)
    {
        this.quitFight = quitFight;
    }

    public void choiceMainMenu()
    {

    }

    public int choiceMenuFight(Fighter enemy)
    {
        int choice;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("What do you want to do now?");
        System.out.println("1. Utilize a magic potion or a scroll");
        System.out.println("2. Rest");
        System.out.println("3. Quit");
        System.out.println("4. Fight newt Fighter");
        choice = keyboard.nextInt();



        switch (choice)
        {
            case 1 :
                fighter.takeItem();
                break;

            case 2 :
                fighter.takeARest(enemy);
                break;

            case 3 :
                quitFight = true;
                System.out.println(" You choose to quit the game. You've got " + fighter.getHitPoints() + " and you've made " + nbFights + " fights");
                break;

            case 4 :

        }
        return choice;
    }

    public void gameLoop()
    {
        int choice = 0;
        for(int i=1; i< coll.getFighterVector().size(); i++)
        {
            if(!quitFight && fighter.isAlive()) {
                nbFights ++;

                coll.getFighterVector().get(0).fightTurn(coll.getFighterVector().get(i));
                while(choice!= 4)
                {
                    choice = choiceMenuFight(coll.getFighterVector().get(i));
                }

            }
        }
    }

    public void shopOpen()
    {
        coll.loadFighter();
        this.setFighter(this.coll.getFighterVector().get(0));
    }

    public void deskConstitution()
    {

    }
}
