package dominion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class VictoryCard extends Card{
    private int victoryPoints;
    
    public VictoryCard(int cardID, int cost, String title, int points){
        super(cardID, cost, title);
        victoryPoints = points;
    }

    public int getVP(){
        return victoryPoints;
    }
}