/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autohearthstone;

import Cards.*;

import java.io.IOException;
import java.io.Serializable;

import ExcelReader.DataReader;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 *
 * @author Bash
 */
public class Hearthstone implements Serializable {

    int Player1Wins = 0;
    int Player2Wins = 0;
    int totalGames = 50;
    
    public Hearthstone() throws BiffException, IOException, WriteException
    {
        DataReader dr = new DataReader();

        for(int l=1; l<totalGames; l++) {
        Player player1 = new Player(this, dr);
        Player player2 = new Player(this, dr);
        
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
            if (player1.lost)
            {
                Player2Wins++;
            }
            else
            {
                Player1Wins++;
            }
        }


        float percent = (Player1Wins * 100.0f) / totalGames;
        System.out.println("Player 1 wins "  + Float.toString(percent) + " percent of the time, calculated by generating " + totalGames + " games");
    }
}
