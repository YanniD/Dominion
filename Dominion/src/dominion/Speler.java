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
public class Speler {
    private String name;
    private int playerID;
    private int actions;
    private int buys;
    private int coins;
    
    
    //constructor
    
    public Speler(String name, int ID)
    {
        this.name = name;
        this.playerID = ID;
        initRound();
   
    }
    
    
    //actie verminderen per actiekaart gespeeld
    
    public void actionDecrement()
    {
        this.actions -= 1;
    
    }
    
    //naargelang de kaart actie terug vermeerderen
    public void actionIncrement(int actionIncrease)
    {
        this.actions += actionIncrease;
    
    }
    
    //na aankoop kaart buy verminderen
    public void buysDecrement()
    {
        this.buys -= 1;
    
    }
    
    
    //na aankoop muntkaarten coins verhogen
    public void coinsIncrement(int coinsIncrease)
    {
        this.coins += coinsIncrease;
    
    }
    
    public void coinsDecrement(int coinsDecrease)
    {
        this.coins -= coinsDecrease;
    
    }
    
    private final void initRound(){
        actions = 1;
        buys = 1;
        coins = 0;
    }
    
    
}