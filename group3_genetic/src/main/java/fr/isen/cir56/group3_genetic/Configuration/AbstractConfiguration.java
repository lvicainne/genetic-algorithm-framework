package fr.isen.cir56.group3_genetic.Configuration;

import fr.isen.cir56.group3_genetic.Criterion.StopCriterion;
import fr.isen.cir56.group3_genetic.Operator.EvolutionOperator;
import fr.isen.cir56.group3_genetic.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.Probability.UtilsProbability;
import fr.isen.cir56.group3_genetic.Selector.Selector;


public abstract class AbstractConfiguration {

	private Selector selector;
	private StopCriterion stopCriterion;
	private EvolutionOperator evolutionOperator;

	private int initialSizePopulation;
	private float mutationProbability;
	private float crossoverProbability;
	
	public AbstractConfiguration(Selector selector, StopCriterion stopCriterion, int initialSizePopulation) {
		this.setSelector(selector);
		this.setStopCriterion(stopCriterion);
		this.setEvolutionOperator(evolutionOperator);
		this.setInitialSizePopulation(initialSizePopulation);
	}

	public final void setSelector(Selector selector) {
		this.selector = selector;
	}

	public final void setStopCriterion(StopCriterion stopCriterion) {
		this.stopCriterion = stopCriterion;
	}

	public final void setEvolutionOperator(EvolutionOperator evolutionOperator) {
		this.evolutionOperator = evolutionOperator;
	}
	
	public Selector getSelector() {
		return selector;
	}

	public StopCriterion getStopCriterion() {
		return stopCriterion;
	}

	public EvolutionOperator getEvolutionOperator() {
		return evolutionOperator;
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
	
	public int getInitialSizePopulation() {
		return initialSizePopulation;
	}

	private void setInitialSizePopulation(int initialSizePopulation) {
		//initialSizePopulation is Read-Only
		this.initialSizePopulation = initialSizePopulation;
	}
	
	
}