package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.Probability.InvalidProbabilityValueException;

public abstract class CrossoverOperator extends AbstractOperator {

	public CrossoverOperator(float p) throws InvalidProbabilityValueException {
		super(p);
	}
	
	protected void operate(Population population) {
		this.crossover(population);
	}
	
	public abstract void crossover(Population population);
}