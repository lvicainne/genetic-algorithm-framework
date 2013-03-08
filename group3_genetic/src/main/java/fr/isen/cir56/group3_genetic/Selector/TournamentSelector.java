package fr.isen.cir56.group3_genetic.Selector;

import fr.isen.cir56.group3_genetic.PersonInterface;
import fr.isen.cir56.group3_genetic.Population;
import java.util.LinkedList;
import java.util.List;

public class TournamentSelector implements Selector {

    public void select(Population population) {
        
    }
	
    public void tournament(List<PersonInterface> players){
        List<PersonInterface> result = new LinkedList();
        for (int i = 0; i < players.size(); i+=2) {
            
            
        }
    }
   
}