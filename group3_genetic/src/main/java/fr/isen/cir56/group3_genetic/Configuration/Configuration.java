package fr.isen.cir56.group3_genetic.Configuration;

import fr.isen.cir56.group3_genetic.Criterion.StopCriterion;
import fr.isen.cir56.group3_genetic.Operator.EvolutionOperator;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.Selector.Selector;

public class Configuration extends AbstractConfiguration {

	public Configuration(Selector selector, StopCriterion stopCriterion, EvolutionOperator evolutionOperator, Population population) {
		super(selector, stopCriterion, evolutionOperator, population);
	}

	

}