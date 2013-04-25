package fr.isen.cir56.group3_genetic.Constraint;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;

public interface ConstraintInterface {

	public boolean isReached(BreederInterface breeder, PopulationInterface population);
	
	/**
	 * Return the parameter value defined for the constraint
	 * i.e.  the number of cycles or time elapse for example
	 * @return 
	 */
	public double getValue();

}