package com.adventuregames;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class GameController {
    private int mainMenuChoice;
    private GameModel gameModel;
    private GameDisplay gameDisplay;



    public GameController(GameModel gameModel)
    {
        mainMenuChoice = 0;
        this.gameModel = gameModel;
        this.gameDisplay = new GameDisplay();

    }

    public int getMainMenuChoice() {
        return mainMenuChoice;
    }



    public GameModel getGameModel() {
        return gameModel;
    }

    public void setMainMenuChoice(int mainMenuChoice) {
        this.mainMenuChoice = mainMenuChoice;
    }



    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void fightMode()
    {





    }

    public void choiceMainMenu()
    {
        int choice = 0;
        gameModel.shopOpen();
        gameDisplay = new GameDisplay();

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(gameDisplay, config);

        while(choice == 0) {

            choice = gameDisplay.getChoice();

            switch (choice) {
                case 1:
                    gameModel.gameLoop();
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
