/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yanni
 */
public class VictoryCard extends Card{
    private int victoryPoints;
    
    public VictoryCard(int cost, String title, int amount, int points){
        super(cost, title, amount);
        victoryPoints = points;
    }
    
    /**
     * returns victory points
     */
    public int getVP(){
        return victoryPoints;
    }
}