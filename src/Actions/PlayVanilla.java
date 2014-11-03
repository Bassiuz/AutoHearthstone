/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import autohearthstone.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bash
 */
public class PlayVanilla extends Action{
   
    public Minion playedMinion;

    public PlayVanilla(Minion playedMinion) {
        this.playedMinion = playedMinion;
    }
    
    public void Perform()
    {
        playedMinion.ap.setEmptyManaCrystal(playedMinion.ap.getEmptyManaCrystal() + playedMinion.cost);
        playedMinion.ap.Hand.remove(playedMinion);
        playedMinion.ap.Board.add(playedMinion);
        //System.out.println(playedMinion.ap.toString() + "played" + playedMinion.toString());
    }

    public Minion getPlayedMinion() {
        return playedMinion;
    }

    public void setPlayedMinion(Minion playedMinion) {
        this.playedMinion = playedMinion;
    }
            
    
}
