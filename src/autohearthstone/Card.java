/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autohearthstone;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Bash
 */
public class Card implements Serializable {
    
    String state;
    public Hearthstone hs;
    public Player ap;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<Action> generateActionsOnBoard()
    {
        return null;
    }
    
    public ArrayList<Action> generateActionsInHand()
    {        
        return null;
    }

    public Hearthstone getHs() {
        return hs;
    }

    public void setHs(Hearthstone hs) {
        this.hs = hs;
    }

    public Player getAp() {
        return ap;
    }

    public void setAp(Player ap) {
        this.ap = ap;
    }
    
    
}
