package fr.isen.cir56.group3_genetic.Selector;

import fr.isen.cir56.group3_genetic.PersonInterface;
import fr.isen.cir56.group3_genetic.Population;
import java.util.Collections;
import java.util.List;

public class RankSelector implements SelectorInterface {
	
	

    public Population select(Population population) {
        Population myPopulation = population.clone();
        List<PersonInterface> people = myPopulation.getPeople();

		int size =  people.size();
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += i;            
        }
        
        Collections.sort(people); // on ordonne les personnes en fonction de leur valeur d'évaluation
        
        for (PersonInterface personInterface : people) {
            personInterface.setValue(100/sum*people.indexOf(personInterface)); // la nouvelle valeur ne dépend plus de celle d'avant mais du rang de la personne
        }
        
		return myPopulation;
    }
    
    
    
}