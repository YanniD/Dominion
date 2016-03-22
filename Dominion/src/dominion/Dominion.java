/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;

/**
 *
 * @author Yanni
 */
public class Dominion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        testKingdomCards();
    }
    
    public static void testKingdomCards()
    {
        Card estate = new Card(5 , "test" , 10);
        System.out.println(estate.getTitle());
    
    }
    
}
