/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;
import java.util.Scanner;
import dominion.Models.Set;
import java.util.Arrays;

public class Console {
    private int turn;
    private Speler speler1;
    private Speler speler2;
    
    public Console(Speler speler1, Speler speler2){
        this.turn = 0;
        this.speler1 = new Speler("Speler 1",1);
        this.speler2 = new Speler("Speler 2",2);
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
            chooseSaveFile();
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
        confirmation();
        String confirmPreSet = scanString();
        while ("n".equals(confirmPreSet)) {
            showPresets();
            confirmation();
            confirmPreSet = scanString();
        }
        startGame();   
    }

    public void confirmation() {
        Set set = new Set();
        int choicePreset = scanInt();
        String[] preSetCards = set.getSet(choicePreset);
        System.out.println(Arrays.toString(preSetCards));
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
    
    public void startGame() {
        System.out.println("Game starting...");
    }
    
    /**
     * make dynamic
     */
    public void chooseSaveFile(){
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
    
}
