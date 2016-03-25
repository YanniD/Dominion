/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;
import dominion.Database.DatabaseService;
import dominion.Models.Card;
import dominion.Models.CardType;
import dominion.Models.Deck;
import dominion.Speler;
import dominion.Models.*;
import java.util.ArrayList;
/**
 *
 * @author Yanni
 */
public class Abilities {
    public Abilities(){
        
    }
    public void cellarAbility(Speler speler,Deck handDeck , Deck discardDeck){
       
        int nrActions = speler.getActions();
        speler.actionIncrement(nrActions);
        while(//user not decided to stop)
        {
            int timesDiscarded =+1 ;
            handDeck.moveOneCardToOtherDeck(discardDeck); // moveOneCardToOtherDeck
        }
        
        // Hierna nog discard any number of cards en +1 card trekken per kaart je hebt discarded
        
    }
    public void MarketAbility(Speler speler,Deck huidigeDeck,Card nieuweKaarten){
        speler.actionIncrement(1);
        speler.buysIncrement();
        speler.coinsIncrement(1);
        
        //trek 1 kaart erbij
        int index = huidigeDeck.getLengthFromDeck() -1 ;
        // index voor op het einde toe te voegen
        huidigeDeck.addToDeck(index,nieuweKaarten);
        
    }
    public void MilitiaAbility(Speler speler1,Speler speler2,Deck huidigeDeck,Deck discardDeck){
        speler1.coinsIncrement(2);
        for (int i = 0 ; i <3; i++){
            huidigeDeck.moveOneCardToOtherDeck(discardDeck); //moveOneCardToOtherDeck moet nog geschreven worden
        }
    }
    public void MineAbility(){
        //trash ability nog niet in de base Deck , moetn og gebeuren
    }
    public Deck SmithyAbility(Deck huidigeDeck,Card nieuweKaarten){
        /* Moet deze kaart 3 kaarten toevoegen aan je draw deck , dan is het dit , als het moet gebeuren in je handDeck moet deze code aangepast worden aan de nieuwe java class
        handDeck
        */
        int index = huidigeDeck.getLengthFromDeck() -1 ;
        for (int i = 0 ; i<3 ; i++){
            huidigeDeck.addToDeck(index,nieuweKaarten );
        }
        return huidigeDeck;
        
    }
    public Deck VillageAbility(Speler speler,Deck huidigeDeck,Card nieuweKaart){
        speler.actionIncrement(2);
        //trek 1 kaart erbij
        // index voor op het einde toe te voegen (handDeck of Drawdeck???!)
        //dit is currently voor de drawDeck
        int index = huidigeDeck.getLengthFromDeck() -1;
        huidigeDeck.addToDeck(index,nieuweKaart);
        return huidigeDeck;
        }
    
    public void WoodercutterAbility(Speler speler){
        // +1 buy en +2 coins
        speler.buysIncrement();
        speler.coinsIncrement(2);
    }
    public Deck WorkshopAbility(Deck huidigeDeck, Card gekozenKaart){
        //gains a card costing up to 4 gold
        DatabaseService db = new DatabaseService();
        int cost = gekozenKaart.getCost();
        if (cost <= 4){
            int index = huidigeDeck.getLengthFromDeck() -1;
            huidigeDeck.addToDeck(index, gekozenKaart); 
        }
        return huidigeDeck;
    }
    
    public void CopperAbility(Speler speler){
        speler.coinsIncrement(1);
    }
    
    public void SilverAbility(Speler speler){
        speler.coinsIncrement(2);
    }
    
    public void GoldAbility(Speler speler){
        speler.coinsIncrement(3);
    }
    
    public void EstateAbility(Speler speler){
        speler.victoryPointsIncrement(1);
    }
    
    public void DuchyAbility(Speler speler){
        speler.victoryPointsIncrement(3);
    }
    
    public void ProvinceAbilty(Speler speler){
        speler.victoryPointsIncrement(6);
    }
    
    public void ChancellorAbililty(Speler speler, Deck handDeck,Deck discardDeck){
        speler.coinsIncrement(2);
        handDeck.moveCardsToOtherDeck(discardDeck);
    }
   
}
