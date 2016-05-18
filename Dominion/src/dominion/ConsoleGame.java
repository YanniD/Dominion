/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;
import dominion.Database.DatabaseService;
import dominion.GameEngine;
import dominion.Models.Card;
import dominion.Models.Deck;
import dominion.Models.Pile;
import dominion.Models.SetConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ConsoleGame {
    private GameEngine engine;
    
    public void showMenu(){
        System.out.println("Dominion");
        System.out.println("----------\n");
        System.out.println("1: New Game");
        System.out.println("2: Load Game");
        System.out.println("3: Exit Game");
        System.out.println("Enter:");
        int choice = scanInt();
        while(choice < 1 && 3 < choice){
            System.out.println("Enter a valid number: ");
            choice = scanInt();
        }
        processChoice(choice);
    }
    
    public void processChoice(int userChoice) {
        if (userChoice == 1) {
            initGameEngine();
            goLoop();
        }
        else if(userChoice == 2){
            showSaveFiles();
        }
        else if (userChoice == 3){
            System.exit(0);
        }
        else {
            throw new IllegalArgumentException("Wrong input");
        }
    }
    
    private int processChoiceSet(DatabaseService dbs){
        int choicePreset = scanInt();
        while (choicePreset < 1 || 5 < choicePreset) {
            showPresets();
            System.out.println("Enter a valid number: ");
            choicePreset = scanInt();
        }
        confirmation(choicePreset, dbs);
        return choicePreset;
    }

    public void initGameEngine(){
        engine = new GameEngine();
        engine.setCLIGameEngine(this);
        engine.initCards(pickSet());
        engine.setPlayers(askPlayers());
        engine.getDbs().close();
        engine.initFirstTurn();
    }

    private void confirmation(int choicePreset, DatabaseService dbs) {
        SetConfig set = new SetConfig();
        int[] preSetCards = set.getSet(choicePreset);
        ArrayList<Card> cards = set.getCardStats(preSetCards, dbs);
        System.out.println("-----------------------");
        for(int i = 0; i < cards.size(); i++) {   
            System.out.println(cards.get(i).getTitle());
        }
        System.out.println("Do you want to use this preset? y/n ");
    }

    public void showPresets(){
        System.out.println("Choose a preset to view the kingdomcards!");
        System.out.println("-------------\n");
        System.out.println("1: First Game");
        System.out.println("2: Big Money");
        System.out.println("3: Interaction");
        System.out.println("4: Size Distortion");
        System.out.println("5: Village Square");
        System.out.println("Enter:");
    }
    
    /**
     * moet nog aangepast worden
     */
    public void showSaveFiles(){
        System.out.println("Choose file to load");
        System.out.println("-------------------\n");
        System.out.println("1: file1");
        System.out.println("2: file2");
    }
    
    public LinkedList<Speler> askPlayers(){
        LinkedList<Speler> Spelers = new LinkedList<>();
        for(int i = 0; i < 2; i++){
            Spelers.add(initPlayer(i));
        }
        return Spelers;
    }
    
    private Speler initPlayer(int spelerID){
        System.out.println("Player " + spelerID);
        System.out.println("Name: ");
        String playerName = scanString();
        Speler speler = new Speler(playerName, spelerID, engine.getDbs());
        return speler;
    } 
    
    public int pickSet(){
        DatabaseService dbs = engine.getDbs();
        showPresets();
        int set = processChoiceSet(dbs);
        String confirmPreSet = scanString();
        while ("n".equals(confirmPreSet)) {
            showPresets();
            set = processChoiceSet(dbs);
            confirmPreSet = scanString();
        }
        return set;
    }
    
    public void goLoop(){
        boolean checkIfFinished = false;
        while(!checkIfFinished || !engine.finished()){
            checkIfFinished = false;
            showHeading();
            showGameCards();
            showCurrentSituation();
            askAction(); // play card --> check ability --> execute
            askBuy(); // buy card --> check cost and coins --> add to handddeck
//            System.out.println("drawDecksize: " + Engine.getCurrentSpeler().getDrawDeck().getLengthFromDeck() + " || " + Engine.getCurrentSpeler().getDrawDeck().toString());
//            System.out.println("handDecksize: " + Engine.getCurrentSpeler().getHandDeck().getLengthFromDeck() + " || " + Engine.getCurrentSpeler().getHandDeck().toString());
//            System.out.println("discardDecksize: " + Engine.getCurrentSpeler().getDiscardDeck().getLengthFromDeck() + " || " + Engine.getCurrentSpeler().getDiscardDeck().toString());
            if (engine.getCurrentSpeler() == engine.getLastSpeler()) {
                checkIfFinished = true;
                engine.nextTurn(); // moves all handDecks to discardDecks --> everyone draws 5 new cards from drawDeck + next turn
            }
            engine.nextPlayer(); // empties playedCards + next player
        }
        showEndScreen();
        promptEnterKey();
        showMenu();
    }
    
    public void showHeading() {
        System.out.println("------------------------ TURN " + engine.getTurn() + " Player: " + engine.getCurrentSpeler().getPlayerName() + " ------------------------");
    }
    
    public void showGameCards(){
        ArrayList<Pile> Piles = engine.getPiles();
        for(int i = 0; i < Piles.size(); i++){
            Card card = Piles.get(i).getCard();
            String cardTitle = card.getTitle();
            int cardCost = card.getCost();
            int amountLeft = Piles.get(i).getAmount();
            System.out.println(i + ". " + cardTitle + " \t\t || " + " cost: " + cardCost + " \t\t || cards left: " + amountLeft );
        }
    }
    
    public void showCurrentSituation(){
        System.out.println("\nActions: " + engine.getCurrentPlayerAcions() + " || Buys : " + engine.getCurrentPlayerBuys() + " || Coins: " + engine.getCurrentPlayerCoins());
        showHand();
    }
    
    private void showHand(){
        Deck handDeck = engine.getCurrentSpeler().getHandDeck();
        System.out.print("Hand : ");
        for (int i = 0; i < handDeck.getLengthFromDeck() ; i++){
            Card card = handDeck.getCardAtIndex(i);
            String cardTitle = card.getTitle();
            System.out.print(" || " + i + ". " + cardTitle);
        }
        System.out.print("\n");
    }
   
    public void askAction(){
        while (engine.currentPlayerHasActionCards() && engine.currentPlayerHasActions()) {
            System.out.println("\nActions left: " + engine.getCurrentPlayerAcions() + " || Pick a card to play (Enter -1 to end action phase): ");
            showHand();
            int pickedCard = scanInt();
            int maxInput = engine.getLengthHandDeckOfCurrentPlayer();
            while((pickedCard < -1 || maxInput < pickedCard) || ((0 <= pickedCard && pickedCard <= maxInput) ? !engine.isActionCard(pickedCard) : false)){
                if(pickedCard < -1 || maxInput < pickedCard){
                    System.out.println("Pick a card to buy (give a correct input): ");
                } else {
                    System.out.println("Pick a ACTION card to play");
                }
                pickedCard = scanInt();
            }
            if(pickedCard == -1){
                System.out.println("Ending buy phase");
                engine.endActionPhase();
            } else {
                engine.playCard(pickedCard);
            }
        }
    }

    public void askBuy(){
        while (engine.currentPlayerHasCoins() && engine.currentPlayerHasBuys()){
            System.out.println("Buys left: " + engine.getCurrentSpeler().getBuys()+ " || Coins left: " + engine.getCurrentPlayerCoins() + " || Pick a card to buy (if you have no coins you can have a free copper. Enter -1 to end buy phase): ");
            int pickedCard = scanInt();
            while((pickedCard < -1 || 15 < pickedCard) || ((0 <= pickedCard && pickedCard <= 15) ? engine.isPileEmpty(pickedCard) : false) || ((0 <= pickedCard && pickedCard <= 15) ? !engine.isCardBuyable(pickedCard) : false)){
                if(pickedCard < -1 || 15 < pickedCard){
                    System.out.println("Pick a card to buy (give a correct input): ");
                } else if(engine.isPileEmpty(pickedCard)) {
                    System.out.println("This pile is empty please pick another card to buy: ");
                } else {
                    System.out.println("This card costs too much, please pick another card: ");
                }
                pickedCard = scanInt();
            }
            if(pickedCard == -1){
                System.out.println("Ending buy phase");
                engine.endBuyPhase();
            } else {
                engine.initBuy(pickedCard);
            }
        }
    }
        
    
    public void showEndScreen(){
        System.out.println("\n----------------------Battle End----------------------\n");
        ArrayList<Speler> winner = engine.getWinner();
        if (winner.size() == 1){
            System.out.println("Player " + winner.get(0).getPlayerName() + " has won the battle!");
            System.out.println("Victory points: " + winner.get(0).getVictoryPoints());
        } else {
            System.out.println("Draw between the following players:\n");
            for (int i = 0; i < winner.size(); i++) {
            System.out.println("Player " + winner.get(i).getPlayerName() + " Victory points: " + winner.get(i).getVictoryPoints());
            }
        }
    }
    
/*--------------------------------------------*/
/*------------------SCANNERS------------------*/  
/*--------------------------------------------*/ 
    
    public int scanInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    
    public String scanString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
/*--------------------------------------------*/
/*------------ACTION Communication------------*/  
/*--------------------------------------------*/ 
    
    public ArrayList<Card> askCardsToDiscard(){
        int cardsDiscarded = 0;
        boolean stop = false;
        int maxCardsToDiscard = engine.getLengthHandDeckOfCurrentPlayer();
        ArrayList<Card> discardCards = new ArrayList<>();
        while(cardsDiscarded < maxCardsToDiscard && !stop){
            System.out.println("Pick a card to discard (Enter -1 to stop): ");
            showHand();
            int pickedCard = scanInt();
            while(pickedCard < -1 && engine.getLengthHandDeckOfCurrentPlayer() < pickedCard){
                System.out.println("Pick a card to discard (give a correct input): ");
                pickedCard = scanInt();
            }
            if(pickedCard > -1){
                Card chosenCard = engine.getCardFromHandDeckCurrentPlayer(pickedCard);
                discardCards.add(chosenCard);
                engine.removeCardFromHandDeckCurrentPlayer(pickedCard);
                cardsDiscarded++;
            } else {
                stop = true;
            }
        }
        return discardCards;
    }
    
    public int askcardToTrash(){
        System.out.println("Select a treasure card to trash (gain a card costing up to 3 more coins): ");
        int pickedCard = scanInt();
        while(pickedCard < 0 || engine.getLengthHandDeckOfCurrentPlayer() < pickedCard || ((0 <= pickedCard && pickedCard <= engine.getLengthHandDeckOfCurrentPlayer()) ? engine.isTreasureCard(pickedCard) : false)){
            if(pickedCard < 0 || engine.getLengthHandDeckOfCurrentPlayer() < pickedCard){
                System.out.println("Give a correct input: ");
            } else {
                System.out.println("Select a TREASURE card: ");
            }
        }
        return pickedCard;
    }
}
