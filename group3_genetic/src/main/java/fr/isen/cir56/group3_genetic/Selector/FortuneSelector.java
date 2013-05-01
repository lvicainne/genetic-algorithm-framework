package fr.isen.cir56.group3_genetic.Selector;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.List;
import java.util.Random;

public class FortuneSelector implements SelectorInterface {

	@Override
	public PopulationInterface select(PopulationInterface population) {
		PopulationInterface myPopulation = new Population(population.getMaximumSize());
        List<ChromosomeInterface> people = population.getChromosomes();
		
		double sum = 0;
		int j = 0;
		for (ChromosomeInterface chromosomeInterface : people) { // on calcule la somme des Fitness pour savoir jusqu'où générer le nombre aléatoire
			sum += 1/(chromosomeInterface.getFitnessValue());
			j++;
		}
		
		for (int i = 0; i < people.size() - population.getMaximumSize(); i++) {
			int rouletteSum = 0;

			Random rdm = new Random();
			double random = rdm.nextDouble()*sum;
			for (ChromosomeInterface chromosomeInterface : people) {
				rouletteSum += 1/(chromosomeInterface.getFitnessValue());
				if (rouletteSum > random) {
					myPopulation.addChromosome(chromosomeInterface);
					break;
				}
			}
		}
		return myPopulation;
	}
}