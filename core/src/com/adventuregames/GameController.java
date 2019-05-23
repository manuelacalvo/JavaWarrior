package com.adventuregames;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class GameController {
    private int mainMenuChoice;
    private FightController fightController;
    private GameDisplay gameDisplay;



    public GameController(FightController fightController)
    {
        mainMenuChoice = 0;
        this.fightController = fightController;
        this.gameDisplay = new GameDisplay();

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

    public void fightMode()
    {





    }

    public void choiceMainMenu()
    {
        int choice = 0;
        fightController.shopOpen();
        gameDisplay = new GameDisplay();

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(gameDisplay, config);

        while(choice == 0) {

            choice = gameDisplay.getChoice();

            switch (choice) {
                case 1:
                    fightController.gameLoop();
                    break;

                case 2:
                    ///
                    break;

                case 3:
                    //
                    break;

            }
        }
    }


}
