package com.adventuregames;

import com.shopmanagement.Collection;

import javax.swing.*;

public class GameController {
    private int mainMenuChoice;
    private GameModel gameModel;
    private GameDisplay gameDisplay;

    public  void GameController(GameModel gameModel, GameDisplay gameDisplay)
    {
        mainMenuChoice = 0;
        this.gameModel = gameModel;
        this.gameDisplay = gameDisplay;
    }

    public int getMainMenuChoice() {
        return mainMenuChoice;
    }

    public GameDisplay getGameDisplay() {
        return gameDisplay;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setMainMenuChoice(int mainMenuChoice) {
        this.mainMenuChoice = mainMenuChoice;
    }

    public void setGameDisplay(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void playChoice()
    {
        int choice = 0;

        choice = gameDisplay.getChoiceMainMenu();
        if(choice == 1)
        {
            gameModel.gameLoop();
        }
    }
}
