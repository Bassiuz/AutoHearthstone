/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autohearthstone;

import Actions.*;
import ExcelReader.DataReader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author Bash
 */
public class Player implements Serializable {
    public Boolean GameOutputOn = false;


    public int totalManaCrystal;
    public int emptyManaCrystal;
    public Hearthstone hs;
    public DataReader dr;

    public String Name;

    public Player opponent;
    
    public int lifeTotal;
    public int armor;
    public int attackPower;
    public int fatigue = 0;
    
    public Deck deck;
    
    public ArrayList<Card> ActiveDeck = new ArrayList<Card>();
    public ArrayList<Card> Hand = new ArrayList<Card>();
    public ArrayList<Minion> Board = new ArrayList<Minion>();
    public ArrayList<Card> Secrets = new ArrayList<Card>();
    
    public ArrayList<Action> actions = new ArrayList<Action>();
    
    public Boolean lost = false;

    public Player(Hearthstone hs, DataReader dr) {
        this.hs = hs;
        this.dr = dr;
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

    public int CalculateBoard()
    {
        int totalValue = 0;
        for(Minion minion : getBoard())
        {
            totalValue = totalValue + minion.getBoardValue(dr);
        }

        try {
            totalValue = totalValue + dr.getLifeValue(lifeTotal);
        }
        catch (Exception e)
        {
            return -1;
        }

        return totalValue;

    }

    public void drawACard()
    {if ( ActiveDeck.size() > 0) {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(ActiveDeck.size());
        Card drawnCard = ActiveDeck.get(index);
        Hand.add(drawnCard);
        ActiveDeck.remove(drawnCard);
    }
    else
    {
        fatigue++;
        takeDamage(fatigue);
    }
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

        if (GameOutputOn) {
            System.out.println(getName() + " starts his turn");
            System.out.println("Life: " + Integer.toString(lifeTotal));
            System.out.println("Mana: " + Integer.toString(totalManaCrystal));
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
        ///System.out.println("Passes by" + this.toString());
        opponent.startTurn();
    }

    public void performSpecificAction(Action action)
    {
        action.Perform();
    }

    public void performSpecificActionOnIndex (int index)
    {
        generateAllActions();
        actions.get(index).Perform();
    }

    public void performActions()
    {
        if (!lost && !opponent.lost)
        {
        generateAllActions();
        if (actions.size() > 0)
        {
            // hier wordt gekozen welke acite re gedaan word
            /// optie 1, doe gewoon de eerste
            ///////actions.get(0).Perform();
            /// optie 2, ga board values uitrekenen.
            int boardValue = CalculateBoard();
            int opponentBoardValue = opponent.CalculateBoard();

            int currentScore = boardValue - opponentBoardValue;
            if (GameOutputOn) {
                System.out.println("Current board score for " + getName() + " " + Integer.toString(currentScore));
            }
            Action bestAction = new DoNothing(this);
            int highestScore = currentScore;
            int index = 0;

            for (Action action : actions)
            {
                Player clonedPlayer = SerializationUtils.clone(this);
                clonedPlayer.performSpecificActionOnIndex(index);
                clonedPlayer.checkBoardState();
                clonedPlayer.opponent.checkBoardState();
                if (clonedPlayer.opponent.lost == true)
                {
                    bestAction = action;
                    highestScore = 99999999;
                }
                if (clonedPlayer.checkForLethal())
                {
                    bestAction = action;
                    highestScore = 99999999;
                }

                int boardValueCloned = clonedPlayer.CalculateBoard();
                int opponentBoardValueCloned = clonedPlayer.opponent.CalculateBoard();
                int score = boardValueCloned - opponentBoardValueCloned;
                if (score > highestScore)
                {
                    highestScore = score;
                    bestAction = action;
                }

                index ++;
            }

            if (GameOutputOn) {
                System.out.println(this.getName() + " performs action " + bestAction.toString() + "to go from " + Integer.toString(currentScore) + " to " + Integer.toString(highestScore));
            }
            bestAction.Perform();



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

    public Boolean checkForLethal()
    {
        Boolean canAttackFace = true;
        for (Minion minion : opponent.getBoard())
        {
            if (minion.isTaunt())
            {
                canAttackFace = false;
            }
        }

        int totalDamage = 0;

        for (Minion minion : getBoard())
        {
            if (!minion.isAttacked() && !minion.isSummoningSick())
            {
                totalDamage = totalDamage + minion.power;
            }
        }

        if (totalDamage >= opponent.lifeTotal)
        {
            return true;
        }
        else
        {
            return false;
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
        //System.out.println(Name + " Lost");

    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    
    
}
