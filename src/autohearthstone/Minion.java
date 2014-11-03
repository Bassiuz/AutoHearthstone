/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autohearthstone;

import ExcelReader.DataReader;

import java.io.Serializable;

/**
 *
 * @author Bash
 */
public class Minion extends Card implements Serializable {
    public int power;
    public int toughness;
    public int damage;
    public boolean taunt;
    public boolean stealth;
    public String state;
    public boolean silenced;
    public  boolean frozen;
    public  boolean attacked;
    public int cost;
    public boolean summoningSick;
    public int ExtraHandValue;
    public int ExtraBoardValue;

    public Minion() {
    }
    
    public void Destroy() {
        ap.Board.remove(this);
    }

    public int getBoardValue()
    {
       try {
           return DataReader.getVanillaValue(power, toughness);
        }
       catch (Exception e)
        {
            return -1;
        }
    }

    public int getHandValue()
    {
        return ExtraHandValue;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isTaunt() {
        return taunt;
    }

    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public boolean isStealth() {
        return stealth;
    }

    public void setStealth(boolean stealth) {
        this.stealth = stealth;
    }

    public boolean isSilenced() {
        return silenced;
    }

    public void setSilenced(boolean silenced) {
        this.silenced = silenced;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
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

    public boolean isAttacked() {
        return attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public int getCost() {
        return cost;
    }

    public boolean isSummoningSick() {
        return summoningSick;
    }

    public void setSummoningSick(boolean summoningSick) {
        this.summoningSick = summoningSick;
    }
}
