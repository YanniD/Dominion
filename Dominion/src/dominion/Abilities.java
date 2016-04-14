/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;
import dominion.Database.DatabaseService;
import dominion.Models.Card;
import dominion.Models.CardType;
import dominion.Models.Deck;
import dominion.Speler;
import dominion.Models.*;
import java.util.ArrayList;
/**
 *
 * @author Yanni
 */
public class Abilities {
    public Abilities(){
        
    }
    public void drawAmountOfCards(Speler speler, int amount){
        Deck handDeck = speler.getHandDeck();
        Deck drawDeck = speler.getDrawDeck();
        checkAmountCardsInDrawDeck(speler, amount);
        drawDeck.moveAmountOfCardsToOtherDeck(amount, handDeck);
    }
    
    public void checkAmountCardsInDrawDeck(Speler speler, int cardsNeeded){
        Deck drawDeck = speler.getDrawDeck();
        Deck discardDeck = speler.getDiscardDeck();
        if (drawDeck.getLengthFromDeck() < cardsNeeded){
            discardDeck.randomShuffle();
            discardDeck.moveAllCardsToOtherDeck(drawDeck);
        }
    }
    
    /**
     * Use in a while loop in the CLI
     * if stop action = true then is choice card = null
     */
    public void cellarAbility(Speler speler,int timesDiscarded,Card choiceCard, Boolean stopAction){
        Deck handDeck = speler.getHandDeck();
        Deck discardDeck = speler.getDiscardDeck();
        Deck drawDeck = speler.getDrawDeck();
        if(!stopAction){
            handDeck.moveOneCardToOtherDeck(discardDeck,choiceCard);
            timesDiscarded +=1;
        }
        else{
           speler.actionIncrement(1);
           checkAmountCardsInDrawDeck(speler, timesDiscarded);
           drawDeck.moveAmountOfCardsToOtherDeck(timesDiscarded, handDeck);
        }     
    }
    
    public void MarketAbility(Speler speler,Card nieuweKaart){
        speler.actionIncrement(1);
        speler.buysIncrement(1); 
        speler.coinsIncrement(1);
        drawAmountOfCards(speler, 1);
    }
    
    public void MilitiaAbility(Speler speler1,Speler speler2){
        speler1.coinsIncrement(2);
        Deck handDeck = speler2.getHandDeck();
        Deck discardDeck = speler2.getDiscardDeck();
        handDeck.moveAmountOfCardsToOtherDeck(3, discardDeck);
    }
    
    public void MineAbility(Speler speler,int indexTreasureCard,Pile orignalTreasurePile, Pile treasurePile){
        Deck handDeck = speler.getHandDeck();
        if(!treasurePile.isEmpty()){
        handDeck.removeCardAtIndex(indexTreasureCard);
        handDeck.addToDeck(0, treasurePile.getCard());
        treasurePile.decrementAmount();
        }
        else{
            Card treasureCard = handDeck.getCardAtIndex(indexTreasureCard);
            handDeck.addToDeck(0, treasureCard);
            orignalTreasurePile.decrementAmount();
        }
    }
    
    public void SmithyAbility(Speler speler){
        drawAmountOfCards(speler, 3);
    }
    
    public void VillageAbility(Speler speler){
        speler.actionIncrement(2);
        drawAmountOfCards(speler, 1);
    }
    
    public void WoodercutterAbility(Speler speler){
        speler.buysIncrement(1);
        speler.coinsIncrement(2);
    }
    public void WorkshopAbility(Speler speler, Pile chosenPile){
        //gains a card costing up to 4 gold
        Card card = chosenPile.getCard();
        int cost = card.getCost();
        if (cost <= 4){
            speler.getHandDeck().addToDeck(0, card);
            chosenPile.decrementAmount();
            // mogelijks if weglaten? en check buiten de functie om te controleren
        }
    }
    
    public void ChancellorAbililty(Speler speler,Boolean discardHand){
        speler.coinsIncrement(2);
        if(discardHand){
            speler.getHandDeck().moveAllCardsToOtherDeck(speler.getDiscardDeck());
        }
    }
        
    public void CopperAbility(Speler speler){
        speler.coinsIncrement(1);
    }
    
    public void SilverAbility(Speler speler){
        speler.coinsIncrement(2);
    }
    
    public void GoldAbility(Speler speler){
        speler.coinsIncrement(3);
    }
    
    public void EstateAbility(Speler speler){
        speler.victoryPointsIncrement(1);
    }
    
    public void DuchyAbility(Speler speler){
        speler.victoryPointsIncrement(3);
    }
    
    public void ProvinceAbilty(Speler speler){
        speler.victoryPointsIncrement(6);
    }
       
}
