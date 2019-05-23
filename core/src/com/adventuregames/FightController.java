package com.adventuregames;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fighterlvl.warrior.Fighter;
import com.shopmanagement.Collection;

import java.util.Scanner;

public class FightController {
    private Collection coll;
    private Fighter fighter;
    private boolean quitFight;
    private int nbFights;
    private boolean restOnce;
    private FightMenuDisplay fightMenuDisplay;
    ;

    public FightController(Collection coll) {
        this.coll = coll;
        this.quitFight = false;
        this.fighter = null;
        this.nbFights = 0;
        this.restOnce = false;
        this.fightMenuDisplay = new FightMenuDisplay();
    }

    public Collection getColl() {
        return coll;
    }

    public boolean isRestOnce() {
        return restOnce;
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

    public void setRestOnce(boolean restOnce) {
        this.restOnce = restOnce;
    }



    public int choiceMenuFight(Fighter enemy)
    {


        /*Scanner keyboard = new Scanner(System.in);


        System.out.println("What do you want to do now?");
        System.out.println("1. Utilize a magic potion or a scroll");
        System.out.println("2. Rest");
        System.out.println("3. Quit");
        System.out.println("4. Fight newt Fighter");
        choice = keyboard.nextInt();
*/
        int choice = 0;



        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(fightMenuDisplay, config);

        
        while(choice == 0) {
            switch (choice) {
                case 1:
                    fighter.takeItem();
                    break;

                case 2:
                    if (isRestOnce() != true) {
                        fighter.takeARest(enemy);
                        setRestOnce(true);
                    } else System.out.println("You already take a rest you can't do it again before the next fight");

                    break;

                case 3:
                    quitFight = true;
                    System.out.println(" You choose to quit the game. You've got " + fighter.getHitPoints() + " life points and you've made " + nbFights + " fights");
                    break;

                case 4:

            }
        }
        return choice;
    }


    public void gameLoop()
    {

        for(int i=1; i< coll.getFighterVector().size(); i++)
        {
            if(!quitFight && fighter.isAlive()) {
                nbFights ++;
                int choice = 0;
                coll.getFighterVector().get(0).fightTurn(coll.getFighterVector().get(i));
                restOnce = false;
                while(choice!= 4 && fighter.isAlive() && choice !=5 && !quitFight)
                {
                    choice = choiceMenuFight(coll.getFighterVector().get(i));

                }

            }


        }
        if(!fighter.isAlive())
        {
            System.out.println(" You are dead. You've got \" + fighter.getHitPoints() + \" life points and you've made \" + nbFights + \" fights\");");
        }
    }

    public void shopOpen()
    {
        coll.loadFighters();
        this.setFighter(this.coll.getFighterVector().get(0));
    }

    public void deskConstitution()
    {

    }
}
