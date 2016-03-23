/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;


public class Dominion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testAanmakenCards();
        testGetKaartUitTest();
    }
    
    public static void testAanmakenCards()
    {
        Card estate = new Card(5 , "test");
        System.out.println(estate.getTitle());
    
    }
    public static void testGetKaartUitTest()
    {
        Set bigMoneySet = new Set();
        System.out.println(bigMoneySet.getSet(bigMoneySet.getBigMoney(),8));
    }
}
