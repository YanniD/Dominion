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
        //testGetGameCards();
        //testArraylistAddDel();
        //testRevealCard();
        //spyAbility();
        //thiefAbility();
        //math();
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
        ArrayList<Card> gameCards = s.getGameCards(s.getSet(1));
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
    
    public static void testArraylistAddDel(){
        ArrayList<Integer> ints = new ArrayList<Integer>();
        int intToEnter = 1;
        for (int i = 0; i < 10; i++) { 
            ints.add(0, intToEnter);
            intToEnter++;
        }
        System.out.println(ints.toString());
        
        for (int i = 0; i < 5; i++) { 
            ints.remove(0);
        }
        System.out.println(ints.toString());
    }
    
    public static void testRevealCard(){
        Speler s = new Speler("Speler1", 1);
        Console c = new Console(s);
        Card spy = new Card(1, 4, "Spy", CardType.Action, 5);
        c.revealCard(s, spy, 1);
    }
    
    public static void spyAbility(){
        Speler s = new Speler("Rudy", 1);
        Speler s2 = new Speler("Frank", 2);
        Abilities ab = new Abilities(s);
        ab.spyAbility(s2);
    }
    
    public static void thiefAbility(){
        Speler s = new Speler("Rudy", 1);
        Speler s2 = new Speler("Frank", 2);
        Abilities ab = new Abilities(s);
        ab.thiefAbility(s2);
    }
    public static void math(){
        System.out.println(25/10);
    }
}