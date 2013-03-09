package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.Probability.InvalidProbabilityValueException;

public class MutationOperator extends AbstractOperator {
	
	public MutationOperator(float p) throws InvalidProbabilityValueException {
		super(p);
	}
	
	protected void operate(Population population) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}