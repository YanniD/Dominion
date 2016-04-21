/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;
import java.util.Scanner;
import dominion.Models.*;
import java.util.ArrayList;
import dominion.Database.DatabaseService;


public class Console {
    private ArrayList<Pile> piles; 
    private DatabaseService dbs;
    
    public Console(){
        this.dbs = new DatabaseService();
    }
    
    public void ShowMenu(){
        System.out.println("Dominion");
        System.out.println("----------\n");
        System.out.println("1: New Game");
        System.out.println("2: Load Game");
        System.out.println("3: Exit Game");
        System.out.println("Enter your choice: ");
        int choice = scanInt();
        processChoice(choice);
    }
    
    public void processChoice(int userChoice) {
        if (userChoice == 1) {
            // function to new game
            Speler s1 = initPlayer(1);
            Speler s2 = initPlayer(2);
            choosePreSet(s1, s2);
        }
        else if(userChoice == 2){
            // function to load game
            showSaveFiles();
        }
        else if (userChoice == 3){
            // function for exit game
            System.exit(0);
        }
        else {
            throw new IllegalArgumentException("Wrong input");
        }
    }
   
    public void choosePreSet(Speler s1, Speler s2){
        showPresets();
        int set = processChoicePreSet();
        String confirmPreSet = scanString();
        while ("n".equals(confirmPreSet)) {
            showPresets();
            set = processChoicePreSet();
            confirmPreSet = scanString();
        }
        initGame(s1, s2, set);
    }
    
    private int processChoicePreSet(){
        int choicePreset = scanInt();
        while (choicePreset < 1 || 5 < choicePreset) {
            showPresets();
            System.out.println("Enter a valid number: ");
            choicePreset = scanInt();
        }
        confirmation(choicePreset, dbs);
        return choicePreset;
    }

    private void confirmation(int choicePreset, DatabaseService dbs) {
        Set set = new Set();
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
    }
    
    /**
     * make dynamic
     */
    public void showSaveFiles(){
        System.out.println("Choose file to load");
        System.out.println("-------------------\n");
        System.out.println("1: file1");
        System.out.println("2: file2");
    }
    
    public Speler initPlayer(int spelerID){
        System.out.println("Player " + spelerID);
        System.out.println("Name: ");
        String player1Name = scanString();
        Speler speler = new Speler(player1Name, spelerID);
        return speler;
    } 
    
    public int scanInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    
    public String scanString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
   
    public void initGame(Speler speler1, Speler speler2, int chosenSet){
        Game g = new Game(speler1, speler2, chosenSet, dbs);
    }
    
    public boolean libraryConfirmation() {
        System.out.println("Do you want to keep this card? y/n");
        return yesOrNo();
    }
    
    /**
     * y/n scan
     */
    private boolean yesOrNo() {
        String s = scanString();
        if ("y".equals(s)){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Used by spyCard and ThiefCard
     */
    public void revealCard(Speler speler, Card card, int index){
        System.out.println(speler.getPlayerName() + ": " + index + ". " + card.getTitle());
    }
    
    /**
     * Used by spyCard part2
     */
    public boolean spyConfirmation() {
        System.out.println("Do you want to discard all revealed cards? y/n");
        return yesOrNo();
    }
    
    /**
     * Used by spyCard part2
     */
    
    public int thiefCardChoice() {

        if(thiefConfirmation()){
            System.out.println("Which treasure card do you want to steal?");
            int cardChoice = scanInt();
            while (cardChoice < 0 || 1 < cardChoice) {
                System.out.println("Wrong input.");
                System.out.println("Which treasure card do you want to steal?");
            }
            return cardChoice;
            }
        return 3;
    }
    
    public boolean thiefConfirmation(){
        System.out.println("Do you want to steal a card?  y/n");
        return yesOrNo();
    }
    
    public boolean remodelConfirmation(){
        System.out.println("Do you want to choose a card?  y/n");
        return yesOrNo();
    }
    
    public void showAvaileblePiles(){
        
    }
}
