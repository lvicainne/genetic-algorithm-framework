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
		List<ChromosomeInterface> people = myPopulation.getChromosomes();
		int sum = 0;

		for (ChromosomeInterface chromosomeInterface : people) { // on calcule la somme des Fitness pour savoir jusqu'où générer le nombre aléatoire
			sum += chromosomeInterface.getFitnessValue();
		}

		for (int i = 0; i < population.size(); i++) {
			int rouletteSum = 0;

			Random rdm = new Random();
			int random = rdm.nextInt((int) Math.rint(sum));
			for (ChromosomeInterface chromosomeInterface : people) {
				rouletteSum += chromosomeInterface.getFitnessValue();
				if (rouletteSum > random) {
					myPopulation.addChromosome(chromosomeInterface);
				}
			}
		}
		return myPopulation;
	}
}