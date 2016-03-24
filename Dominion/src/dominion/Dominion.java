package dominion;


import java.sql.Connection;
import java.sql.SQLException;
import dominion.Database.DatabaseService;
import dominion.Models.Card;
import dominion.Models.CardType;
import dominion.Models.Deck;
import java.util.Arrays;

public class Dominion{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //testAddtoDeck();
        //testDeckShuffle(); testDeckShuffle();
        //testDatabase();
        showMenuTest();
        //testGame();
    }
    
     public static void testAddtoDeck(){
        Deck d = new Deck(true);
        Card c = new Card(11,5,"adventurer",CardType.Action);
        d.addToDeck(0,c);
        System.out.println(d.getCardAtIndex(0));
    }
    
    public static void testDeckShuffle(){
        Deck d = new Deck(true);
        d.randomShuffle();
        System.out.println(d.toString());
    }
    public static void showMenuTest(){
        Game g = new Game();
        g.getConsole().ShowMenu();
    }
    
    public static void testDatabase() throws SQLException, ClassNotFoundException{
        DatabaseService dbservice = new DatabaseService();
    	System.out.println(dbservice.FindCards());
        System.out.println("Database intact");
    }
    
    public static void testGame() {
        Game g = new Game();
        g.printAllCards();
    }
}