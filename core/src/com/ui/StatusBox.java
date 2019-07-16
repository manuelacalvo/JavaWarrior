package com.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.fighterlvl.warrior.Fighter;
import com.javawarrior.JWGame;

/**
 * Display name and life of a Fighter
 */
public class StatusBox extends Table {

    private Label nameLabel;
    private Label hpText;

    private HPBar hpBar;

    private Table uiContainer;

    public StatusBox(Skin skin){
        super(skin);
        this.setBackground("battleinfobox");
        uiContainer = new Table();
        this.add(uiContainer).pad(0f).expand().fill();

        nameLabel = new Label("namenull", skin, "smallLabel");
        uiContainer.add(nameLabel).align(Align.left).padTop(0f).row();

        hpBar = new HPBar(skin);
        uiContainer.add(hpBar).spaceTop(0f).expand().fill();

        hpText = new Label("NaN/NaN", skin, "smallLabel");
        uiContainer.row();
        uiContainer.add(hpText).expand().right();

    }

    public StatusBox(JWGame oGame, Fighter oFighter){
        this(oGame.getSkin());
        updateFighter(oFighter);
    }

    /**
     * Used to change StatusBox infos internally and externally
     * @param oFighter
     */
    public void updateFighter(Fighter oFighter){
        this.setNameLabel(oFighter.getName());
        this.setHPText(oFighter.getHitPoints(),oFighter.getMaxHP());
    }
    public void updateFighter(Fighter oFighter,int HPInstant, int HPMax){
        this.setNameLabel(oFighter.getName());
        this.setHPText(HPInstant, HPMax);
    }

    public void setNameLabel(String pName){
        nameLabel.setText(pName);
    }

    public void setHPText(int hpLeft, int hpTotal) {
        hpText.setText(hpLeft+" / "+hpTotal);
    }

    public HPBar getHPBar() {
        return hpBar;
    }

}
