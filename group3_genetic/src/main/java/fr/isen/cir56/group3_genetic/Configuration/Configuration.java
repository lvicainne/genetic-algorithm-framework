package fr.isen.cir56.group3_genetic.Configuration;

import fr.isen.cir56.group3_genetic.Criterion.StopCriterion;
import fr.isen.cir56.group3_genetic.Operator.EvolutionOperator;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.Selector.Selector;
import java.util.List;

public class Configuration implements ConfigurationInterface {

	private Selector selector;
	private StopCriterion stopCriterion;
	private EvolutionOperator evolutionOperator;
	private Population population;
	public List myStopCriterion;
	public List mySelector;
	public List myEvolutionOperator;
	public List myPopulation;
}