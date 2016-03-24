/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.Models;

/**
 *
 * @author Yanni
 */
public class Card {
    private int cardID;
    private CardType cardType;    
    private int cost;
    private String title;
    
    public Card(int cardID ,int cost, String title, CardType cardType){
        this.cardID = cardID;
        this.cost = cost;
        this.title = title;
        this.cardType = cardType;
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
