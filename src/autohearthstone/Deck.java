/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autohearthstone;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Bash
 */
public class Deck {
    public ArrayList<Card> ActiveDeck = new ArrayList<Card>();
    public Hero hero;
    
    public void addCard (Card card)
    {
        ActiveDeck.add(card);
    }
    
    public ArrayList<Card> getDeck(Hearthstone hs, Player ap)
    {
        ArrayList<Card> deck = new ArrayList<Card>();
        
        for (Card card : ActiveDeck)
        {
            card.setAp(ap);
            card.setHs(hs);
            deck.add(card);
        }
        
        return deck;
    }
}
