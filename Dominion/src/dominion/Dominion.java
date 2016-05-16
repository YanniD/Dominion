package dominion;


//import Abilities.CardAbility;
import java.sql.SQLException;
import dominion.Database.DatabaseService;
import dominion.Models.*;
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
        //startConsoleGame();
        showGameCards();
        //showAllPiles();
        //testGetSetCards(1);
        //testGetGameCards(3);
        //testArraylistAddDel();
        //spyAbility();
        //thiefAbility();
        //math();
    }
    
     public static void testPrintOneCard(){
        DatabaseService dbs = new DatabaseService();
        Deck d = new Deck(true, dbs);
        Card c = dbs.FindCardByID(5); //moat
        d.addToDeck(0, c);
        String title = c.getTitle();
        int cost = c.getCost();
        CardType cardType = c.getType();
        System.out.println("title cost cardType");
        System.out.println(title + " " + cost + " " +cardType);
    }
     
     public static void showGameCards() {
        ConsoleGame c = new ConsoleGame();
        c.showMenu();
     }
     

    public static void testGetSetCards(int chosenSet){
        DatabaseService dbs = new DatabaseService();
        SetConfig set = new SetConfig();
        int[] getYourSet = set.getSet(chosenSet);
        ArrayList<Card> cardSet = set.getCardStats(getYourSet, dbs);
        for (int i = 1; i < set.getLength(getYourSet) ;i++){
            Card c = dbs.FindCardByID(set.getOneCardOfSet(getYourSet, i));
            System.out.println(c.getTitle());
        }
    }

    
    public static void testGetGameCards(int chosenSet){
        DatabaseService dbs = new DatabaseService();
        SetConfig s = new SetConfig();
        ArrayList<Card> gameCards = s.getGameCards(s.getSet(chosenSet), dbs);
        for (int i = 0; i < gameCards.size(); i++){
            Card c = gameCards.get(i);
            System.out.println(c.getTitle());
        }
    }
    
    public static void testAddCardToDeck(){
        DatabaseService dbs = new DatabaseService();
        Deck d = new Deck(true, dbs);
        Card c = dbs.FindCardByID(5); //moat
        d.addToDeck(0, c);
        d.randomShuffle();
        for (int i = 0; i < d.getLengthFromDeck(); i++) {
            Card cardName = d.getCardAtIndex(i);
            System.out.println(cardName.getTitle());
        }
    }
    
    public static void showAllPiles() {
        ConsoleGame cg = new ConsoleGame();
        DatabaseService dbs = new DatabaseService();
        GameEngine ge = new GameEngine();
        ge.initCards(cg.pickSet());
        ArrayList<Pile> piles = ge.getPiles();
        for(int i = 0; i < piles.size(); i++) {
            System.out.print(i + ". " + piles.get(i).getCard().getTitle() + " || ");
        }
    }
    
    public static void testDeckShuffle(){
        DatabaseService dbs = new DatabaseService();
        Deck d = new Deck(true, dbs);
        d.randomShuffle();
        System.out.println(d.toString());
    }
    public static void startConsoleGame(){
        ConsoleGame c = new ConsoleGame();
        c.showMenu();
    }
    
    public static void testDatabase() throws SQLException, ClassNotFoundException{
        DatabaseService dbservice = new DatabaseService();
    	System.out.println(dbservice.FindCards());
        System.out.println("Database intact");
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
    
    
//    public static void spyAbility(){
//        Speler s = new Speler("Rudy", 1);
//        Speler s2 = new Speler("Frank", 2);
//        Console c = new Console();
//        CardAbility ab = new CardAbility(c);
//        ab.spyAbility(s, s2);
//    }
//    
//    public static void thiefAbility(){
//        Speler s = new Speler("Rudy", 1);
//        Speler s2 = new Speler("Frank", 2);
//        Console c = new Console();
//        CardAbility ab = new CardAbility(c);
//        ab.thiefAbility(s, s2);
//    }
    
    public static void math(){
        System.out.println(25/10);
    }
}