/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;

import dominion.Database.DatabaseService;
import dominion.Models.*;

public class Speler {
    private String name;
    private int playerID;
    private Deck drawDeck;
    private Deck handDeck;
    private Deck discardDeck;
    private int actions;
    private int buys;
    private int coins;
    private int victoryPoints;
    private boolean effected;
    
    //constructor
    public Speler(String name, int ID, DatabaseService dbs){
        this.name = name;
        this.playerID = ID;
        newDecks(dbs);
//        initRound();
    }
    
    private void newDecks(DatabaseService dbs){
        drawDeck = new Deck(true, dbs);
        handDeck = new Deck(false, dbs);
        discardDeck = new Deck(false, dbs);
    }
    
    public void initRound(){
        actions = 1;
        buys = 1;
        coins = 0;
    }
    
    public void setEffected(boolean b){
        effected = b;
    }
    
    //actie verminderen per actiekaart gespeeld
    public void actionDecrement(int actionDecrease){
        this.actions -= actionDecrease;
    }
    
    //naargelang de kaart actie terug vermeerderen
    public void actionIncrement(int actionIncrease){
        this.actions += actionIncrease;
    }
    
    //na aankoop kaart buy verminderen
    public void buysDecrement(int buysDecrease){
        this.buys -= buysDecrease;
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
    
    public void setVictoryPoints(int amount){
        this.victoryPoints = amount;
    }

    public boolean isEffected() {
        return effected;
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
    
}