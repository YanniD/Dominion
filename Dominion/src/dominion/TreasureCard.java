package dominion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class TreasureCard extends Card{
    private int worth;
    
    public TreasureCard(int cardID, int cost, String title,int worth){
        super(cardID, cost, title);
        this.worth = worth;
    }
    
    public int getWorth(){
        return worth;
    } 
}
