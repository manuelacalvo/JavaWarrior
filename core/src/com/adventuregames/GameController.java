package com.adventuregames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;

public class GameController {
    private int mainMenuChoice;
    private FightController fightController;
    private Collection shop;





    public GameController(FightController fightController)
    {
        mainMenuChoice = 0;
        this.fightController = fightController;
        this.shop = null;

    }

    public int getMainMenuChoice() {
        return mainMenuChoice;
    }

    public FightController getFightController() {
        return fightController;
    }

    public void setMainMenuChoice(int mainMenuChoice) {
        this.mainMenuChoice = mainMenuChoice;
    }


    public void setFightController(FightController fightController) {
        this.fightController = fightController;
    }



    public void choiceMainMenu()
    {
        int choice = 0;

        while(choice == 0) {

            //choice = gameDisplay.getChoice();

            switch (choice) {
                case 1:
                    //game.exit();
                    fightController.gameLoop();

                    break;

                case 2:
                    ///
                    //game.exit();
                    break;

                case 3:
                    //
                    //game.exit();
                    break;

                case 4 :
                    //game.exit();
                    fightController.shopOpen();
                    shop = fightController.getColl();
                    shop.openShop();
                    break;

            }
        }




    }


}
