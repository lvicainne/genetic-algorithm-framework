/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Breeder;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Operator.OperatorInterface;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Breeder implements BreederInterface {
	
	/**
	 * Select the population and then operate on it.
	 * @param population
	 * @param configuration
	 * @return Population selected and operated
	 */
	public PopulationInterface evolve(PopulationInterface population, ConfigurationInterface configuration) {
		PopulationInterface selectedPopulation = this.applySelectors(population, configuration.getSelectors());
		this.applyOperators(selectedPopulation, configuration.getOperators());
		return selectedPopulation;
	}
	
	/**
	 * Apply selectors from Configuration on the population
	 * @param population
	 * @param configuration
	 * @return Population selected
	 */
	protected PopulationInterface applySelectors(PopulationInterface population, List<SelectorInterface> selectors) {
		PopulationInterface selectedPopulation = population.clone();
		for (SelectorInterface selector : selectors) {
			selectedPopulation = selector.select(selectedPopulation);
		}
		return selectedPopulation;
	}
	
	/**
	 * Apply operators defined in configuration on the population.
	 * @param population
	 * @param configuration 
	 */
	protected void applyOperators(PopulationInterface population, List<OperatorInterface> operators) {
		for (OperatorInterface operator : operators) {
			operator.evaluate(population);
		}
	}
	
}
