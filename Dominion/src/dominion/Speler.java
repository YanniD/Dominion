/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;

import dominion.Models.*;

public class Speler {
    private String name;
    private int playerID;
    private Deck drawDeck;
    private Deck handDeck;
    private Deck discardDeck;
    private Deck playedDeck;
    private int actions;
    private int buys;
    private int coins;
    private int victoryPoints;
    
    //constructor
    public Speler(String name, int ID){
        this.name = name;
        this.playerID = ID;
        newDecks();
        initRound();
    }
    
    private final void newDecks(){
        drawDeck = new Deck(true);
        handDeck = new Deck(false);
        discardDeck = new Deck(false);
    }
    
    private final void initRound(){
        victoryPoints = 0; // victoryPoints toegevoegd
        actions = 1;
        buys = 1;
        coins = 0;
    }
    
    //actie verminderen per actiekaart gespeeld
    public void actionDecrement(){
        this.actions -= 1;
    }
    
    //naargelang de kaart actie terug vermeerderen
    public void actionIncrement(int actionIncrease){
        this.actions += actionIncrease;
    }
    
    //na aankoop kaart buy verminderen
    public void buysDecrement(){
        this.buys -= 1;
    }
    
    public void buysIncrement(int buysIncrease){
        this.buys += buysIncrease;
    }
    
    //na aankoop muntkaarten coins verhogen
    public void coinsIncrement(int coinsIncrease){
        this.coins += coinsIncrease;
    }
    
    //na aankoop muntkaarten coins verlagen
    public void coinsDecrement(int coinsDecrease){
        this.coins -= coinsDecrease;
    }
    
    public void victoryPointsIncrement(int amountOfVictoryPoints){
        this.victoryPoints += victoryPoints;
    }
    
    public void victoryPointsDecrement(int amountOfVictoryPointsLost){
        this.victoryPoints -= amountOfVictoryPointsLost;
    }
    
    public String getPlayerName() {
        return name;
    }
    
    public int getPlayerID() {
        return playerID;
    }
    
    public int getActions() {
        return actions;
    }
    
    public int getBuys() {
        return buys;
    }
    
    public int getCoins() {
        return coins;
    }
    public int getVictoryPoints(){
        return victoryPoints;
    }
    
    public Deck getHandDeck(){
        return handDeck;
    }
    public Deck getDiscardDeck(){
        return discardDeck;
    }
    public Deck getDrawDeck(){
        return drawDeck;
    }

    public Deck getPlayedDeck() {
        return playedDeck;
    }
    
}