package fr.isen.cir56.group3_genetic.Selector;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.Collections;
import java.util.List;

public class RankSelector implements SelectorInterface {
	
	

	@Override
    public PopulationInterface select(PopulationInterface population) {
        PopulationInterface myPopulation = population.clone();
        List<ChromosomeInterface> people = myPopulation.getChromosomes();

		int size =  people.size();
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += i;            
        }
        
        Collections.sort(people); // on ordonne les personnes en fonction de leur valeur d'évaluation
        
        for (ChromosomeInterface personInterface : people) {
            personInterface.setFitnessValue(100/sum*people.indexOf(personInterface)); // la nouvelle valeur ne dépend plus de celle d'avant mais du rang de la personne
        }
        
		return myPopulation;
    }
    
    
    
}