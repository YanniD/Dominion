/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;
import java.util.ArrayList;
import dominion.Models.Set;
import dominion.Models.Card;
import dominion.Models.Pile;
import dominion.Database.DatabaseService;

public class Game {
    private int turn;
    private Speler speler1;
    private Speler speler2;
    private ArrayList<Pile> allPiles;
    private Abilities abs;
    private DatabaseService dbs;
    
    public Game(Speler speler1, Speler speler2, int chosenSet, DatabaseService dbs){
        this.speler1 = speler1;
        this.speler2 = speler2;
        this.dbs = dbs;
        allPiles = new ArrayList<Pile>();
        newGame(chosenSet);
        abs = new Abilities(new Console()); // make ConsoleGame
    }

    public void newGame(int chosenSet){
        Set set = new Set();
        ArrayList<Card> playCards = set.getGameCards(set.getSet(chosenSet), dbs);
        for (int i = 0; i < playCards.size(); i++){
            Pile pileToAdd = new Pile(dbs.FindCardByID(playCards.get(i).getCardID()));
            allPiles.add(pileToAdd);
        } 
    }
  
    public ArrayList<Pile> getPiles() {
        return allPiles;
    }
}