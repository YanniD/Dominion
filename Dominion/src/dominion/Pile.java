/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;

/**
 * Stack are the kingdom-, victoy- and treasurycards
 */
public class Pile {
    private Card card;
    private int amount;
    
    public Pile(Card givenCard, int totalAmount) {
        card = givenCard;
        amount = totalAmount;
    }

    public int getAmount() {
        return amount;
    }
    
    public void decrementAmount() {
        amount--;
    }
}
