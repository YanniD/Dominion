/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominion.Database;

import com.mysql.jdbc.NotImplemented;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import dominion.Models.Card;
import dominion.Models.CardType;
import java.util.ArrayList;
/*
 *
 * @author Yanni
*/
public class DatabaseService {
    
    //http://www.connectionstrings.com/mysql/
    //Server=localhost;Database=myDataBase;Uid=root;Pwd=root; 
    private final String host = "jdbc:mysql://localhost:3306/dominioncard";
    private final String username = "root";
    private final String password = "root";
    
    private Connection con;
 
    public DatabaseService(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(host,username,password);
        } catch(ClassNotFoundException e){            
        } catch (SQLException ex) {
        } 

    }
        
   /*@Override
    public Boolean SaveUser(String name) {
        //save to database
        String query = "INSERT INTO dbo.Users(name) values (" + name + ")";
        try{
            //Create connection
            Connection myConnection = getConnection();
            //Create statement
            Statement myStatement = myConnection.createStatement();
            //Execute statement
            myStatement.executeUpdate(query);
            
            //Close statement & connection
            myStatement.close();
            myConnection.close();
        } catch(ClassNotFoundException e){
            return false;            
        } catch (SQLException ex) {
            return false;
        }        
        
        return true;
    }
*/
    public ArrayList<Card> FindCards(){
        String query = BuildGetCardsQuery();    	
        try{
            Statement myStatement = con.createStatement();
            //Execute statement
            ResultSet rs = myStatement.executeQuery(query);  
            ArrayList<Card> cards = new ArrayList<Card>();        
            while(rs.next()){
                Card card = CreateCard(rs);
                cards.add(card);
            }        
            //Close statement & connection
            myStatement.close();
            return cards;     
        } catch(SQLException e){
            return null;
        }
    }
      
    
    public Card FindCardByID(int cardID){
        String query = BuildGetCardByIDQuery(cardID);
    	try{
        Statement myStatement = con.createStatement();
        //Execute statement
        ResultSet rs = myStatement.executeQuery(query);  
        
        Card card = null;
        while(rs.next()){        
            card = CreateCard(rs);
        }
        //Close statement & connection
        myStatement.close();
        return card; 
        } catch(SQLException e){
            return null;
        }
    }
    
    private Card CreateCard(ResultSet rs){
        try{
        Card card;
        CardType cardType = CardType.values()[rs.getInt("Type")];        
        int cost = rs.getInt("Cost");
        String title = rs.getString("Name");
        int cardID = rs.getInt("ID");   
        int amount = rs.getInt("StartAmount");
        
        switch(cardType){
            case Action: 
                card = CreateActionCard(cardID,cost, title,amount);
                break;
            case ActionAttack:
                card = CreateActionAttackCard(cardID, cost, title, amount);
                break;
            case Treasure:
                int worth = rs.getInt("Worth");
                card = CreateTreasureCard(cardID,cost,title,worth,amount);
                break;
            case Victory:
                int victoryPoints = rs.getInt("VictoryPoints");
                card = CreateVictoryCard(cardID,cost,title,victoryPoints,amount);
                break;
            default: 
                throw new NotImplemented();
        }   
        return card;
        } catch(SQLException e){
            return null;
        } 
    }
    
    private dominion.Models.ActionCard CreateActionCard(int cardID,int cost, String title,int amount){
        return new dominion.Models.ActionCard(cardID, cost, title, CardType.Action, amount);
    }
    
     private dominion.Models.ActionCard CreateActionAttackCard(int cardID,int cost, String title,int amount){
        return new dominion.Models.ActionCard(cardID, cost, title, CardType.ActionAttack, amount);
    }
    
    private dominion.Models.TreasureCard CreateTreasureCard(int cardID, int cost, String title, int worth,int amount){
        return new dominion.Models.TreasureCard (cardID, cost,title,CardType.Treasure,worth,amount);
    }
    
    private dominion.Models.VictoryCard CreateVictoryCard(int cardID,int cost, String title, int victoryPoints,int amount){
        return new dominion.Models.VictoryCard(cardID,cost,title,CardType.Victory,victoryPoints,amount);        
    }
    
    public String BuildGetCardsQuery(){
    	return "SELECT * FROM cards";
    }
    
    public String BuildGetCardByIDQuery(int cardID){
    	return "SELECT * FROM cards WHERE ID = " + cardID;
    }
    
    public void close(){
        try{con.close();
        }catch(SQLException e){
        }
    }
}


//http://stackoverflow.com/questions/14704559/how-to-insert-image-in-mysql-databasetable foto toevoegen aan DB
// http://stackoverflow.com/questions/3577848/generating-a-css-file-from-file-or-database-php css files toevoegen aan DB en ophalen voor website
