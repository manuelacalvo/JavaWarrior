package com.adventuregames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fighterlvl.warrior.Player;
import com.shopmanagement.Collection;

public class GameController {
    private int mainMenuChoice;
    private FightController fightController;
    private GameDisplay gameDisplay;
    private Collection shop;
    private Player player;





    public GameController(FightController fightController)
    {
        mainMenuChoice = 0;
        this.fightController = fightController;
        this.gameDisplay = new GameDisplay();
        this.player = null;
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

    public void startPlayer()
    {
        String nom = "Manuela";
        player = new Player(nom);
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
        this.startPlayer();
        shop = new Collection(player);

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.forceExit = false;
        LwjglApplication game = new LwjglApplication(gameDisplay, config);

        while(choice == 0) {

            choice = gameDisplay.getChoice();

            switch (choice) {
                case 1:
                    game.exit();
                    fightController.gameLoop();

                    break;

                case 2:
                    ///
                    game.exit();
                    break;

                case 3:
                    //
                    game.exit();
                    break;

                case 4 :
                    game.exit();
                    fightController.shopOpen();
                    shop = fightController.getColl();
                    shop.openShop();
                    break;

            }
        }




    }


}
