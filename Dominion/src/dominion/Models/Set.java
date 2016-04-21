/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.Models;
import dominion.Database.DatabaseService;
import java.util.ArrayList;


public class Set {
    private int[] firstGame;
    private int[] bigMoney;
    private int[] interaction;
    private int[] sizeDistortion;
    private int[] villageSquare;
    
    public Set(){
        firstGame = new int[] {1,2,3,4,5,6,7,8,9,10};
        bigMoney = new int[] {11,12,13,14,15,16,2,4,17,18};
        interaction = new int[] {12,13,31,19,20,3,5,21,22,8};
        sizeDistortion = new int[]{1,14,15,23,16,22,8,24,9,10};
        villageSquare = new int[] {12,1,19,20,2,6,7,18,8,9};
    }
    
    public int[] getSet(int preSetChoice) {
        switch(preSetChoice) {
            case 1: return firstGame;
            case 2: return bigMoney;
            case 3: return interaction;        
            case 4: return sizeDistortion;
            case 5: return villageSquare;
            default: return null;
        }
    }
    
    public void showSetCards(int[] Set) {
        for (int i = 0; i < Set.length; i++) {
            System.out.println(getOneCardOfSet(Set,i));
        }
    }
    
    public int getOneCardOfSet(int[] Set , int i){
            return Set[i];
    }
    
    public int getLength(int[] set) {
        return set.length;
    }
    
    public ArrayList<Card> getCardStats(int[] set){
        DatabaseService dbs = new DatabaseService();
        ArrayList<Card> cardStats = new ArrayList<Card>();
        for (int i = 0; i < set.length; i++) {
            Card c = dbs.FindCardByID(set[i]);
            cardStats.add(c);
        }
        return cardStats;
    }
    
    private ArrayList<Card> getTRAndVPCards(ArrayList<Card> setCards){   
        DatabaseService dbs = new DatabaseService();
         for (int i = 25; i <= 30; i++) {
             Card c = dbs.FindCardByID(i);
             setCards.add(c);
         }
         return setCards;     
    }
    
    public ArrayList<Card> getGameCards(int[] set) {
        ArrayList<Card> gameCards = getCardStats(set);
        return getTRAndVPCards(gameCards);
    }
    
}
