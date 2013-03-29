package fr.isen.cir56.group3_genetic.Constraint;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class EvolutionConstraint implements ConstraintInterface {
	private final float minimumPercentageEvolution;
	
	EvolutionConstraint(float minimumPercentageEvolution) {
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
