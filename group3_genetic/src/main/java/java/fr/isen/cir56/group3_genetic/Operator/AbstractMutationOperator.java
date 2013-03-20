package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.PopulationInterface;

public abstract class AbstractMutationOperator extends AbstractOperator {
	
	public AbstractMutationOperator(float p) throws InvalidProbabilityValueException {
		super(p);
	}
	
	protected void operate(PopulationInterface population) {
		int populationSize = population.size();
		int numberOfMutations = (int) ((double) this.getProbability())*populationSize;
		
		for (int i = 0; i < numberOfMutations; i++) {
			ChromosomeInterface ch1 = population.getChromosome(AbstractOperator.randomGenerator.nextInt(populationSize));
			this.mutation(ch1);
		}
	}
	
	public abstract void mutation(ChromosomeInterface chromosome);
	
}