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
public class AttackMinion extends Action{

    public Minion attackingMinion;
    public Minion defendingMinion;

    public AttackMinion(Minion attackingMinion, Minion defendingMinion) {
        this.attackingMinion = attackingMinion;
        this.defendingMinion = defendingMinion;
    }
    
    public void Perform()
    {
        attackingMinion.setDamage(attackingMinion.getDamage() + defendingMinion.getPower());
        defendingMinion.setDamage(defendingMinion.getDamage() + attackingMinion.getPower());
        attackingMinion.setAttacked(true);
        System.out.println(attackingMinion.toString() + "attacking" + defendingMinion.toString());
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(AttackMinion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Minion getAttackingMinion() {
        return attackingMinion;
    }

    public void setAttackingMinion(Minion attackingMinion) {
        this.attackingMinion = attackingMinion;
    }

    public Minion getDefendingMinion() {
        return defendingMinion;
    }

    public void setDefendingMinion(Minion defendingMinion) {
        this.defendingMinion = defendingMinion;
    }
    
    
    
}
