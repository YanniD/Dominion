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

public class Action {
    private GameEngine engine;
    private Speler actionPlayer;
    
    public Action(GameEngine ge, Speler cardPlayer){
        this.engine = ge;
        this.actionPlayer = cardPlayer;
    }
    
    public void drawAmountOfCards(int amount){
        Deck handDeck = actionPlayer.getHandDeck();
        Deck drawDeck = actionPlayer.getDrawDeck();
        drawDeck.moveAmountOfCardsToOtherDeck(amount, handDeck);
    }
    
    public void trashCard(Card c){
        actionPlayer.getHandDeck().removeCardFromDeck(c);
    }
    
    public void actionIncrement(int increaseAmount){
        actionPlayer.actionIncrement(increaseAmount);
    }
    
    public void buysIncrement(int increaseAmount){
        actionPlayer.buysIncrement(increaseAmount);
    }
    
    public void coinsIncrement(int increaseAmount){
        actionPlayer.coinsIncrement(increaseAmount);
    }
    
    public Deck getHandDeckOfPlayer(){
        return actionPlayer.getHandDeck();
    }
    
    public Deck getDiscardDeckOfPlayer(){
        return actionPlayer.getDiscardDeck();
    }
    
    public Deck getDrawDeckOfPlayer(){
        return actionPlayer.getDrawDeck();
    }
    
    public GameEngine getEngine(){
        return engine;
    }

    public Speler getActionPlayer() {
        return actionPlayer;
    }
    
    public void revealCard(Speler s, Card c){
        engine.getCli().revealCard(s, c);
    }
    
    public void trashSpecifiedAmountOfCards(int amount){
        for (int i = 0 ; i <amount ; i++){
            Card chosenCardToTrash = engine.getCli().chooseACardToTrash();
            trashCard(chosenCardToTrash);
        }
    }
    
    
}
