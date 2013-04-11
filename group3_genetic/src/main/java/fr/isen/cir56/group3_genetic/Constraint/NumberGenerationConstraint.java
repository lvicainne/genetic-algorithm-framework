package fr.isen.cir56.group3_genetic.Constraint;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Wizard.Parameter;

		
public class NumberGenerationConstraint implements ConstraintInterface {
	private final int maximumGenerations;
	
	@Parameter(value="Maximum Generation", defaultValue="100", type=int.class)
	public NumberGenerationConstraint(int maximumGenerations) {
		this.maximumGenerations = maximumGenerations;
	}

	public int getMaximumGenerations() {
		return maximumGenerations;
	}

	/*
	 * Is true since at least on
	 */
	@Override
	public boolean isReached(BreederInterface breeder, PopulationInterface population) {
		return (breeder.getNumberGenerations() > this.maximumGenerations);
	}
	
	
}