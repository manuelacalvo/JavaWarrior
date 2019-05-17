package com.adventuregames;

public class GameController {
    private int mainMenuChoice;
    private GameModel gameModel;



    public GameController(GameModel gameModel)
    {
        mainMenuChoice = 0;
        this.gameModel = gameModel;

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

            gameModel.shopOpen();
            gameModel.gameLoop();


    }



}
