///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package Abilities;
//
//import dominion.ConsoleGame;
//import dominion.Models.*;
//import dominion.Speler;
//import static dominion.Models.CardType.*;
//import java.util.ArrayList;
//
//public class CardAbility {
//    private String name;
//    
//    public CardAbility(String name){
//        this.name = name;
//    }
//    
//    public void drawAmountOfCards(Speler speler, int amount){
//        Deck handDeck = getHandDeckOfPlayer(speler);
//        Deck drawDeck = getDrawDeckOfPlayer(speler);
//        checkAmountCardsInDrawDeck(speler, amount);
//        drawDeck.moveAmountOfCardsToOtherDeck(amount, handDeck);
//    }
//    
////    public void checkAmountCardsInDrawDeck(Speler speler, int cardsNeeded){ // this function is implemented in GameEngine
////        Deck drawDeck = getDrawDeckOfPlayer(speler);
////        Deck discardDeck = speler.getDiscardDeck();
////        if (drawDeck.getLengthFromDeck() < cardsNeeded){
////            discardDeck.randomShuffle();
////            discardDeck.moveAllCardsToOtherDeck(drawDeck);
////        }
////    }
//    
//    public void gainCardCostingUpTo(Speler speler, Pile chosenPile, int maxCost){
//        Card card = chosenPile.getCard();
//        int cost = card.getCost();
//        if (cost <= maxCost){
//            speler.getHandDeck().addToDeck(0, card);
//            chosenPile.decrementAmount();
//        }
//    }
//    
//    public void actionIncrement(Speler speler, int increaseAmount){
//        speler.actionIncrement(increaseAmount);
//    }
//    
//    public void buysIncrement(Speler speler, int increaseAmount){
//        speler.buysIncrement(increaseAmount);
//    }
//    
//    public void coinsIncrement(Speler speler,int increaseAmount){
//        speler.coinsIncrement(increaseAmount);
//    }
//    
//    public Deck getHandDeckOfPlayer(Speler speler){
//        return speler.getHandDeck();
//    }
//    
//    public Deck getDiscardDeckOfPlayer(Speler speler){
//        return speler.getDiscardDeck();
//    }
//    
//    public Deck getDrawDeckOfPlayer(Speler speler){
//        return speler.getDrawDeck();
//    }
//    
//   
//        
//
//            
//    /**
//      * Use in a while loop in the CLI
//      *if stop action = true then is choice card = null
//      */
//    
//    public void cellarAbility(Speler speler, int timesDiscarded,Card choiceCard, Boolean stopAction){
//        Deck handDeck = getHandDeckOfPlayer(speler);
//        Deck discardDeck = getDiscardDeckOfPlayer(speler);
//        Deck drawDeck = getDrawDeckOfPlayer(speler);
//        if(!stopAction){
//            handDeck.moveOneCardToOtherDeck(discardDeck,choiceCard);
//            timesDiscarded +=1;
//        }
//        else {
//           speler.actionIncrement(1);
//           checkAmountCardsInDrawDeck(speler, timesDiscarded);
//           drawDeck.moveAmountOfCardsToOtherDeck(timesDiscarded, handDeck);
//        }     
//    }
//    
//    public void marketAbility(Speler speler, Card nieuweKaart){
//        actionIncrement(speler, 1);
//        buysIncrement(speler, 1);
//        coinsIncrement(speler, 1);
//        drawAmountOfCards(speler, 1);
//    }
//    
//    public void militiaAbility(Speler speler, Speler otherPlayer){
//        coinsIncrement(speler, 2);
//        Deck handDeck = getHandDeckOfPlayer(otherPlayer);
//        Deck discardDeck = otherPlayer.getDiscardDeck();
//        handDeck.moveAmountOfCardsToOtherDeck(3, discardDeck);
//    }
//    
////    public void mineAbility(Speler speler){
////        Deck handDeck = speler.getHandDeck();
////        c.showHand(speler);
////        Card trashCard = c.minePickCardToTrash(speler);
////        while(trashCard.getType() != CardType.Treasure) {
////            trashCard = c.minePickCardToTrash(speler);
////        }        
////        int cost = trashCard.getCost();
////        Card gainCard = c.minePickCardToGain(speler);
////    }
//    
//
//    public void moatAbility(Speler speler, Speler speler2){
//        drawAmountOfCards(speler, 2);
//        // rest of ability coded in game engine
//    }
//    
//    public void remodelAbility(Speler speler){
//        Deck handDeck = getHandDeckOfPlayer(speler);
//        Card cardToTrash = handDeck.getCardAtIndex(c.remodelPickTrashCard(speler));
//        int cost = cardToTrash.getCost();
//        handDeck.removeCardfromDeck(handDeck.getIndexOf(cardToTrash));
//        c.showAvaileblePiles();
//        Card cardToGain = c.remodelPickGainCard(cost).getCard();
//        handDeck.addToDeck(0, cardToGain);
//    }
//    
//    
//    public void smithyAbility(Speler speler){
//        drawAmountOfCards(speler, 3);
//    }
//    
//    public void villageAbility(Speler speler){
//        actionIncrement(speler, 2);
//        drawAmountOfCards(speler, 1);
//    }
//    
//    public void woodercutterAbility(Speler speler){
//        buysIncrement(speler, 1);
//        coinsIncrement(speler, 2);
//    }
//    
//    public void workshopAbility(Speler speler){
//        gainCardCostingUpTo(speler, c.workshopPickCard(), 4);
//    }
//
//    
//    /*
//    public void adventurerAbility(Speler speler){
//        Deck drawDeck = speler.getDrawDeck();
//        Deck handDeck = speler.getHandDeck();
//        Deck discardDeck = speler.getDiscardDeck();
//        int amountTScards = 0;
//        while (amountTScards < 2 && drawDeck.getLengthFromDeck() > 0) {
//            Card card = drawDeck.getCardAtIndex(0);
//            if (card.getType() == CardType.Treasure){
//                drawDeck.moveOneCardToOtherDeck(handDeck, card);
//                amountTScards++;
//            } else {
//                drawDeck.moveOneCardToOtherDeck(discardDeck, card);
//            }
//            //reveal ts cards
//        }      
//    }
//    */
//
//    
//    public void bureaucratAbility(Speler speler, Speler OtherPlayer, Pile silverPile){
//        if (!silverPile.isEmpty()){
//            speler.getDrawDeck().addToDeck(0, silverPile.getCard());
//            silverPile.decrementAmount();
//        } 
//        
//        Deck handDeck = getHandDeckOfPlayer(OtherPlayer);
//        ArrayList<Card> Vcards = new ArrayList<Card>();
//        for (int i = 0; i < handDeck.getLengthFromDeck(); i++) {
//            Card card = handDeck.getCardAtIndex(i);
//            if (card.getType() == CardType.Victory) {
//                Vcards.add(card);
//            } 
//        }
//        
//        if (Vcards.size() > 1){
//            Card chosenVcard = Vcards.get(c.bureaucratCardChoice(OtherPlayer, Vcards));
//            handDeck.moveOneCardToOtherDeck(OtherPlayer.getDrawDeck(), chosenVcard);
//        } else if (Vcards.size() == 1) {
//            handDeck.moveOneCardToOtherDeck(OtherPlayer.getDrawDeck(), Vcards.get(0));
//        } else {
//            for (int i = 0; i < Vcards.size(); i++){
//                c.revealCard(OtherPlayer, handDeck.getCardAtIndex(i), i);
//            }
//        }
//    }
//    
//    public void chancellorAbililty(Speler speler){
//        speler.coinsIncrement(2);
//        if(c.chancellorConfirmation()){
//            speler.getHandDeck().moveAllCardsToOtherDeck(speler.getDiscardDeck());
//        }
//    }
//    
//    public void chapelAbililty(Speler speler) {
//        Deck handDeck = speler.getHandDeck();
//        boolean stop = false;
//        int i = 4;
//        while (!stop && i > 0) {
//            Card card2Trash = c.chapelTrashCard(speler);
//            handDeck.removeCardAtIndex(handDeck.getIndexOf(card2Trash));
//            i--;
//        }
//    }
//    
//    public void feastAbility(Speler speler){
//        Deck handDeck = speler.getHandDeck();
//        Card tmpFeastCard = new Card(15 , 4, "feast", Action, 10);
//        handDeck.removeCardAtIndex(handDeck.getIndexOf(tmpFeastCard));
//        c.showAvaileblePiles();
//        Pile chosenPile = c.feastPickCard();
//        gainCardCostingUpTo(speler, chosenPile, 5);
//    }
//    
//    public void laboratoryAbility(Speler speler) {
//        drawAmountOfCards(speler, 2);
//        speler.actionIncrement(1);
//    }
//        
//
//    
//    public void moneylenderAbility(Speler speler) {
//        Deck handDeck = speler.getHandDeck();
//        Card tmpCopperCard = new Card(25 , 4, "copper", Treasure, 60);
//        for(int i = 0; i < handDeck.getLengthFromDeck(); i++){
//            if (handDeck.getCardAtIndex(i) == tmpCopperCard){
//                handDeck.removeCardAtIndex(i);
//                speler.coinsIncrement(3);
//            }
//        }
//    }
//    
//    public void throneroomAbility(Speler speler){
//        //c.throneroomPickCardtoPlayTwice(speler);
//        
//    }
//    
//    public void councilroomAbility(Speler speler, Speler OtherPlayer){
//        drawAmountOfCards(speler, 4);
//        speler.buysIncrement(1);
//        drawAmountOfCards(OtherPlayer, 1);
//    }
//    
//    public void festivalAbility(Speler speler){
//        speler.actionIncrement(2);
//        speler.buysIncrement(1);
//        speler.coinsIncrement(2);
//    }
//    
//    public void drawCardsUntilDeckComplete(Speler speler, int deckSizeNeeded){
//        Deck handDeck = getHandDeckOfPlayer(speler);
//        int handDeckSize = handDeck.getLengthFromDeck();
//        Deck drawDeck = getDrawDeck(speler);
//        while (handDeckSize < deckSizeNeeded){
//            Card card = drawDeck.getCardAtIndex(0);
//            drawDeck.moveOneCardToOtherDeck(handDeck, card);
//        }
//    }
//    
//    /**
//     * 
//     * This method is used to set aside cards that equals with the right cardTypes. The user has to confirm yes or no if he want to move this to the discardDeck. If no or the type is diffrent they will be moved to the handDeck
//     * @param setAside 
//     */
//    public void setAsideTypeOfCard(Speler speler,Card card, CardType cardTypeToSetAside, boolean setAside){
//        Deck handDeck = getHandDeckOfPlayer(speler);
//        Deck drawDeck = getDrawDeck(speler);
//        Deck discardDeck = getDiscardDeck(speler);
//        if (card.getType() == cardTypeToSetAside && setAside){
//            drawDeck.moveOneCardToOtherDeck(discardDeck, card);
//        }
//        else{
//            drawDeck.moveOneCardToOtherDeck(handDeck, card);
//        }
//    }
//    
//    public void libraryAbility(Speler speler){
//        Deck handDeck = speler.getHandDeck();
//        Deck drawDeck = speler.getDrawDeck();
//        Deck discardDeck = speler.getDiscardDeck();
//        int handDeckSize = handDeck.getLengthFromDeck();
//        while (handDeckSize < 7){
//            for (int i = 0; i < drawDeck.getLengthFromDeck(); i++) {
//                Card card = drawDeck.getCardAtIndex(i);
//                if (card.getType() == Action) {
//                    discardActionCard(speler, card, c.libraryConfirmation());
//                } 
//                else {
//                    drawDeck.moveOneCardToOtherDeck(handDeck, card);
//                } 
//            }
//        }
//    }
//    
//    /**
//     * Part of libraryAbility 
//     * ----------------------
//     * player choses to discard or add actionCard to deck
//     */
//    
//    //I dont think this is still needed because of the setasidetypeofCard method
//    public void discardActionCard(Speler speler, Card actionCard, boolean choiceToKeepCard) {
//        Deck drawDeck = getDrawDeckOfPlayer(speler);
//        if (choiceToKeepCard) {
//            drawDeck.moveOneCardToOtherDeck(speler.getHandDeck(), actionCard);
//        } else {
//            drawDeck.moveOneCardToOtherDeck(speler.getDiscardDeck(), actionCard);
//        }
//    }
//    
//    public void spyAbility(Speler speler, Speler OtherPlayer){
//        Deck drawDeck = getDrawDeckOfPlayer(speler);
//        drawDeck.moveOneCardToOtherDeck(speler.getHandDeck(), drawDeck.getCardAtIndex(0));
//        speler.actionIncrement(1);
//        
//        Card cardSpeler1 = drawDeck.getCardAtIndex(0); 
//        c.revealCard(speler, cardSpeler1, 1);
//        Card cardSpeler2 = OtherPlayer.getDrawDeck().getCardAtIndex(0); 
//        c.revealCard(OtherPlayer, cardSpeler2, 1);
//        
//        if (c.spyConfirmation()) {
//            drawDeck.moveOneCardToOtherDeck(speler.getDiscardDeck(), drawDeck.getCardAtIndex(0));
//            Deck drawDeckVictim = getDrawDeckOfPlayer(OtherPlayer);
//            drawDeckVictim.moveOneCardToOtherDeck(OtherPlayer.getDiscardDeck(), drawDeckVictim.getCardAtIndex(0));
//        }
//    }
//    
//    public void thiefAbility(Speler speler, Speler OtherPlayer){
//        Deck drawDeckVictim = OtherPlayer.getDrawDeck();
//        int amountTRCards = 0;
//        for (int i = 0; i < 2; i++) {
//            Card card = drawDeckVictim.getCardAtIndex(i);
//            if (card.getType() == CardType.Treasure) {
//                amountTRCards++;
//            }
//            c.revealCard(OtherPlayer, card, i);
//        }
//        int choiceCard = c.thiefCardChoice();
//        if(choiceCard <= 0 &&choiceCard <3) {
// 
//            drawDeckVictim.moveOneCardToOtherDeck(speler.getHandDeck(), drawDeckVictim.getCardAtIndex(choiceCard));
//        }
//    } 
//    
//    public void witchAbility(Speler speler, Speler otherPlayer,Card curse){
//        getDrawDeckOfPlayer(speler).moveAmountOfCardsToOtherDeck(2, speler.getHandDeck());
//        otherPlayer.getHandDeck().addToDeck(0, curse);
//    }
//    
//    public void copperAbility(Speler speler){
//        speler.coinsIncrement(1);
//    }
//    
//    public void silverAbility(Speler speler){
//        speler.coinsIncrement(2);
//    }
//    
//    public void goldAbility(Speler speler){
//        speler.coinsIncrement(3);
//    }
//    
//    // implement this in GameEngine
////    public void gardensAblity(Speler speler) {
////        int amountCards = speler.getDrawDeck().getLengthFromDeck();
////        speler.victoryPointsIncrement(amountCards / 10);
////    }
//       
//}
