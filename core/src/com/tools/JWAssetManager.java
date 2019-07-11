package com.tools;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class JWAssetManager extends AssetManager {

    private static JWAssetManager instance = null;
    // = new JWAssetManager();

    /* PICTURES */
    public final static String path_background = "core/assets/graphics/pictures/main_background.png";
    public final static String path_shopBG = "core/assets/graphics/pictures/shop.jpg";

    /* FIGHTERS *//*
    public final static String path_fighterUser = "core/assets/graphics/fighter_picture/User.jpg";
    public final static String path_fighterBerserker = "core/assets/graphics/fighter_picture/Berserker.jpg";
    public final static String path_fighterOrc = "core/assets/graphics/fighter_picture/Orc.jpg";
    public final static String path_fighterTroll = "core/assets/graphics/fighter_picture/Troll.jpg";
    public final static String path_fighterWizzard = "core/assets/graphics/fighter_picture/Wizard.jpg";
    public final static String path_fighterDragon = "core/assets/graphics/fighter_picture/Dragon.jpg";
    public final static String path_fighterNinja = "core/assets/graphics/fighter_picture/Ninja.jpg";
    public final static String path_fighterDoppleganger = "core/assets/graphics/fighter_picture/Doppleganger.jpg";
    public final static String path_fighterNestOfSnakes = "core/assets/graphics/fighter_picture/Nest of snakes.jpg";*/

    /* BACKGROUND *//*
    public final static String path_BGAerodactyl = "background/bgAerodactyl.png";
    public final static String path_BGHooh = "background/bgHooh.png";
    public final static String path_BGLock = "background/bgLock.png";
    public final static String path_BGMeloetta = "background/bgMeloetta.png";
    public final static String path_BGBG = "background/bg.png";*/

    /* BUTTONS *//*
    public final static String path_ButtonBuy = "buttons/buy.PNG";
    public final static String path_ButtonArrowDoubleRight = "buttons/arrow_double_right.jpg";
    public final static String path_ButtonQuit = "buttons/quit.PNG";
    public final static String path_ButtonContinue = "buttons/continue.PNG";
    public final static String path_ButtonOk = "buttons/ok.PNG";
    public final static String path_ButtonArrowDoubleLeft = "buttons/arrow_double_left.jpg";
    public final static String path_ButtonShop = "buttons/shop.PNG";
    public final static String path_ButtonCancelSel = "buttons/partyCancelSel.png";
    public final static String path_ButtonRestart = "buttons/restart.PNG";
    public final static String path_ButtonConnect = "buttons/connect.PNG";
    public final static String path_ButtonOptions = "buttons/options.PNG";
    public final static String path_ButtonBackBar = "buttons/Back_bar.png";
    public final static String path_ButtonPlay = "buttons/play.PNG";
    public final static String path_ButtonStart = "buttons/start.PNG";*/

    /* ITEMS *//*
    public final static String path_itemShield = "items/shield.jpg";
    public final static String path_itemChainMail = "items/chain_mail.jpg";
    public final static String path_itemPotion = "items/potion.png";
    public final static String path_itemDagger = "items/dagger.jpg";
    public final static String path_itemSword = "items/sword.jpg";
    public final static String path_itemSilver = "items/silver.PNG";
    public final static String path_itemHeavyShield = "items/heavy_shield.jpg";
    public final static String path_itemItem725 = "items/item725.png";
    public final static String path_itemScroll = "items/scroll.jpg";
    public final static String path_itemLeatherArmor = "items/leather_armor.jpg";
    public final static String path_itemGold = "items/gold.PNG";*/

    /* MAP */
    /*
    public final static String path_mapTSG1 = "map/TilesetGame.png";
    public final static String path_mapTSG3 = "map/TilesetGame3.png";
    public final static String path_mapTSG2 = "map/TilesetGame2.png";
    public final static String path_mapTSG = "map/TilesetGame.atlas";*/

    /* UI */

    private JWAssetManager(){
        super();
    }

    /**
     * Public access to the instance
     * @return JWAssetManager an instance of AssetManager with ressources loaded
     */
    public static JWAssetManager getInstance(){
        if (instance != null)
            return instance;
        else {
            instance = new JWAssetManager();
            loadAssetsManager();
            return instance;
        }
    }

    /**
     * Used internally to load the assets
     */
    private static void loadAssetsManager(){
        instance.load(JWAssetManager.path_shopBG, Texture.class);
        instance.load(JWAssetManager.path_background, Texture.class);

        //END
        instance.finishLoading();
    }


}
