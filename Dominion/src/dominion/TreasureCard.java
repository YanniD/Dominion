/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yanni
 */
public class TreasureCard extends Card{
    private int worth;
    
    public TreasureCard(int cost, String title,int amount,int worth){
        super(cost, title, amount);
        this.worth = worth;
    }
    public int GetWorth(){
        return worth;
    } 
}
