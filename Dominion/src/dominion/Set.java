/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion;


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
    
    public String[] getFirstGame(){
        return firstGame;
    }
    
    public String[] getBigMoney(){
        return bigMoney;
    }
    
    public String[] getInteraction(){
        return interaction;
    }
    
    public String[] getSizeDistortion(){
        return sizeDistortion;
    }
    
    public String[] getVillageSquare(){
        return villageSquare;
    }
    
    public String getSet(String[] Set , int i){
        return Set[i];
    }
}
