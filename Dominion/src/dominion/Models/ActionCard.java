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
public class ActionCard extends Card{
    
    public ActionCard(int cardID,int cost, String title, CardType cardType,int amount) {
        super(cardID,cost, title, cardType,amount);
    }
    
}
