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
public class TreasureCard extends Card {
    private int worth;

    public TreasureCard(int cardID, int cost, String title, CardType cardType, int worth) {
        super(cardID ,cost, title, cardType);
        this.worth = worth;
    }   
    
    public int getWorth(){
        return worth;
    } 
}
