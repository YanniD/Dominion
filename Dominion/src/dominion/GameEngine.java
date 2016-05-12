/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;
import java.util.ArrayList;
import dominion.Models.SetConfig;
import dominion.Models.Card;
import dominion.Models.Pile;
import dominion.Database.DatabaseService;
import dominion.Models.Deck;
import java.util.LinkedList;

public class GameEngine {
    private int turn;
    private int gamePhase;
    private Speler currentSpeler;
    private ArrayList<Speler> Spelers;
    private ArrayList<Pile> allPiles;
    private LinkedList<Card> playedCards;
    private DatabaseService dbs;

    
    
    public GameEngine(DatabaseService dbs, int chosenSet){
        turn = 0;
        this.dbs = dbs;
        initCards(chosenSet);
    }
    
    public void setPlayers(ArrayList<Speler> Spelers) {
        currentSpeler = Spelers.get(0);
        this.Spelers = Spelers;
    }

    private void initCards(int chosenSet){
        SetConfig setConf = new SetConfig();
        allPiles = new ArrayList<Pile>();
        ArrayList<Card> playCards = setConf.getGameCards(setConf.getSet(chosenSet), dbs);
        for (int i = 0; i < playCards.size(); i++){
            Pile pileToAdd = new Pile(dbs.FindCardByID(playCards.get(i).getCardID()));
            allPiles.add(pileToAdd);
        }
        dbs.close();
    }
    
    /**
     * return true if province pile is empty or 3 kingdomcard piles are empty 
     */
    public boolean finished(){
        int indexProvinceCard = 15;
        if(allPiles.get(indexProvinceCard).isEmpty() || checkKingdomCards()){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * if 3 kingdomcard piles are empty --> true
     */ private boolean checkKingdomCards() {
        int amountPilesEmpty = 0;
        int i = 0;
        while(i < 10 && amountPilesEmpty < 3) {
            if(allPiles.get(i).isEmpty()){
                amountPilesEmpty++;
            }
            i++;
        }
        if(amountPilesEmpty > 3) {
            return true;
        } else {
            return false;
        }
    }
    
    public void playCard(int indexCard){
        Deck handDeck = currentSpeler.getHandDeck();
        Card cardToPlay = handDeck.getCardAtIndex(indexCard);
        playedCards.add(cardToPlay);
        handDeck.removeCardAtIndex(indexCard);
        currentSpeler.actionDecrement();
    }
     
    public void initFirstRound(){
        for(int i = 0 ; i < Spelers.size(); i++){
            Deck drawDeck = Spelers.get(i).getDrawDeck();
            drawDeck.randomShuffle();
            drawDeck.moveAmountOfCardsToOtherDeck(5, Spelers.get(i).getHandDeck());
        }
    }
    
    private void nextSpeler(){
        int currentSpelerIndex = Spelers.indexOf(currentSpeler);
        int nextSpelerIndex = (currentSpelerIndex + 1) % 2;
        currentSpeler = Spelers.get(nextSpelerIndex);
    }

    private void cleanUpPlayedCards() {
        Deck discardDeck = currentSpeler.getDiscardDeck();
        while(!playedCards.isEmpty()){
            Card card = playedCards.getFirst();
            discardDeck.addToDeck(0, card);
            playedCards.removeFirst();
        }
    }
    
    private void cleanUpHands(){
        Deck handDeck = currentSpeler.getHandDeck();
        Deck discardDeck = currentSpeler.getDiscardDeck();
        handDeck.moveAllCardsToOtherDeck(discardDeck);
    }

    /**
     * phase 0: Action
     * phase 1: Buy
     * phase 2: Cleanup
     */
    public void nextPhase(){
        int currentPhase = gamePhase;
        int nextPhase = currentPhase + 1;
        if(nextPhase == 2){
            cleanUpPlayedCards();
        }
        gamePhase = nextPhase % 3;
    }
    
    /**
     * turn increments and next player becomes currentPlayer
     */
    public void nextPlayer(){
        nextSpeler();
        cleanUpPlayedCards();
    }
    
    public void nextTurn(){
        cleanUpHands();
        turn++;
    }
    
    public Speler getCurrentSpeler(){
        return currentSpeler;
    }
    
    public int getTurn(){
        return turn;
    }
    
    public int getGamePhase() {
        return gamePhase;
    }
    
    public ArrayList<Speler> getSpelers(){
        return Spelers;
    }

    public ArrayList<Pile> getPiles() {
        return allPiles;
    }
}