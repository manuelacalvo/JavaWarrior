package com.adventuregames;

import com.fighterlvl.warrior.Fighter;
import com.shopmanagement.Collection;

import java.util.Scanner;

public class GameController {
    private Collection coll;
    private boolean quitFight;

    public GameController(Collection coll) {
        this.coll = coll;
        this.quitFight = false;
    }

    public Collection getColl() {
        return coll;
    }

    public boolean getQuitFight()
    {
        return quitFight;
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

    public void choiceMenuFight(Fighter enemy)
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
                //takeItem();
                break;

            case 2 :
                //takeARest(enemy);
                break;

            case 3 :
                quitFight = true;
                break;

            case 4 :
                break;
        }
    }

    public void gameLoop()
    {
        for(int i=1; i< coll.getFighterVector().size(); i++)
        {
            if(!quitFight) {
                coll.getFighterVector().get(0).fightTurn(coll.getFighterVector().get(i));
                choiceMenuFight(coll.getFighterVector().get(i));
            }
        }
    }

    public void shopOpen()
    {
        coll.loadFighter();
    }

    public void deskConstitution()
    {

    }
}
