/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abilities;

import dominion.GameEngine;
import dominion.Models.Card;

public class ActionHandler {
    private GameEngine engine;
    
    public ActionHandler(GameEngine ge) {
        engine = ge;
    }
    
      public void executeAction(Card c){
        switch(c.getCardID()){
            case 1:
                SpecialAction cellar = new SpecialAction(engine);
                cellar.actionIncrement(1);
                cellar.discardForNewCard(engine.getCli().askCardsToDiscard());
                break;
            case 2:
                Action market = new Action(engine);
                market.drawAmountOfCards(1);
                market.actionIncrement(1);
                market.buysIncrement(1);
                market.coinsIncrement(1);
                break;
            case 3: // TODO make discardToAmountOfCards()
                AttackAction militia = new AttackAction(engine);
                militia.coinsIncrement(2);
                militia.discardToAmountOfCards(3); //effects only other players
                break;
            case 4:
                SpecialAction mine = new SpecialAction(engine);
                int cardToTrash = engine.getCli().askcardToTrash();
                mine.trashToGain(cardToTrash);
                mine.trashCard(cardToTrash);
                break;
            case 5: //TODO make this action
                ReactionAction moat = new ReactionAction(engine);
                moat.disableAttackAction(); //effects only player of this card
                break;
        }
    }
}
