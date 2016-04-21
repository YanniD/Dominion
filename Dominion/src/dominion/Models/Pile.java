/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.Models;

/**
 * piles are the kingdom-, victoy- and treasurycards
 */
public class Pile {
    private Card card;
    private int amount;
    
    public Pile(Card givenCard) {
        card = givenCard;
        amount = card.getAmount();
    }
    
    public Card getCard(){
        return card;
    }

    public int getAmount() {
        return amount;
    }
    
    public boolean isEmpty(){
        if (amount == 0){
            return true;
        } else{
            return false;
        }
    }
    
    public void decrementAmount() {
        amount--;
    }
    
    
    
}