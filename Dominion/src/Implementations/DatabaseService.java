/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementations;

import Interfaces.IDatabaseService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Yanni
 */
public class DatabaseService implements IDatabaseService {

    //http://www.connectionstrings.com/mysql/
    //Server=localhost;Database=myDataBase;Uid=root;Pwd=root;
    private final String connectionString;
    
    public DatabaseService(String connectionString){
        this.connectionString = connectionString;
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //http://stackoverflow.com/questions/14914494/how-to-make-sql-connection-statements-common
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(this.connectionString, "username", "password");
        return con;
    }
    
    @Override
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

    @Override
    public String FindUsers() {
        String query = "SELECT * FROM dbo.Users";
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
            return null;            
        } catch (SQLException ex) {
            return null;
        }        
        
        return null;
        
    }
    
}
