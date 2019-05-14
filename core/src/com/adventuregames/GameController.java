package com.adventuregames;

import com.shopmanagement.Collection;

public class GameController {
    private Collection coll;

    public GameController(Collection coll) {
        this.coll = coll;
    }

    public Collection getColl() {
        return coll;
    }

    public void setColl(Collection coll) {
        this.coll = coll;
    }

    public void choiceMenu()
    {

    }

    public void gameLoop()
    {
        for(int i=1; i< coll.getFighterVector().size(); i++)
        {
            coll.getFighterVector().get(0).fightTurn(coll.getFighterVector().get(i));
        }
    }

    public void shopOpen()
    {
        coll.loadFighter();
    }

    public void deskConstitution()
    {

    }
}
