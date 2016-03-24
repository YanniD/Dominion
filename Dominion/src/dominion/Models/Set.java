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
public class Set {
    private String[] firstGame;
    private String[] bigMoney;
    private String[] interaction;
    private String[] sizeDistortion;
    private String[] villageSquare;
    
    public Set(){
        firstGame = new String[] {"cellar","market","militia","mine","moat","remodel","smithy","vilage","woodcutter","workshop"};
        bigMoney = new String[] {"adventurer","bureaucrat","chancellor","chapel","feast","labatory","market","mine","moneylender","throneRoom"};
        interaction = new String[] {"bureaucrat","chancellor","councilRoom","festival","libary","militia","moat","spy","thief","village"};
        sizeDistortion = new String[]{"cellar","chapel","feast","gardens","laboratory","thief","village","witch","woodcutter","workshop"};
        villageSquare = new String[] {"bureaucrat","cellar","festival","libary","market","remodel","smithy","throneRoom","village","woodcutter"};
    }
    
    public String[] getSet(int preSetChoice) {
        switch(preSetChoice) {
            case 1: return firstGame;
            case 2: return bigMoney;
            case 3: return interaction;        
            case 4: return sizeDistortion;
            case 5: return villageSquare;
            default: System.out.println("Wrong preset");
                     return null;
        }
    }
    
    public void showSetCards(String[] Set) {
        for (int i = 0; i < Set.length; i++) {
            System.out.println(getOneCardOfSet(Set,i));
        }
    }
    
    public String getOneCardOfSet(String[] Set , int i){
            return Set[i];
    }
}
