package fr.isen.cir56.group3_genetic.Constraint;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;

		
public class NumberGenerationConstraint implements ConstraintInterface {
	private final int maximumGenerations;
	
	public NumberGenerationConstraint(int maximumGenerations) {
		this.maximumGenerations = maximumGenerations;
	}

	public int getMaximumGenerations() {
		return maximumGenerations;
	}

	/*
	 * Is true since at least on
	 */
	public boolean isReached(BreederInterface breeder, PopulationInterface population) {
		return (breeder.getNumberGenerations() > this.maximumGenerations);
	}
	
	
}