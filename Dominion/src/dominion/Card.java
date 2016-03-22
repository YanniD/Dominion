package dominion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yanni
 */
public class Card {
    private int cost;
    private String title;
    private int amount;
    
    public Card(int cost, String title,int amount){
        this.cost = cost;
        this.title = title;
        this.amount = amount;
    }
    public int getCost(){
        return cost;
    }
    public String getTitle(){
        return title;
    }
    public int getAmount(){
        return amount;
    }
    public void gecrementAmount(){
        amount--;
    }
    
}
