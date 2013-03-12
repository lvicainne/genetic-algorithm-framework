package fr.isen.cir56.group3_genetic.Constraint;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;

public class TimeElapsedConstraint implements ConstraintInterface {
	private final double maximumTimeElapse;
	
	/**
	 * Maximum time for computing generations (in milliseconds)
	 * @param time 
	 */
	public TimeElapsedConstraint(double time) {
		this.maximumTimeElapse = time;
	}

	public double getMaximumTimeElapse() {
		return maximumTimeElapse;
	}
	
	public boolean isReached(BreederInterface breeder, PopulationInterface population) {
		return (breeder.getTimeElapse() > this.maximumTimeElapse);
	}
	
}