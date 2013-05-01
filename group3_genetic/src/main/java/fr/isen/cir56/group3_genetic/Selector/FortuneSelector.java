package fr.isen.cir56.group3_genetic.Selector;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.List;
import java.util.Random;

public class FortuneSelector implements SelectorInterface {

	@Override
	public PopulationInterface select(PopulationInterface population) {
        PopulationInterface myPopulation = population.clone();
       myPopulation.removeChromosomes();
        List<ChromosomeInterface> people = population.getChromosomes();
		
		double sum = 0;
		int j = 0;
		for (ChromosomeInterface chromosomeInterface : people) { // on calcule la somme des Fitness pour savoir jusqu'où générer le nombre aléatoire
			sum += (chromosomeInterface.getFitnessValue());
			j++;
		}
		
		int maxSize = population.getMaximumSize();
		Random rdm = new Random();
        while(myPopulation.getChromosomes().size() < maxSize) {
			int rouletteSum = 0;
			
			double random = rdm.nextDouble()*sum;
			for (ChromosomeInterface chromosomeInterface : people) {
				rouletteSum += (chromosomeInterface.getFitnessValue());
				if (rouletteSum < random) {
					myPopulation.addChromosome(chromosomeInterface);
					break;
				}
			}
		}
		return myPopulation;
	}
}