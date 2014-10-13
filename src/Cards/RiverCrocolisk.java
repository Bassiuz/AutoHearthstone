/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import Actions.*;
import autohearthstone.*;
import java.util.ArrayList;

/**
 *
 * @author Bash
 */
public class RiverCrocolisk extends Minion {
    
    
    public RiverCrocolisk()
    {
        super.cost = 2;
        super.power = 2;
        super.toughness = 3;
        super.damage = 0;
        super.taunt = false;
        super.stealth = false;
        super.silenced = false;
        super.frozen = false;
        super.attacked = false;
        super.summoningSick = true;
    }
    
    public ArrayList<Action> generateActionsOnBoard()
    {
        ArrayList<Action> result = new ArrayList<Action>();
        //attackignoringtaunts
        if (!attacked && !summoningSick)
        {
        for (Card card : super.ap.getOpponent().getBoard())
        {
            result.add(new AttackMinion(this, (Minion)card));
        }
        result.add(new AttackHero(this, super.ap.getOpponent()));
        }
        
        return result;
    }
    
    public ArrayList<Action> generateActionsInHand()
    {
         ArrayList<Action> result = new ArrayList<Action>();
         
         if (ap.getFullManaCrystal() >= cost)
         {
             result.add(new PlayVanilla(this));
         }
         
         return result;
        
    }
}
