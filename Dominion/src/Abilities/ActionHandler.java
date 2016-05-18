/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abilities;

import dominion.GameEngine;
import dominion.Models.Card;
import dominion.Speler;
import java.util.ArrayList;
import java.util.Map;

public class ActionHandler {
    private GameEngine engine;
    
    public ActionHandler(GameEngine ge) {
        engine = ge;
    }
    
      public void executeAction(Card c, Speler s){
        switch(c.getCardID()){
            case 1:
                SpecialAction cellar = new SpecialAction(engine, s);
                cellar.actionIncrement(1);
                cellar.discardForNewCard(engine.getCli().askCardsToDiscard());
                break;
            case 2:
                Action market = new Action(engine, s);
                market.drawAmountOfCards(1);
                market.actionIncrement(1);
                market.buysIncrement(1);
                market.coinsIncrement(1);
                break;
            case 3:
                AttackAction militia = new AttackAction(engine, s);
                militia.coinsIncrement(2);
                Map<Speler, ArrayList<Card>> cardsToDiscard = engine.getCli().askOtherPlayersToDiscardTo(3);
                militia.discardToAmountOfCards(cardsToDiscard); //effects only other players
                break;
            case 4:
                SpecialAction mine = new SpecialAction(engine, s);
                Card cardToTrash = engine.getCli().askcardToTrash(s);
                mine.trashTStoGainTS(cardToTrash);
                mine.trashCard(cardToTrash);
                break;
            case 5: //TODO test this
                ReactionAction moat = new ReactionAction(engine, s);
                moat.revealCard(s, c);
                moat.drawAmountOfCards(2);
                moat.ReactionDone(c); 
                break;
            case 6: //TODO make
                break;
            case 7:
                Action smithy = new Action(engine, s);
                smithy.drawAmountOfCards(3);
                break;
            case 8:
                Action village = new Action(engine, s);
                village.drawAmountOfCards(1);
                village.actionIncrement(2);
                break;
            case 9:
                Action woodcutter = new Action(engine, s);
                woodcutter.buysIncrement(1);
                woodcutter.coinsIncrement(2);
                break;
            case 10:
                SpecialAction workshop = new SpecialAction(engine, s);
                Card card = engine.getCli().gainCardCostingUpTo(4);         //test dit D:
                workshop.gainCard(card);
                break;
            case 11: //TODO make
                SpecialAction adventurer = new SpecialAction(engine, s);
                break;
            case 12: //TODO make
                SpecialAction bureaucrat = new SpecialAction(engine, s);
                break;
            case 13:
                SpecialAction chancellor = new SpecialAction(engine, s);
                chancellor.coinsIncrement(2);
                boolean optionToDiscard = engine.getCli().OptionToDiscardAll();
                chancellor.discardAllCards(optionToDiscard);
                break;
            case 14:
                Action chapel = new Action(engine, s);
                chapel.trashSpecifiedAmountOfCards(4);
                break;
            case 15:
                SpecialAction feast = new SpecialAction(engine, s);
                Card cardGained = engine.getCli().gainCardCostingUpTo(5);
                feast.gainCard(cardGained);
                feast.trashCard(c);
            case 16:
                Action labatory = new Action(engine, s);
                labatory.drawAmountOfCards(2);
                labatory.actionIncrement(1);
            case 17:
                SpecialAction moneylender = new SpecialAction(engine, s);
                Card copperCard = engine.getCli().askTrashCopperCard();
                moneylender.trashCard(copperCard);
        }
    }
}
