/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Breeder;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Operator.OperatorInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Breeder implements BreederInterface {
	/**
	 * An hisotry of each population generated.
	 */
	private List<PopulationInterface> history;
	
	private List<OperatorInterface> operators;
	private List<SelectorInterface> selectors;
	
	private double timeElapse = 0;
	
	public Breeder(ConfigurationInterface configuration) {
		this.operators = configuration.getOperators();
		this.selectors = configuration.getSelectors();
		
		this.history = new LinkedList<PopulationInterface>();
	}
	
	/**
	 * Select the population and then operate on it.
	 * Increment the number of generations made on the population
	 * @param population
	 * @return Population selected and operated
	 */
	public PopulationInterface evolve(PopulationInterface population) {
		long startTime = System.currentTimeMillis();

		
		PopulationInterface selectedPopulation = this.applySelectors(population, this.selectors);
		this.applyOperators(selectedPopulation, this.operators);
		this.history.add(selectedPopulation);
		
		long stopTime = System.currentTimeMillis();
		this.timeElapse += stopTime - startTime;	
		
		return selectedPopulation;
	}
	
	/**
	 * Apply selectors from selectors on the population
	 * @param population
	 * @param selectors
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
	 * Apply operators on the population.
	 * @param population
	 * @param operators 
	 */
	protected void applyOperators(PopulationInterface population, List<OperatorInterface> operators) {
		for (OperatorInterface operator : operators) {
			operator.evaluate(population);
		}
	}

	/**
	 * The number of generations correspond to the number of population generated and saved in the history
	 * @return int
	 */
	public int getNumberGenerations() {
		return this.history.size();
	}
	
	/**
	 * Time elapse for generating the populations (selecting, operating, ...)
	 * @return 
	 */
	public double getTimeElapse() {
		return this.timeElapse;
	}
	
	public List<PopulationInterface> getPopulationsHistory() {
		return this.history;
	}

	public PopulationInterface getLastPopulation() {
		if(this.history.size() < 1) {
			return null;
		}
		
		return this.history.get(this.history.size()-1);
	}
	
	/**
	 * Reset the data about generated population, time elpase, ...
	 */
	public void reset() {
		this.timeElapse = 0;
		this.history = new LinkedList<PopulationInterface>();
	}
}
