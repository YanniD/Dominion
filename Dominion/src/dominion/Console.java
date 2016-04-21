/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;
import java.util.Scanner;
import dominion.Models.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Console {
    private int turn;
    private Speler speler1;
    private Speler speler2;
    private ArrayList<dominion.Models.Card> cards; 
    
    public Console(Speler speler1){
        this.turn = 0;
        this.speler1 = new Speler("Speler 1",1);
        this.cards = new ArrayList();
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
            choosePreSet();
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
   
    public void choosePreSet(){
        showPresets();
        int set = processChoicePreSet();
        String confirmPreSet = scanString();
        while ("n".equals(confirmPreSet)) {
            showPresets();
            processChoicePreSet();
            confirmPreSet = scanString();
        }
        initGameCards(set);
    }
    
    private int processChoicePreSet(){
        Set set = new Set();
        int choicePreset = scanInt();
        confirmation(set, choicePreset);
        return choicePreset;
    }

    public void confirmation(Set set, int choicePreset) {
        int[] preSetCards = set.getSet(choicePreset);
        ArrayList<Card> cards = set.getCardStats(preSetCards);
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
    
    public int scanInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    
    public String scanString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
   public void incrementTurn() {
       turn++;
   }
   
   public int getTurn() {
       return turn;
   }
   
    public void initGameCards(int chosenSet){
        Set set = new Set();
        cards = set.getGameCards(set.getSet(chosenSet));        
       // ArrayList<Pile> StackOfCards = new ArrayList();
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
    
    public void showAllAvaileblePiles(){
        for{
            
        }
    }
}
