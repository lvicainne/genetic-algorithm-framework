package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.PopulationInterface;

public abstract class MutationOperator extends AbstractOperator {
	
	public MutationOperator(float p) throws InvalidProbabilityValueException {
		super(p);
	}
	
	protected void operate(PopulationInterface population) {
		this.mutation(population);
	}
	
	public abstract void mutation(PopulationInterface population);
	
}