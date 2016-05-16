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
import dominion.Models.CardType;
import dominion.Models.Deck;
import dominion.Models.TreasureCard;
import dominion.Models.VictoryCard;
import java.util.LinkedList;

public class GameEngine {
    private int turn;
    private ArrayList<Speler> winner; //list for draws
    private int gamePhase;
    private Speler currentSpeler;
    private LinkedList<Speler> Spelers;
    private ArrayList<Pile> allPiles;
    private LinkedList<Card> playedCards;
    private DatabaseService dbs;

    
    
    public GameEngine(){
        turn = 0;
        this.dbs = new DatabaseService();
        playedCards = new LinkedList<>();
//        initCards(chosenSet);
    }
    
    public void setPlayers(LinkedList<Speler> Spelers) {
        currentSpeler = Spelers.get(0);
        this.Spelers = Spelers;
    }

    public void initCards(int chosenSet){
        SetConfig setConf = new SetConfig();
        allPiles = new ArrayList<Pile>();
        ArrayList<Card> playCards = setConf.getGameCards(setConf.getSet(chosenSet), dbs);
        for (int i = 0; i < playCards.size(); i++){
            Pile pileToAdd = new Pile(dbs.FindCardByID(playCards.get(i).getCardID()));
            allPiles.add(pileToAdd);
        }
//        dbs.close();
    }
    
    /**
     * return true if province pile is empty or 3 kingdomcard piles are empty 
     */
    public boolean finished(){
        int indexProvinceCard = 15;
        if(allPiles.get(indexProvinceCard).isEmpty() || checkKingdomCards()){
            endGame();
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
        handDeck.removeCardfromDeck(indexCard);
        currentSpeler.actionDecrement();
    }
    
    public void buyCard(int indexPile){
        if (!allPiles.get(indexPile).isEmpty()){
            Deck handDeck = currentSpeler.getHandDeck();
            Card cardToBuy = allPiles.get(indexPile).getCard();
            handDeck.addToDeck(0, cardToBuy);
            allPiles.get(indexPile).decrementAmount();
            currentSpeler.buysDecrement();
        }
    }
     
    public void initTurn(boolean isFirstTurn){
        for(int i = 0 ; i < Spelers.size(); i++){
            Speler s = Spelers.get(i);
            s.initRound();
            Deck drawDeck = s.getDrawDeck();
            if (isFirstTurn){
                drawDeck.randomShuffle();
            }
            checkDrawDeckSizeOfPlayer(s, 5);
            drawDeck.moveAmountOfCardsToOtherDeck(5, s.getHandDeck());
        }
    }
    
    /**
     * if drawdeck size of given player is smaller than sizeNeeded 
     * it will shuffle the player's discarddeck and move all the cards to his drawdeck
     */
    public void checkDrawDeckSizeOfPlayer(Speler s, int sizeNeeded){
        Deck drawDeck = s.getDrawDeck();
        if (drawDeck.getLengthFromDeck() < sizeNeeded){
            Deck discardDeck = s.getDiscardDeck();
            discardDeck.randomShuffle();
            discardDeck.moveAllCardsToOtherDeck(drawDeck);
        }
    }
    
    public boolean currentPlayerHasActionCards() {
        Deck handDeck = currentSpeler.getHandDeck();
        for(int i = 0; i < handDeck.getLengthFromDeck(); i++) {
            CardType cardToCheck = handDeck.getCardAtIndex(i).getType();
            if (cardToCheck == CardType.Action || cardToCheck == CardType.ActionAttack){
                return true;
            }
        }
        return false;
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
        for (int i = 0; i < Spelers.size(); i++){
            Deck handDeck = Spelers.get(i).getHandDeck();
            Deck discardDeck = Spelers.get(i).getDiscardDeck();
            handDeck.moveAllCardsToOtherDeck(discardDeck);
        }
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
        int currentSpelerIndex = Spelers.indexOf(currentSpeler);
        int nextSpelerIndex = (currentSpelerIndex + 1) % 2;
        currentSpeler = Spelers.get(nextSpelerIndex);
        cleanUpPlayedCards();
    }
    
    public void nextTurn(){
        cleanUpHands();
        turn++;
        initTurn(false);
    }
    
    private void currentPlayerUpdateCoins() {
        int amount = 0;
        Deck handDeck = currentSpeler.getHandDeck();
        for(int i = 0; i < handDeck.getLengthFromDeck(); i++){
            if (handDeck.getCardAtIndex(i) instanceof TreasureCard){
                TreasureCard card = (TreasureCard) handDeck.getCardAtIndex(i); //CASTING?
                amount += card.getWorth();
            }
        }
        currentSpeler.coinsIncrement(amount);
    }
    
    public void endGame(){
        //move all cards from one player to his drawDeck --> count all VPcards + points --> set winner
        for(int i = 0; i < Spelers.size(); i++){
            Speler s = Spelers.get(i);
            Deck allCards = s.getDrawDeck();
            Deck handDeck = s.getHandDeck();
            Deck discardDeck = s.getDiscardDeck();
            handDeck.moveAllCardsToOtherDeck(allCards);
            discardDeck.moveAllCardsToOtherDeck(allCards);
            calculateVictoryPoints(s, allCards);
        }
        setWinner(searchPlayerHighestVP());
    }
    
    private void calculateVictoryPoints(Speler s, Deck allCards){
        int Vpoints = 0;
        for(int i = 0; i < allCards.getLengthFromDeck(); i++){
            Card c = allCards.getCardAtIndex(i);
            if(c instanceof VictoryCard){
                VictoryCard Vcard = (VictoryCard) c; //CASTING?
                Vpoints += Vcard.getVictoryPoints();
                System.out.println("Vcard VP: " + Vcard.getVictoryPoints());
            }
        }
        System.out.println("Vpoints: " + Vpoints);
        s.setVictoryPoints(Vpoints);
    }
    
    private ArrayList<Speler> searchPlayerHighestVP(){
        ArrayList<Speler> winners = new ArrayList<>();
        int highestVP = Spelers.get(0).getVictoryPoints();
        for(int i = 0; i < Spelers.size(); i++){
            Speler s = Spelers.get(i);
            if (s.getVictoryPoints() > highestVP){
                winners.set(0, s);
            } else if (s.getVictoryPoints() == highestVP){ //if draw
                winners.add(s);
            }
        }
        return winners;
    }
    
    /**
     * used by askAction in ConsoleGame
     */
    public boolean isActionCard(int index){
        CardType c = currentSpeler.getHandDeck().getCardAtIndex(index).getType();    
        if (c == CardType.Action || c == CardType.ActionAttack) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * used by askBuy in ConsoleGame
     */
    public boolean isPileEmpty(int indexPile) {
        if(allPiles.get(indexPile).isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    
    private void setWinner(ArrayList<Speler> s){
        winner = s;
    }
    
    public int getCurrentPlayerCoins(){
        currentPlayerUpdateCoins();
        return currentSpeler.getCoins();
    }
    
    public int getLengthHandDeckOfCurrentPlayer(){
        return currentSpeler.getHandDeck().getLengthFromDeck();
    }
    
    public Speler getLastSpeler(){
        return Spelers.getLast();
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
    
    public LinkedList<Speler> getSpelers(){
        return Spelers;
    }

    public ArrayList<Pile> getPiles() {
        return allPiles;
    }

    public DatabaseService getDbs() {
        return dbs;
    }

    public ArrayList<Speler> getWinner() {
        return winner;
    }
}