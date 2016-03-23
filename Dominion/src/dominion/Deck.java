/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;
import java.util.*;

/**
 * Decks are the players playing cards
 */
public class Deck {
    private List<Integer> cards;
    
    public Deck() {
         cards = new List<Integer>;
    }
    
    public void addToDeck(int Card) {
        List<Integer> cards = new ArrayList<Integer>();
        cards.add(Card);
    }
    
    public void removeFromDeck(int amountOfCards){
        cards.remove(amountOfCards)
    }
    
    public void randomshuffle() {
        for (int i = 0; i < cards.length; i++) {
            int randomSpot = (int)((Math.random()*(51-i))+i);
            Card temp = this.cardAt(i);
            this.cards[i] = this.cardAt(randomSpot);
            this.cards[randomSpot] = temp;
        }
    }
}
