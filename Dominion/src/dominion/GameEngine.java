/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;
import Abilities.Action;
import Abilities.ActionHandler;
import Abilities.SpecialAction;
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
    private ConsoleGame cli;
    private ActionHandler actionHandler;
    private int turn;
    private ArrayList<Speler> winner; //list makes draws possible
    private Speler currentSpeler;
    private LinkedList<Speler> Spelers;
    private LinkedList<Card> playedCards;
    private ArrayList<Pile> allPiles;
    private DatabaseService dbs;
    
    public GameEngine(){
        turn = 0;
        this.dbs = new DatabaseService();
        playedCards = new LinkedList<>();
        actionHandler = new ActionHandler(this);
    }
    
    public void setCLIGameEngine(ConsoleGame consoleGame){
        cli = consoleGame;
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
    
    /**
     * executes the chosen action card from hand
     */ 
    public void playCard(int indexCard){
        Deck handDeck = currentSpeler.getHandDeck();
        Card cardToPlay = handDeck.getCardAtIndex(indexCard);
        playedCards.add(cardToPlay);
        handDeck.removeCardfromDeck(indexCard);
        currentSpeler.actionDecrement(1);
        actionHandler.executeAction(cardToPlay);
    }
    
    public void endActionPhase(){
        currentSpeler.actionDecrement(currentSpeler.getActions());
    }
    
    public void endBuyPhase(){
        currentSpeler.buysDecrement(currentSpeler.getBuys());
    }
    
    /**
     * executes the buy of the chosen card
     */
    public void initBuy(int indexPile){
        if (!allPiles.get(indexPile).isEmpty()){
            Deck handDeck = currentSpeler.getHandDeck();
            Pile chosenPile = allPiles.get(indexPile);
            Card cardToBuy = chosenPile.getCard();
            handDeck.addToDeck(0, cardToBuy);
            allPiles.get(indexPile).decrementAmount();
            currentSpeler.coinsDecrement(cardToBuy.getCost());
            currentSpeler.buysDecrement(1);
        }
    }
    
    /**
     * returns true if coins >= cost of card
     * returns false if not
     */
    public boolean isCardBuyable(int indexPile){
        int coins = currentSpeler.getCoins();
        int cost = allPiles.get(indexPile).getCard().getCost();
        if(coins >= cost){
            return true;
        }
        return false;
    }
     
    /**
     * all players draw 5 cards from drawdeck into hand
     * @param isFirstTurn is true if it's the first turn of the game
     * else it should be false
     */
    public void initFirstTurn(){
        for(int i = 0 ; i < Spelers.size(); i++){
            Speler s = Spelers.get(i);
            s.initRound();
            Deck drawDeck = s.getDrawDeck();
            drawDeck.randomShuffle();
            drawDeck.moveAmountOfCardsToOtherDeck(5, s.getHandDeck());
            updateCoinsOfPlayer(s);
        }
    }
    
    public void initNextTurn(){
        cleanUpPlayedCards();
        cleanUpHands();
        currentSpeler.initRound();
        checkDrawDeckSizeOfPlayer(currentSpeler, 5);
        currentSpeler.getDrawDeck().moveAmountOfCardsToOtherDeck(5, currentSpeler.getHandDeck());
        updateCoinsOfPlayer(currentSpeler);
        
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
    
    /**
     * return true if currentPlayer has action cards in hand
     * else false
     */
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
    
    public boolean currentPlayerHasActions(){
        if(currentSpeler.getActions() > 0){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean currentPlayerHasBuys(){
        if(currentSpeler.getBuys()> 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean currentPlayerHasCoins(){
        if(currentSpeler.getCoins() > 0){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * used by nextPlayer
     */
    private void cleanUpPlayedCards() {
        Deck discardDeck = currentSpeler.getDiscardDeck();
        while(!playedCards.isEmpty()){
            Card card = playedCards.getFirst();
            discardDeck.addToDeck(0, card);
            playedCards.removeFirst();
        }
    }
    
    /**
     * used by nextTurn
     */
    private void cleanUpHands(){
        Deck handDeck = currentSpeler.getHandDeck();
        Deck discardDeck = currentSpeler.getDiscardDeck();
        handDeck.moveAllCardsToOtherDeck(discardDeck);
//        for (int i = 0; i < Spelers.size(); i++){
//            Deck handDeck = Spelers.get(i).getHandDeck();
//            Deck discardDeck = Spelers.get(i).getDiscardDeck();
//            handDeck.moveAllCardsToOtherDeck(discardDeck);
//        }
    }
    
    /**
     * turn increments and next player becomes currentPlayer
     */
    public void nextPlayer(){
        initNextTurn();
        int currentSpelerIndex = Spelers.indexOf(currentSpeler);
        int nextSpelerIndex = (currentSpelerIndex + 1) % 2;
        currentSpeler = Spelers.get(nextSpelerIndex);
    }
    
    public void nextTurn(){
        turn++;
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
    
    /**
     * used by endGame method
     */
    private void calculateVictoryPoints(Speler s, Deck allCards){
        int Vpoints = 0;
        for(int i = 0; i < allCards.getLengthFromDeck(); i++){
            Card c = allCards.getCardAtIndex(i);
            if(c instanceof VictoryCard){
                VictoryCard Vcard = (VictoryCard) c;
                if(Vcard.getCardID() == 23){ //CardID 23 is gardens
//                    SpecialVictoryCard.gardens();
                }
                Vpoints += Vcard.getVictoryPoints();
            }
        }
        s.setVictoryPoints(Vpoints);
    }
    
    /**
     * used by endGame method
     */
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
    
    public boolean isTreasureCard(int index){
        CardType c = currentSpeler.getHandDeck().getCardAtIndex(index).getType();    
        if (c == CardType.Treasure) {
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
    
    private void updateCoinsOfPlayer(Speler s) {
        int amount = 0;
        Deck handDeck = s.getHandDeck();
        for(int i = 0; i < handDeck.getLengthFromDeck(); i++){
//            TreasureCard c = (handDeck.getCardAtIndex(i);
            if (handDeck.getCardAtIndex(i) instanceof TreasureCard){
                TreasureCard card = (TreasureCard) handDeck.getCardAtIndex(i); //CASTING?
                amount += card.getWorth();
            }
        }
        s.coinsIncrement(amount);
    }
    
//    private void executeAction(Card c){
//        switch(c.getCardID()){
//            case 1:
//                SpecialAction cellar = new SpecialAction(this, 1, "cellar");
//                cellar.actionIncrement(1);
//                cellar.discardForNewCard(askDiscardedCards());
//                break;
//            case 2:
//                Action market = new Action(this, 2, "market");
//                market.drawAmountOfCards(1);
//                market.actionIncrement(1);
//                market.buysIncrement(1);
//                market.coinsIncrement(1);
//                break;
//        }
//    }
    
    public void removeCardFromHandDeckCurrentPlayer(int indexCard){
        currentSpeler.getHandDeck().removeCardfromDeck(indexCard);
    }
    
    public Card getCardFromHandDeckCurrentPlayer(int indexCard){
        return currentSpeler.getHandDeck().getCardAtIndex(indexCard);
    }

    public ConsoleGame getCli(){
        return cli;
    }
    
    public int getCurrentPlayerAcions(){
        return currentSpeler.getActions();
    }
    
    public int getCurrentPlayerBuys(){
        return currentSpeler.getBuys();
    }
    
    public int getCurrentPlayerCoins(){
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