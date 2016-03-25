/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.Models;
import java.util.Arrays;

/**
 *
 * @author Yanni
 */
public class Card {
    private int cardID;
    private CardType cardType;    
    private int cost;
    private String title;
    private int amount;
    
    public Card(int cardID ,int cost, String title, CardType cardType,int amount){
        this.cardID = cardID;
        this.cost = cost;
        this.title = title;
        this.cardType = cardType;
        this.amount = amount;
    }
    
    public int getCardID(){
        return cardID;
    }
    public int getCost(){
        return cost;
    }
    public String getTitle(){
        return title;
    }
    public CardType getType(){
        return this.cardType;
    }
    
}
