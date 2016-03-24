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
 
    public DatabaseService() throws SQLException, ClassNotFoundException{
        
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(host,username,password);
        
        
        
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
        }
        catch(ClassNotFoundException e){
            return false;            
        } catch (SQLException ex) {
            return false;
        }        
        
        return true;
    }
*/
    public ArrayList<dominion.Models.Card> FindCards()throws SQLException, ClassNotFoundException{
        String query = BuildGetCardsQuery();    	
        
        Statement myStatement = con.createStatement();
        //Execute statement
        ResultSet rs = myStatement.executeQuery(query);     
        ArrayList<dominion.Models.Card> cards = new ArrayList<dominion.Models.Card>();        
        
        while(rs.next()){
            dominion.Models.Card card = CreateCard(rs);
            cards.add(card);
        }        

        //Close statement & connection
        myStatement.close();
        
        return cards;     
    }
      
    
    public dominion.Models.Card FindCardByID(int cardID) throws SQLException, ClassNotFoundException{
    	
        String query = BuildGetCardByIDQuery(cardID);
    	
        Statement myStatement = con.createStatement();
        //Execute statement
        ResultSet rs = myStatement.executeQuery(query);  
        
        dominion.Models.Card card = null;
        while(rs.next()){        
            card = CreateCard(rs);
        }
        //Close statement & connection
        myStatement.close();
        
        return card;       
    }
    
    private dominion.Models.Card CreateCard(ResultSet rs) throws SQLException, ClassNotFoundException{
        dominion.Models.Card card;
        CardType cardType = CardType.values()[rs.getInt("Type")];        
        int cost = rs.getInt("Cost");
        String title = rs.getString("Name");
        int cardID = rs.getInt("ID");
               
        switch(cardType){
            case Action: 
                card = CreateActionCard(cardID,cost, title);
                break;
            case Treasure:
                int worth = rs.getInt("Worth");
                card = CreateTreasureCard(cardID,cost,title,worth);
                break;
            case Victory:
                int victoryPoints = rs.getInt("VictoryPoints");
                card = CreateVictoryCard(cardID,cost,title,victoryPoints);
                break;    
            default: 
                throw new NotImplemented();
        }   
        
        return card;
    }
    
    private dominion.Models.ActionCard CreateActionCard(int cardID,int cost, String title){
        return new dominion.Models.ActionCard(cardID, cost, title, CardType.Action);
    }
    
    private dominion.Models.TreasureCard CreateTreasureCard(int cardID, int cost, String title, int worth){
        return new dominion.Models.TreasureCard (cardID, cost,title,CardType.Treasure,worth);
    }
    
    private dominion.Models.VictoryCard CreateVictoryCard(int cardID,int cost, String title, int victoryPoints){
        return new dominion.Models.VictoryCard(cardID,cost,title,CardType.Victory,victoryPoints);        
    }
    
    public String BuildGetCardsQuery(){
    	return "SELECT * FROM cards";
    }
    
    public String BuildGetCardByIDQuery(int cardID){
    	return "SELECT * FROM cards WHERE ID = " + cardID;
    }
    
    public void close()
    {
        try
        {
            con.close();
        }
        catch(SQLException e)
        {
        }
    }
}


//http://stackoverflow.com/questions/14704559/how-to-insert-image-in-mysql-databasetable foto toevoegen aan DB
// http://stackoverflow.com/questions/3577848/generating-a-css-file-from-file-or-database-php css files toevoegen aan DB en ophalen voor website
