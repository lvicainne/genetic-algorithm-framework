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

	/*
	 * Est vrai dès qu'un chromosome au moins de la population est âgé du 
	 * nombre mamximum de générations
	 */
	public boolean isReached(Population population) {
		return !(population.getMaximumAge() > this.maximumGenerations);
	}
	
	
}