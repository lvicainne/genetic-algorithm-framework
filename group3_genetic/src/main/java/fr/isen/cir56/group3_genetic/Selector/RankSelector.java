package fr.isen.cir56.group3_genetic.Selector;

import fr.isen.cir56.group3_genetic.PersonInterface;
import fr.isen.cir56.group3_genetic.Population;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RankSelector implements Selector {

    public void select(Population population) {
        
        int sum = 0;
        
        for (int i = 0; i < population.people.size(); i++) {
            sum += i;            
        }
        
        Collections.sort(population.people); // on ordonne les personnes en fonction de leur valeur d'évaluation
        
        for (PersonInterface personInterface : population.people) {
            personInterface.setValue(100/sum*population.people.indexOf(personInterface)); // la nouvelle valeur ne dépend plus de celle d'avant mais du rang de la personne
        }
        
    }
    
    
    
}