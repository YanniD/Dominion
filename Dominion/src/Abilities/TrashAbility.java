///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Abilities;
//
//import dominion.Models.Card;
//import dominion.Models.Deck;
//import dominion.Speler;
//
//
//public class TrashAbility extends CardAbility{
//    
//    public TrashAbility(String name) {
//        super(name);
//    }
//    
//    public void trashCard(Speler speler, Card card){
//        Deck handDeck = getHandDeckOfPlayer(speler);
//        handDeck.removeCardAtIndex(handDeck.getIndexOf(card));
//    }
//    
//    public void trashCardFromHand(Speler speler, Card cardToTrash){
//        Deck handDeck = getHandDeckOfPlayer(speler);
//        for(int i = 0; i < handDeck.getLengthFromDeck(); i++){
//            if (handDeck.getCardAtIndex(i) == cardToTrash){
//                handDeck.removeCardAtIndex(i);
//            }
//        }
//    }
//    
//}
