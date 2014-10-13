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
public class AttackHero extends Action{
    
    public Minion attackingMinion;
    public Player defendingPlayer;

    public AttackHero(Minion attackingMinion, Player defendingPlayer) {
        this.attackingMinion = attackingMinion;
        this.defendingPlayer = defendingPlayer;
    }

    public void Perform()
    {
        defendingPlayer.takeDamage(attackingMinion.getPower());
        attackingMinion.setAttacked(true);
        System.out.println(attackingMinion.toString() + "attacking" + defendingPlayer.toString());
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(AttackHero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Minion getAttackingMinion() {
        return attackingMinion;
    }

    public void setAttackingMinion(Minion attackingMinion) {
        this.attackingMinion = attackingMinion;
    }

    public Player getDefendingPlayer() {
        return defendingPlayer;
    }

    public void setDefendingHero(Player defendingPlayer) {
        this.defendingPlayer = defendingPlayer;
    }
    
    
}
