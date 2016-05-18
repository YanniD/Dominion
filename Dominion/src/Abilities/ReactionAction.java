/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abilities;

import dominion.GameEngine;
import dominion.Models.Card;
import dominion.Models.Deck;
import dominion.Speler;

public class ReactionAction extends Action{
    
    public ReactionAction(GameEngine engine, Speler actionPlayer){
        super(engine, actionPlayer);
    }
    
    
    public void ReactionDone(Card c){
        Deck handDeck = super.getActionPlayer().getHandDeck();
        Deck discardDeck = super.getActionPlayer().getDiscardDeck();
        super.getActionPlayer().setEffected(true);
        handDeck.moveOneCardToOtherDeck(discardDeck, c);
    }
    
    
}
