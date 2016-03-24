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
    private ArrayList<Card> cards;
    
    /**
     * At start of game draw deck is 
     */
    public Deck(boolean isDrawDeck) {
        cards = new ArrayList<Card>();
        if (isDrawDeck) {
            ArrayList<Card> starterDeck = makeStarterDeck();
            cards.addAll(starterDeck);
        }
        else{
            Arrays.asList();
        }
    }
    
    public ArrayList<Card> makeStarterDeck() {
        ArrayList<Card> starterDeck = new ArrayList<Card>();
        for (int i = 0; i < 10; i++) {
            if (i < 7){
                Card tempCopperCard = new Card(25,0,"copper");
                starterDeck.add(tempCopperCard);
            }
            else {
                Card tempEstateCard = new Card(28,2,"estate");
                starterDeck.add(tempEstateCard);
            }
        }
        return starterDeck;
    }
    
    public void addToDeck(int index, Card c){
        cards.add(index, c);
    }
    
    public void removeCardatIndex(int index){
        cards.remove(index);
    }
    
    public int getIndexOf(Card c){
        return cards.indexOf(c);
    }
    
    private void emptyDeck() {
        for (int i = 0; i < cards.size(); i++) {
            removeCardatIndex(i);
        }
    }
    
    public void randomShuffle() {
        int max = cards.size();
        for (int i = 0; i < max; i++) {
            int randomSpot = 0 + (int)(Math.random() * max);;
            Card temp = cards.get(randomSpot);
            Card temp2 = cards.get(i);
            cards.set(i,temp);
            cards.set(randomSpot,temp2);
        }
    }
    
    public void CardToOtherDeck(Card c,Deck otherDeck){
        otherDeck.cards = this.cards;
    }
    
    public Card getCardAtIndex(int index)
    {
        return cards.get(index);
    }
    
    public String toString()
    {
        return cards.toString();
    }
}
