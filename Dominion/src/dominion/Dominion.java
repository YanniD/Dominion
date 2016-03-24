package dominion;

import dominion.Database.DatabaseService;
import dominion.Models.Deck;
import java.sql.Connection;
import java.sql.SQLException;
import dominion.Models.*;

public class Dominion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        testAddtoDeck();
        testDeckShuffle(); testDeckShuffle();
        testDatabase();
    }
    
     public static void testAddtoDeck()
    {
        Deck d = new Deck(true);
        Card c = new Card(11,5,"adventurer",CardType.Action);
        d.addToDeck(0,c);
        System.out.println(d.getCardAtIndex(0));
    }
    
    public static void testDeckShuffle()
    {
        Deck d = new Deck(true);
        d.randomShuffle();
        System.out.println(d.toString());
    }
   
    
    public static void testDatabase() throws SQLException, ClassNotFoundException{
        DatabaseService dbservice = new DatabaseService();
    	System.out.println(dbservice.FindCards());
        System.out.println("blabla");
    }
}