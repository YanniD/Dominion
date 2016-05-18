/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abilities;

import dominion.GameEngine;
import dominion.Models.Card;
import dominion.Models.Deck;
import dominion.Models.Pile;
import dominion.Models.TreasureCard;
import dominion.Speler;
import java.util.ArrayList;

public class SpecialAction extends Action { 
    
    public SpecialAction(GameEngine engine, Speler actionPlayer){
        super(engine, actionPlayer);
    }
    
    /**
     * used by cellar
     */
    public void discardForNewCard(ArrayList<Card> discardedCards){
        Deck handDeck = super.getHandDeckOfPlayer();
        Deck discardDeck = super.getDiscardDeckOfPlayer();
        Deck drawDeck = super.getDiscardDeckOfPlayer();
        handDeck.moveSpecificCardsToOtherDeck(discardedCards, discardDeck);
        drawDeck.moveAmountOfCardsToOtherDeck(discardedCards.size(), handDeck);
    }
    
    public void trashTStoGainTS(int indexTScard){
        Pile higherTSpile = null;
        Deck handDeck = super.getHandDeckOfPlayer();
        int cardID = handDeck.getCardAtIndex(indexTScard).getCardID();
        if(cardID == 25){
            higherTSpile = super.getEngine().getPiles().get(11);
        } else if (cardID == 26){
            higherTSpile = super.getEngine().getPiles().get(12);        
        } else {
            higherTSpile = super.getEngine().getPiles().get(12);
        }
        handDeck.addToDeck(0, higherTSpile.getCard());
        higherTSpile.decrementAmount();
    }

    public void gainCard(Card card){
        super.getDiscardDeckOfPlayer().addToDeck(0, card);
    }
    
    public void discardAllCards(Boolean optionToDiscard){
        if(optionToDiscard){
            Deck discardDeck = super.getDiscardDeckOfPlayer();
            super.getHandDeckOfPlayer().moveAllCardsToOtherDeck(discardDeck);
        }
    }
}
