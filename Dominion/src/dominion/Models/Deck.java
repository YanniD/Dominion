/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.Models;
import java.util.*;
import dominion.Models.*;
import dominion.Database.DatabaseService;

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
    }
    
    public ArrayList<Card> makeStarterDeck() {
        DatabaseService dbs = new DatabaseService();
        ArrayList<Card> starterDeck = new ArrayList<Card>();
        for (int i = 0; i < 10; i++) {
            if (i < 7){  //int cardID ,int cost, String title, CardType cardType
                Card tempCopperCard = dbs.FindCardByID(25);            //ID temp kaart niet juist
                starterDeck.add(tempCopperCard);
            }
            else {
                Card tempEstateCard = dbs.FindCardByID(28);
                starterDeck.add(tempEstateCard);
            }
        }
        return starterDeck;
    }
    
    public void addToDeck(int index, Card c){
        cards.add(index, c);
    }
    
    public void removeCardAtIndex(int index){
        cards.remove(index);
    }
    
    public int getIndexOf(Card c){
        return cards.indexOf(c);
    }
    
    public Card getCardAtIndex(int index){
        return cards.get(index);
    }
    
    private void emptyDeck() {
        for (int i = 0; i < cards.size(); i++) {
            removeCardAtIndex(i);
        }
    }
    
    public int getLengthFromDeck(){
        return cards.size();
    }
    
    public void moveAllCardsToOtherDeck(Deck otherDeck){
        otherDeck.cards = this.cards;
        emptyDeck();
    }
    
    public void moveOneCardToOtherDeck(Deck otherDeck,Card choiceCard){   // 1 kaart wegsturen naar bv naar je discard pile
         otherDeck.addToDeck(0,choiceCard);
         removeCardAtIndex(getIndexOf(choiceCard));
    }
    
    public void moveAmountOfCardsToOtherDeck(int amount , Deck otherDeck){
        while( amount != 0){     
            moveOneCardToOtherDeck(otherDeck, getCardAtIndex(0));
            amount -=1;
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
    
    /**
     * testFunction !!mag weg!!
     */
    public String toString(){
        return cards.toString();
    }
}