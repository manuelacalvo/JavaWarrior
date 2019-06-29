package com.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Display name and life of a Fighter
 */
public class StatusBox extends Table {

    private Label nameLabel;
    private Label lifeLabel;
    private Table uiContainer;

    public StatusBox(Skin skin){
        super(skin);
        this.setBackground("battleinfobox");
        uiContainer = new Table();
        this.add(uiContainer).pad(0f).expand().fill();

        nameLabel = new Label("namenull", skin, "smallLabel");
        uiContainer.add(nameLabel).align(Align.left).padTop(0f).row();

        lifeLabel = new Label("0", skin, "smallLabel");
        uiContainer.add(lifeLabel).spaceTop(0f).expand().fill();
    }

    public void setlifeLabel(String pName){
        lifeLabel.setText(pName);
    }
    public void setNameLabel(String pName){
        nameLabel.setText(pName);
    }
    public Label getLifeLabel(){
        return this.lifeLabel;
    }
}
