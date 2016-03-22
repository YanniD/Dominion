/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominion;

/**
 *
 * @author Kevin
 */
public class Game {
    private int acties;
    private int buys;
    
    
    
    //Constructor
    public Game()
    {
        acties = 1;
        buys = 1;
    }
    
    /**
     * Increment action verhoogt aantal acties
     */
    
    // Actiefase
    
    public void incrementAction(int acties)
    {
      this.acties += acties;  
    }
    
    public void decrementActions()
    {
      acties--;   
    }
            
    
   
    public void incrementBuys(int buys)
    {
      this.buys += buys;
    }
    

    
    
    
    //Aankopen van actiekaarten
    public void aankoopKaart()
    {
        
        
    }
    
    public void decrementBuys()
    {
       buys--;
    }

    
    
    //Einde van de ronde
    
   
    
    
    
    
    
    
    
    //Einde van het spel
    
    
    
    public void Endgame()
    {

    }
        
}