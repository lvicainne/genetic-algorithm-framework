package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.Math.Probability.InvalidProbabilityValueException;

public abstract class MutationOperator extends AbstractOperator {
	
	public MutationOperator(float p) throws InvalidProbabilityValueException {
		super(p);
	}
	
	protected void operate(Population population) {
		this.mutation(population);
	}
	
	public abstract void mutation(Population population);
	
}