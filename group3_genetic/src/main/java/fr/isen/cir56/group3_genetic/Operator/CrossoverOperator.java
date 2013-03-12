package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.Population;
import java.util.List;

public abstract class CrossoverOperator extends AbstractOperator {

	public CrossoverOperator(float p) throws InvalidProbabilityValueException {
		super(p);
	}
	
	/**
	 * Determine which chromosomes for making a crossver between them
	 * @param population 
	 */
	protected void operate(Population population) {
		
		int populationSize = population.size();
		int numberOfCrossovers = (int) ((double) this.getProbability())*populationSize;
		
		for (int i = 0; i < numberOfCrossovers; i++) {
			ChromosomeInterface ch1 = population.getChromosome(AbstractOperator.randomGenerator.nextInt(populationSize));
			ChromosomeInterface ch2 = population.getChromosome(AbstractOperator.randomGenerator.nextInt(populationSize));
	
			this.crossover(ch1, ch2);
		}
		
	}
	
	/**
	 * Make a crossover between the 2 chromosomes passed in parameter
	 * @param ch1
	 * @param ch2 
	 */
	public abstract void crossover(ChromosomeInterface ch1, ChromosomeInterface ch2);
}