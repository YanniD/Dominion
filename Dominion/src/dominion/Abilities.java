/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominion;
import dominion.Speler;
import dominion.Models.*;
import static dominion.Models.CardType.*;
import java.util.ArrayList;
import dominion.Console;
import java.util.Scanner;
/**
 *
 * @author Yanni
 */
public class Abilities {
    private Speler speler;

    public Abilities(Speler speler){
        this.speler = speler;
    }
    
    public void drawAmountOfCards(Speler speler, int amount){
        Deck handDeck = speler.getHandDeck();
        Deck drawDeck = speler.getDrawDeck();
        checkAmountCardsInDrawDeck(speler, amount);
        drawDeck.moveAmountOfCardsToOtherDeck(amount, handDeck);
    }
    
    private void checkAmountCardsInDrawDeck(Speler speler, int cardsNeeded){
        Deck drawDeck = speler.getDrawDeck();
        Deck discardDeck = speler.getDiscardDeck();
        if (drawDeck.getLengthFromDeck() < cardsNeeded){
            discardDeck.randomShuffle();
            discardDeck.moveAllCardsToOtherDeck(drawDeck);
        }
    }
    
    public void gainCardCostingUpTo(Pile chosenPile, int maxCost){
        Card card = chosenPile.getCard();
        int cost = card.getCost();
        if (cost <= maxCost){
            speler.getHandDeck().addToDeck(0, card);
            chosenPile.decrementAmount();
        }
    }
        
    /**
      * Use in a while loop in the CLI
      *if stop action = true then is choice card = null
      */
    public void cellarAbility(int timesDiscarded,Card choiceCard, Boolean stopAction){
        Deck handDeck = speler.getHandDeck();
        Deck discardDeck = speler.getDiscardDeck();
        Deck drawDeck = speler.getDrawDeck();
        if(!stopAction){
            handDeck.moveOneCardToOtherDeck(discardDeck,choiceCard);
            timesDiscarded +=1;
        }
        else {
           speler.actionIncrement(1);
           checkAmountCardsInDrawDeck(speler, timesDiscarded);
           drawDeck.moveAmountOfCardsToOtherDeck(timesDiscarded, handDeck);
        }     
    }
    
    public void marketAbility(Card nieuweKaart){
        speler.actionIncrement(1);
        speler.buysIncrement(1); 
        speler.coinsIncrement(1);
        drawAmountOfCards(speler, 1);
    }
    
    public void militiaAbility(Speler OtherPlayer){
        speler.coinsIncrement(2);
        Deck handDeck = OtherPlayer.getHandDeck();
        Deck discardDeck = OtherPlayer.getDiscardDeck();
        handDeck.moveAmountOfCardsToOtherDeck(3, discardDeck);
    }
    
    public void mineAbility(int indexTreasureCard,Pile orignalTreasurePile, Pile treasurePile){
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
    
    /*
    public void moatAbility(Speler speler){
        drawAmountOfCards(speler, 3);
    }
    */
    
    public void remodelAbility(Card cardToTrash){
        Deck handDeck = speler.getHandDeck();
        int cost = cardToTrash.getCost();
        handDeck.removeCardAtIndex(handDeck.getIndexOf(cardToTrash));
        Console c = new Console(speler);
        if(c.remodelConfirmation()){
            
        }
    }
    
    
    public void smithyAbility(){
        drawAmountOfCards(speler, 3);
    }
    
    public void villageAbility(){
        speler.actionIncrement(2);
        drawAmountOfCards(speler, 1);
    }
    
    public void woodercutterAbility(){
        speler.buysIncrement(1);
        speler.coinsIncrement(2);
    }
    public void workshopAbility(Pile chosenPile){
        //gains a card costing up to 4 gold
        gainCardCostingUpTo(chosenPile, 4);
        //mogelijks if weglaten? en check buiten de functie om te controleren
    }
    
    
    public void adventurerAbility(Speler speler){
        Deck drawDeck = speler.getDrawDeck();
        Deck handDeck = speler.getHandDeck();
        Deck discardDeck = speler.getDiscardDeck();
        int amountTScards = 0;
        while (amountTScards < 2) {
            Card card = drawDeck.getCardAtIndex(0);
            int cardID = card.getCardID();
            if (cardID == 25 || cardID == 26 || cardID == 27){
                drawDeck.moveOneCardToOtherDeck(handDeck, card);
                amountTScards++;
            } 
            else {
                drawDeck.moveOneCardToOtherDeck(discardDeck, card);
            }
        }      
    }
    
    public void bureaucratAbility(Speler OtherPlayer, Pile silverPile){
        if (silverPile.getAmount() > 0){
            speler.getDrawDeck().addToDeck(0, silverPile.getCard());
            silverPile.decrementAmount();
        } 
        
        Deck handDeck = OtherPlayer.getHandDeck();
        for (int i = 0; i < handDeck.getLengthFromDeck(); i++) {
            Card card = handDeck.getCardAtIndex(i);
            if (card.getCardID() != 28 && card.getCardID() != 29 && card.getCardID() != 30) {
                handDeck.moveOneCardToOtherDeck(OtherPlayer.getDrawDeck(), card);
            } 
            // else reveal whole handDeck in CLI
        }
    }
    
    public void chancellorAbililty(Boolean discardHand){
        speler.coinsIncrement(2);
        if(discardHand){
            speler.getHandDeck().moveAllCardsToOtherDeck(speler.getDiscardDeck());
        }
    }
    
    public void chapelAbililty(ArrayList<Card> cards2Trash) {
        Deck handDeck = speler.getHandDeck();
        for (int i = 0; i < cards2Trash.size(); i++) {
            handDeck.removeCardAtIndex(handDeck.getIndexOf(cards2Trash.get(i)));
        }
    }
    
    public void feastAbility(Pile chosenPile){
        Deck handDeck = speler.getHandDeck();
        Card tmpFeastCard = new Card(15 , 4, "feast", Action, 10);
        handDeck.removeCardAtIndex(handDeck.getIndexOf(tmpFeastCard));
        gainCardCostingUpTo(chosenPile, 5);
    }
    
    public void laboratoryAbility() {
        drawAmountOfCards(speler, 2);
        speler.actionIncrement(1);
    }
        
    public void moneylenderAbility() {
        Deck handDeck = speler.getHandDeck();
        Card tmpCopperCard = new Card(25 , 4, "copper", Treasure, 60);
        for(int i = 0; i < handDeck.getLengthFromDeck(); i++){
            if (handDeck.getCardAtIndex(i) == tmpCopperCard){
                handDeck.removeCardAtIndex(i);
                speler.coinsIncrement(3);
            }
        }
    }
    
    public void throneroomAbility(Card cardToPlayTwice){
        //switch over alle kaarten en dubbel uitvoeren
    }
    
    public void councilroomAbility(Speler OtherPlayer){
        drawAmountOfCards(speler, 4);
        speler.buysIncrement(1);
        drawAmountOfCards(OtherPlayer, 1);
    }
    
    public void festivalAbility(){
        speler.actionIncrement(2);
        speler.buysIncrement(1);
        speler.coinsIncrement(2);
    }
    
    public void libraryAbility(){
        Deck handDeck = speler.getHandDeck();
        Deck drawDeck = speler.getDrawDeck();
        Deck discardDeck = speler.getDiscardDeck();
        int handDeckSize = handDeck.getLengthFromDeck();
        while (handDeckSize < 7){
            for (int i = 0; i < drawDeck.getLengthFromDeck(); i++) {
                Card card = drawDeck.getCardAtIndex(i);
                if (card.getType() == Action) {
                    Console c = new Console(speler); 
                    discardActionCard(card, c.libraryConfirmation());
                } 
                else {
                    drawDeck.moveOneCardToOtherDeck(handDeck, card);
                } 
            }
        }
    }
    
    /**
     * Part of libraryAbility 
     * ----------------------
     * player choses to discard or add actionCard to deck
     */
    public void discardActionCard(Card actionCard, boolean choiceToKeepCard) {
        Deck drawDeck = speler.getDrawDeck();
        if (choiceToKeepCard) {
            drawDeck.moveOneCardToOtherDeck(speler.getHandDeck(), actionCard);
        } else {
            drawDeck.moveOneCardToOtherDeck(speler.getDiscardDeck(), actionCard);
        }
    }
    
    public void spyAbility(Speler OtherPlayer){
        Deck drawDeck = speler.getDrawDeck();
        drawDeck.moveOneCardToOtherDeck(speler.getHandDeck(), drawDeck.getCardAtIndex(0));
        speler.actionIncrement(1);
        
        Console c = new Console(speler);
        Card cardSpeler1 = drawDeck.getCardAtIndex(0); 
        c.revealCard(speler, cardSpeler1, 1);
        Card cardSpeler2 = OtherPlayer.getDrawDeck().getCardAtIndex(0); 
        c.revealCard(OtherPlayer, cardSpeler2, 1);
        
        if (c.spyConfirmation()) {
            drawDeck.moveOneCardToOtherDeck(speler.getDiscardDeck(), drawDeck.getCardAtIndex(0));
            Deck drawDeckVictim = OtherPlayer.getDrawDeck();
            drawDeckVictim.moveOneCardToOtherDeck(OtherPlayer.getDiscardDeck(), drawDeckVictim.getCardAtIndex(0));
        }
    }
    
    public void thiefAbility(Speler OtherPlayer){
        Console c = new Console(speler);
        Deck drawDeckVictim = OtherPlayer.getDrawDeck();
        int amountTRCards = 0;
        for (int i = 0; i < 2; i++) {
            Card card = drawDeckVictim.getCardAtIndex(i);
            if (card.getType() == CardType.Treasure) {
                amountTRCards++;
            }
            c.revealCard(OtherPlayer, card, i);
        }
        int choiceCard = c.thiefCardChoice();
        if(choiceCard <= 0 &&choiceCard <3) {
            drawDeckVictim.moveOneCardToOtherDeck(speler.getHandDeck(), drawDeckVictim.getCardAtIndex(choiceCard));
        }
    } 
    
    public void witchAbility(Speler otherPlayer,Card curse){
        speler.getDrawDeck().moveAmountOfCardsToOtherDeck(2, speler.getHandDeck());
        otherPlayer.getHandDeck().addToDeck(0, curse);
    }
    
    public void copperAbility(){
        speler.coinsIncrement(1);
    }
    
    public void silverAbility(){
        speler.coinsIncrement(2);
    }
    
    public void goldAbility(){
        speler.coinsIncrement(3);
    }
    
    public void estateAbility(){
        speler.victoryPointsIncrement(1);
    }
    
    public void duchyAbility(){
        speler.victoryPointsIncrement(3);
    }
    
    public void provinceAbilty(){
        speler.victoryPointsIncrement(6);
    }
    
    public void gardensAblity() {
        int amountCards = speler.getDrawDeck().getLengthFromDeck();
        speler.victoryPointsIncrement(amountCards / 10);
    }
       
}
