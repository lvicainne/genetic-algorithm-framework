package fr.isen.cir56.group3_genetic.Constraint;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Wizard.Parameter;

public class TimeElapsedConstraint implements ConstraintInterface {
	private final double maximumTimeElapse;
	
	/**
	 * Maximum time for computing generations (in milliseconds)
	 * @param time 
	 */
	@Parameter(value="Maximum time elapsed (in ms)", defaultValue="2000", type=double.class)
	public TimeElapsedConstraint(double time) {
		this.maximumTimeElapse = time;
	}

	public double getMaximumTimeElapse() {
		return maximumTimeElapse;
	}
	
	@Override
	public boolean isReached(BreederInterface breeder, PopulationInterface population) {
		return (breeder.getTimeElapse() > this.maximumTimeElapse);
	}
	
}