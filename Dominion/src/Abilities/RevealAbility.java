///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Abilities;
//
//import dominion.Models.Card;
//import dominion.Models.CardType;
//import dominion.Models.Deck;
//import dominion.Speler;
//import java.util.ArrayList;
//
//public class RevealAbility extends CardAbility{
//    
//    public RevealAbility(String name) {
//        super(name);
//    }
//        
//    /**
//     * Keep drawing cards until you have enough type of cards , for example you need to keep drawing until you get 2 Treassure cards , then this method stops
//     * @param speler
//     * @param amountCardsNeeded
//     * @param cardType 
//     */
//    public void revealCardsUntilAmountTypeOfCards(Speler speler ,int amountCardsNeeded, CardType cardType){  //need a better name
//        int amountTypeOfCard = 0;
//        Deck drawDeck = getDrawDeck(speler);
//        Deck handDeck = getHandDeckOfPlayer(speler);
//        Deck discardDeck = getDiscardDeck(speler);
//        while (amountTypeOfCard < amountCardsNeeded && drawDeck.getLengthFromDeck() > 0){
//            Card firstCard = drawDeck.getCardAtIndex(0);
//            if (firstCard.getType() == cardType){
//                drawDeck.moveOneCardToOtherDeck(handDeck, firstCard);
//                amountTypeOfCard++;
//            } 
//            else{
//                drawDeck.moveOneCardToOtherDeck(discardDeck, firstCard);
//            }
//            // reveal TS card with console?
//        }
//        
//    }    
//    
//    public void revealTypeOfCardAndPutInDeck(Speler PlayerReveals,CardType typeOfCard){
//        Deck handDeck = getHandDeckOfPlayer(PlayerReveals);
//        ArrayList<Card> typeCards = new ArrayList<Card>();
//        for (int i = 0; i < handDeck.getLengthFromDeck(); i++){
//            Card card = handDeck.getCardAtIndex(i);
//            if (card.getType() == typeOfCard){
//                typeCards.add(card);
//            }
//            // Need more work , conssole problem
//        }
//    }
//
//}