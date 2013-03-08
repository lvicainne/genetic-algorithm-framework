package fr.isen.cir56.group3_genetic.Configuration;

import fr.isen.cir56.group3_genetic.Criterion.StopCriterion;
import fr.isen.cir56.group3_genetic.Operator.EvolutionOperator;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.Probability.UtilsProbability;
import fr.isen.cir56.group3_genetic.Selector.Selector;


public abstract class AbstractConfiguration {

	private Selector selector;
	private StopCriterion stopCriterion;
	private EvolutionOperator evolutionOperator;
	private Population population;

	protected float mutationProbability;
	protected float crossoverProbability;
	
	public AbstractConfiguration(Selector selector, StopCriterion stopCriterion, EvolutionOperator evolutionOperator, Population population) {
		this.selector = selector;
		this.stopCriterion = stopCriterion;
		this.evolutionOperator = evolutionOperator;
		this.population = population;
	}

	public float getMutationProbability() {
		return mutationProbability;
	}

	public void setMutationProbability(float mutationProbability) throws InvalidProbabilityValueException {
		UtilsProbability.checkProbabilityValue(mutationProbability);
		this.mutationProbability = mutationProbability;
	}

	public float getCrossoverProbability() {
		return crossoverProbability;
	}

	public void setCrossoverProbability(float crossoverProbability) throws InvalidProbabilityValueException {
		UtilsProbability.checkProbabilityValue(mutationProbability);
		this.crossoverProbability = crossoverProbability;
	}
	
}