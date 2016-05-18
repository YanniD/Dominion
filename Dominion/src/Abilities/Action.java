/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abilities;

import dominion.GameEngine;
import dominion.Models.Deck;
import dominion.Speler;

public class Action {
    private GameEngine engine;
    private Speler actionPlayer;
    
    public Action(GameEngine ge){
        this.engine = ge;
        this.actionPlayer = engine.getCurrentSpeler();
    }
    
    public void drawAmountOfCards(int amount){
        Deck handDeck = actionPlayer.getHandDeck();
        Deck drawDeck = actionPlayer.getDrawDeck();
        drawDeck.moveAmountOfCardsToOtherDeck(amount, handDeck);
    }
    
    public void trashCard(int indexCard){
        actionPlayer.getHandDeck().removeCardfromDeck(indexCard);
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
    
}
