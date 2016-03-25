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
public class VictoryCard  extends Card{
    
    private int victoryPoints;
    
    public VictoryCard(int cardID,int cost, String title, CardType cardType,int victoryPoints,int amount) {
        super(cardID,cost, title, cardType,amount);
        this.victoryPoints = victoryPoints;
    }   
    
    /**
     * returns victory points
     */
    public int getVictoryPoints(){
        return victoryPoints;
    }
    
}
