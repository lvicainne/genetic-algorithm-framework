package fr.isen.cir56.group3_genetic.Selector;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.List;
import java.util.Random;

public class FortuneSelector implements SelectorInterface {

    public PopulationInterface select(PopulationInterface population) {
        PopulationInterface myPopulation = population.clone();
        List<ChromosomeInterface> people = myPopulation.getChromosomes();
        int sum = 0;
        
        for (ChromosomeInterface personInterface : people) {
            sum += personInterface.getFitnessValue();
        }
        
        Random rdm = new Random();
        int random = rdm.nextInt((int)Math.round(sum));
		
		return myPopulation;
    }
}