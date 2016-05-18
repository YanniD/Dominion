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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class AttackAction extends Action {
    
    public AttackAction(GameEngine engine, Speler actionPlayer){
        super(engine, actionPlayer);
    }
    
    public void discardToAmountOfCards(Map<Speler, ArrayList<Card>> cardsToDiscard){
        LinkedList<Speler> Spelers = super.getEngine().getSpelers(); //bevat alleen de otherPlayers
        for(int i = 0; i < Spelers.size(); i++){
            Speler s = Spelers.get(i);
            if(s != super.getActionPlayer() && !super.getEngine().isPlayerEffected(s)){
                Deck handDeck = s.getHandDeck();
                Deck discardDeck = s.getDiscardDeck();
                ArrayList<Card> discardCards = cardsToDiscard.get(s);
                for(int j = 0; j < discardCards.size(); j++){
                    Card cardToDiscard = discardCards.get(j);
                    discardDeck.addToDeck(0, cardToDiscard);
                }
            }
        }
    }
}
