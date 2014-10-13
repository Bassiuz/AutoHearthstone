/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autohearthstone;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Bash
 */
public class Player {
    public int totalManaCrystal;
    public int emptyManaCrystal;
    public Hearthstone hs;
    
    public Player opponent;
    
    public int lifeTotal;
    public int armor;
    public int attackPower;
    
    public Deck deck;
    
    public ArrayList<Card> ActiveDeck = new ArrayList<Card>();
    public ArrayList<Card> Hand = new ArrayList<Card>();
    public ArrayList<Minion> Board = new ArrayList<Minion>();
    public ArrayList<Card> Secrets = new ArrayList<Card>();
    
    public ArrayList<Action> actions = new ArrayList<Action>();
    
    public Boolean lost = false;

    public Player(Hearthstone hs) {
        this.hs = hs;
    }
    
    public void generateAllActions()
    {
        actions.clear();
        for (Card card : Hand)
        {
            actions.addAll(card.generateActionsInHand());
        }
        for (Card card : Board)
        {
            actions.addAll(card.generateActionsOnBoard());
        }
    }
    
    public void drawACard()
    {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(ActiveDeck.size());
        Card drawnCard = ActiveDeck.get(index);
        Hand.add(drawnCard);
        ActiveDeck.remove(drawnCard);
    }
    
    public void setUp()
    {
        ActiveDeck.addAll(deck.getDeck(hs, this));
        drawACard();
        drawACard();
        drawACard();
        lifeTotal = 30;
    }
    
    public void startTurn()
    {
        drawACard();
        if (totalManaCrystal < 10)
        {
            totalManaCrystal = totalManaCrystal + 1;
        }        
        emptyManaCrystal = 0;
        
        for (Minion minion : Board)
        {
            minion.setSummoningSick(false);
            minion.setAttacked(false);
        }
        
        
        performActions();
    }
    
    public void checkBoardState() {
        if (lifeTotal <= 0)
        {
            lose();
        }
        ArrayList<Minion> deadBodies = new ArrayList<Minion>();
        for (Minion minion : Board)
        {
            if (minion.damage >= minion.toughness)
            {
               deadBodies.add(minion);
            }
        }
        Board.removeAll(deadBodies);
    }
    
    public void pass()
    {
        System.out.println("Passes by" + this.toString());
        opponent.startTurn();
    }
    
    public void performActions()
    {
        if (!lost && !opponent.lost)
        {
        generateAllActions();
        if (actions.size() > 0)
        {
            actions.get(0).Perform();
            checkBoardState();
            opponent.checkBoardState();
            actions.clear();
            performActions();
        }
        else
        {
            pass();
        }
        }
        
    }
    
    public void takeDamage(int damage)
    {
        if (armor > damage)
        {
            armor = armor - damage;
        }
        else
        {
            damage = damage - armor;
            armor = 0;
            lifeTotal = lifeTotal - damage;
        }
    }

    public int getTotalManaCrystal() {
        return totalManaCrystal;
    }

    public void setTotalManaCrystal(int totalManaCrystal) {
        this.totalManaCrystal = totalManaCrystal;
    }

    public int getEmptyManaCrystal() {
        return emptyManaCrystal;
    }

    public void setEmptyManaCrystal(int emptyManaCrystal) {
        this.emptyManaCrystal = emptyManaCrystal;
    }
    
    public int getFullManaCrystal()
    {
        return totalManaCrystal - emptyManaCrystal;
    }

    public int getLifeTotal() {
        return lifeTotal;
    }

    public void setLifeTotal(int lifeTotal) {
        this.lifeTotal = lifeTotal;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getActiveDeck() {
        return ActiveDeck;
    }

    public void setActiveDeck(ArrayList<Card> ActiveDeck) {
        this.ActiveDeck = ActiveDeck;
    }

    public ArrayList<Card> getHand() {
        return Hand;
    }

    public void setHand(ArrayList<Card> Hand) {
        this.Hand = Hand;
    }

    public ArrayList<Minion> getBoard() {
        return Board;
    }

    public void setBoard(ArrayList<Minion> Board) {
        this.Board = Board;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public ArrayList<Card> getSecrets() {
        return Secrets;
    }

    public void setSecrets(ArrayList<Card> Secrets) {
        this.Secrets = Secrets;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    private void lose() {
        lost = true;
        System.out.println(this.toString() + "Lost");
    }


    
    
}