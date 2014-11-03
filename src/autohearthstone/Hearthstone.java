/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autohearthstone;

import Cards.*;

import java.io.Serializable;

/**
 *
 * @author Bash
 */
public class Hearthstone implements Serializable {
    Player player1;
    Player player2;
    
    public Hearthstone()
    {
        player1 = new Player(this);
        player2 = new Player(this);
        
        Deck deck = new Deck();
        Deck deck2 = new Deck();
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        deck.addCard(new RiverCrocolisk());
        
        
        deck2.addCard(new ChillwindYeti());
        deck2.addCard(new ChillwindYeti());
        deck2.addCard(new ChillwindYeti());
        deck2.addCard(new ChillwindYeti());
        deck2.addCard(new ChillwindYeti());
        deck2.addCard(new ChillwindYeti());
        deck2.addCard(new ChillwindYeti());
        deck2.addCard(new ChillwindYeti());
        deck2.addCard(new ChillwindYeti());
        deck2.addCard(new ChillwindYeti());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        deck2.addCard(new Wisp());
        

        
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        
        player1.setDeck(deck);
        player2.setDeck(deck2);
        
        player1.setUp();
        player2.setUp();

        player1.setName("Player 1");
        player2.setName("Player 2");

        player1.startTurn();
        
    }
}
