/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;
import java.util.ArrayList;
import dominion.Database.DatabaseService;

public class Game {;
    private Speler speler1;
    private Speler speler2;
    private ArrayList<dominion.Models.Card> allCards;
    private Console console;
    
    public Game(){
        DatabaseService dbs = new DatabaseService();
        this.console = new Console(speler1,speler2); 
        allCards = dbs.FindCards();
    }

    public Console getConsole(){
        return console;
    }
    
    /**
     * testfunction
     */
    public void printAllCards(){
        for(int i = 0; i < allCards.size(); i++) {
        dominion.Models.Card cardName = allCards.get(i);
        System.out.println(cardName.getTitle());
        }
    }
    
}