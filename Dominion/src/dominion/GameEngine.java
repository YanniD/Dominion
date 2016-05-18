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
    private boolean reactionInGame;
    private Speler currentSpeler;
    private LinkedList<Speler> Spelers;
    private LinkedList<Card> playedCards;
    private ArrayList<Pile> allPiles;
    private ArrayList<Speler> winner;
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
     
    private void checkForReactionCard(){
        Deck handDeck = currentSpeler.getHandDeck();
        for(int i = 0; i < handDeck.getLengthFromDeck(); i++){
            Card c = handDeck.getCardAtIndex(i);
            if(c.getCardID() == 5){ //cardID 5 is moat
                reactionInGame = true;
            }
        }
    }
     
    private void askForReactions(){
        for(int i = 0; i < Spelers.size(); i++){
            Speler s = Spelers.get(i);
            if(s != currentSpeler && hasReactionCardsInHand(s)){
                Card reactionCard = cli.askToThrowReaction(s);
                actionHandler.executeAction(reactionCard, s);
                s.setEffected(false);
            }
        }
    }
    
    /**
     * if a reactionCard is in the game and the currentPlayer plays an actionCard
     * it will keep asking to other players with a reactionCard to throw it
     */
    private void checkForReaction(Card c){
        if(reactionInGame && c.getType() == CardType.ActionAttack){
            askForReactions();
        } else if(c.getType() == CardType.ActionReaction){
            reactionInGame = false;
        }
    }

    /**
     * executes the chosen action card from hand
     */ 
    public void playCard(int indexCard){
        Deck handDeck = currentSpeler.getHandDeck();
        Card cardToPlay = handDeck.getCardAtIndex(indexCard);
        checkForReaction(cardToPlay);
        playedCards.add(cardToPlay);
        handDeck.removeCardfromDeck(indexCard);
        currentSpeler.actionDecrement(1);
        actionHandler.executeAction(cardToPlay, currentSpeler);
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
    public boolean isCardBuyable(int coins, int indexPile){
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
        checkForReactionCard();
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
    
    private boolean hasReactionCardsInHand(Speler s){
        Deck handDeck = s.getHandDeck();
        for(int i = 0; i < handDeck.getLengthFromDeck(); i++){
            Card c = handDeck.getCardAtIndex(i);
            if(c.getType() == CardType.ActionReaction){
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
    
    public boolean isReactionCard(Speler s, int index){
        CardType c = s.getHandDeck().getCardAtIndex(index).getType();
        if (c == CardType.ActionReaction) {
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
    
    public boolean isPlayerEffected(Speler s){
        return s.isEffected();
    }
    
    public void removeCardFromHandDeck(Speler s, int indexCard){
        s.getHandDeck().removeCardfromDeck(indexCard);
    }
    
    public Card getCardFromHandDeck(Speler s, int indexCard){
        return s.getHandDeck().getCardAtIndex(indexCard);
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
    
    public int getLengthHandDeckPlayer(Speler s){
        return s.getHandDeck().getLengthFromDeck();
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