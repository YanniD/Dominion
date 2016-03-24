package dominion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Card {
    private int cardID;
    private int cost;
    private String title;

    public Card(int cardID, int cost, String title){
        this.cardID = cardID;
        this.cost = cost;
        this.title = title;       
    }
    
    public int getCost(){
        return cost;
    }
    
    public String getTitle(){
        return title;
    }
    
    public int getCardID(){
        return cardID;
    }
    
}
