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
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author quentin
 */
public class ConsoleGame {
    private GameEngine Engine;
    
    public ConsoleGame(){
        
    }
    
        public void ShowMenu(){
        System.out.println("Dominion");
        System.out.println("----------\n");
        System.out.println("1: New Game");
        System.out.println("2: Load Game");
        System.out.println("3: Exit Game");
        System.out.println("Enter:");
        int choice = scanInt();
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
        DatabaseService dbs = new DatabaseService();
        Engine = new GameEngine(dbs, pickSet(dbs));
        Engine.setPlayers(askPlayers());
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
    
    public ArrayList<Speler> askPlayers(){
        ArrayList<Speler> Spelers = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            Spelers.add(initPlayer(i));
        }
        return Spelers;
    }
    
    public Speler initPlayer(int spelerID){
        System.out.println("Player " + spelerID);
        System.out.println("Name: ");
        String playerName = scanString();
        Speler speler = new Speler(playerName, spelerID);
        return speler;
    } 
    
    public int pickSet(DatabaseService dbs){
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
        Engine.initFirstRound();
        while(!Engine.finished()){
            showCurrentSituation();
            showGameCards();
            showHand();
            Engine.nextPlayer();
            Engine.nextTurn();
        }
    }
    
    public void showCurrentSituation() {
        Speler currentSpeler = Engine.getCurrentSpeler();
        System.out.println("------------------------ TURN " + Engine.getTurn() + " Player: " + currentSpeler.getPlayerName() + " ------------------------");
    }
    
    public void showGameCards(){
        ArrayList<Pile> Piles = Engine.getPiles();
        for(int i = 0; i < Piles.size(); i++){
            Card card = Piles.get(i).getCard();
            String cardTitle = card.getTitle();
            int cardCost = card.getCost();
            int amountLeft = Piles.get(i).getAmount();
            System.out.println(i + ". " + cardTitle + " \t || " + " cost: " + cardCost + " \t || cards left: " + amountLeft );
        }
    }
    
    public void showHand(){
        Deck handDeck = Engine.getCurrentSpeler().getHandDeck();
        
        System.out.print("\nHand : ");
        for (int i = 0; i < handDeck.getLengthFromDeck() ; i++){
            Card card = handDeck.getCardAtIndex(i);
            String cardTitle = card.getTitle();
            System.out.print("\t || " + i + ". " + cardTitle);
        }
        System.out.println("\n");
        
    }
    
   
    public void pickCardToPlay(){
        System.out.print("Pick a card to play");
        int PickedCard = scanInt();
        while(PickedCard <= 0 || Engine.getCurrentSpeler().getHandDeck().getLengthFromDeck() <= PickedCard){          
            System.out.print("Pick a card to play (give a correct input)");
            PickedCard = scanInt();
        }
        Engine.playCard(PickedCard);
    }
    
    public int scanInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    
    public String scanString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
