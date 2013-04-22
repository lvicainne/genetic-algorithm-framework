package fr.isen.cir56.group3_genetic.Constraint;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.ConstraintParameter;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.Name;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
@Name("Evolution Constraint")
public class EvolutionConstraint implements ConstraintInterface {
	private final double minimumPercentageEvolution;
	
	/**
	 * 
	 * @param minimumPercentageEvolution percentage between 0 and 100 
	 */
	@ConstraintParameter(name="Minimum percentage evolution between two generations", defaultValue="10")
	public EvolutionConstraint(double minimumPercentageEvolution) {
		this.minimumPercentageEvolution = minimumPercentageEvolution;
	}

	@Override
	public boolean isReached(BreederInterface breeder, PopulationInterface population) {
		double oldValue = breeder.getLastPopulation().getBestChromosome().getFitnessValue();
		double newValue = population.getBestChromosome().getFitnessValue();
		
		if((oldValue - newValue)/oldValue < minimumPercentageEvolution) {
			return true;
		} else {
			return false;
		}
	}
	
}
