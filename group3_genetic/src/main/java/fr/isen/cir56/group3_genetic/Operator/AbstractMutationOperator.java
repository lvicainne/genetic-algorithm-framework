package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import java.util.List;

public abstract class AbstractMutationOperator extends AbstractOperator {
	
	public AbstractMutationOperator(double p) throws InvalidProbabilityValueException {
		super(p);
	}
	
	@Override
	protected void operate(PopulationInterface population) {
		List<ChromosomeInterface> chromosomes = population.getChromosomes();
		double operatorProbability = this.getProbability();
		
		for (ChromosomeInterface chromosomeInterface : chromosomes) {
			int p = AbstractOperator.randomGenerator.nextInt(100);
			if(p < operatorProbability) {
				this.mutation(chromosomeInterface);
			}
		}
		
	}
	
	public abstract void mutation(ChromosomeInterface chromosome);
	
}