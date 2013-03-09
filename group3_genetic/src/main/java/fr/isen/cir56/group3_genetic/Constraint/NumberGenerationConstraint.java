package fr.isen.cir56.group3_genetic.Constraint;

import fr.isen.cir56.group3_genetic.Population;

		
public class NumberGenerationConstraint implements ConstraintInterface {
	private final int maximumGenerations;
	
	public NumberGenerationConstraint(int maximumGenerations) {
		this.maximumGenerations = maximumGenerations;
	}

	public int getMaximumGenerations() {
		return maximumGenerations;
	}

	public boolean isReached(Population population) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	
}