package dominion;


import java.sql.Connection;
import java.sql.SQLException;
import dominion.Database.DatabaseService;
import dominion.Models.Card;
import dominion.Models.CardType;
import dominion.Models.Deck;
import dominion.Models.Set;
import java.util.ArrayList;
public class Dominion{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //testPrintOneCard();
        //testAddCardToDeck();
        //testDeckShuffle(); testDeckShuffle();
        //testDatabase();
        //showMenuTest();
        //testPrintAllCards();
        //testGetSetCards();
        testGetGameCards();
    }
    
     public static void testPrintOneCard(){
        DatabaseService dbs = new DatabaseService();
        Deck d = new Deck(true);
        Card c = dbs.FindCardByID(5); //moat
        d.addToDeck(0,c);
        String title = c.getTitle();
        int cost = c.getCost();
        CardType cardType = c.getType();
        System.out.println("title cost cardType");
        System.out.println(title + " " + cost + " " +cardType);
    }
     
    /*
    public static void testGetSetCards(){
        DatabaseService dbs = new DatabaseService();
        Set set = new Set();
        int[] getYourSet = set.getSet(3);
        ArrayList<Card> cardSet= set.getCardStats(getYourSet);
        for (int i = 1; i < set.getLength(getYourSet) ;i++){
            Card c = dbs.FindCardByID(set.getOneCardOfSet(getYourSet, i));
            System.out.println(c.getTitle());
        }
    }
    */
    
    public static void testGetGameCards(){
        Set s = new Set();
        ArrayList<Card> gameCards = s.getGameCards(s.getSet(2));
        for (int i = 0; i < gameCards.size(); i++){
            Card c = gameCards.get(i);
            System.out.println(c.getTitle());
        }
    }
    
    public static void testAddCardToDeck(){
        DatabaseService dbs = new DatabaseService();
        Deck d = new Deck(true);
        Card c = dbs.FindCardByID(5); //moat
        d.addToDeck(10, c);
        d.randomShuffle();
        for (int i = 0; i < d.getLengthFromDeck(); i++) {
            Card cardName = d.getCardAtIndex(i);
            System.out.println(cardName.getTitle());
        }
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
    
    public static void testPrintAllCards() {
        Game g = new Game();
        g.printAllCards();
    }
}