package fr.isen.cir56.group3_genetic.Constraint;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.ConstraintParameter;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.DefaultConstructor;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.Name;
import java.util.List;

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
	@DefaultConstructor
	@ConstraintParameter(name="Minimum percentage evolution between two generations", defaultValue="10", maxValue="100")
	public EvolutionConstraint(double minimumPercentageEvolution) {
		this.minimumPercentageEvolution = minimumPercentageEvolution;
	}

	@Override
	public boolean isReached(BreederInterface breeder, PopulationInterface population) {
		PopulationInterface lastPop = breeder.getLastPopulation();
		List<PopulationInterface> populationHistory = breeder.getPopulationsHistory();
		int sizeHistory = populationHistory.size();
		
		if(sizeHistory < 2) {
			return false;
		}
		
		lastPop = populationHistory.get(sizeHistory-2); //select the before-last
		
		double oldValue = lastPop.getBestChromosome().getFitnessValue();
		double newValue = population.getBestChromosome().getFitnessValue();
		
		if(Math.abs(oldValue - newValue)*100/oldValue < minimumPercentageEvolution) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public double getValue() {
		return minimumPercentageEvolution;
	}
	
}
