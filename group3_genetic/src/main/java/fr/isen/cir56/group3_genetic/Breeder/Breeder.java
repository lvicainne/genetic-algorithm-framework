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
	 * An history of each population generated.
	 */
	private List<PopulationInterface> history;
	private double timeElapse = 0;

	public Breeder() {
		this.history = new LinkedList<>();
	}

	/**
	 * Select the population and then operate on it. Increment the number of
	 * generations made on the population
	 *
	 * @param population
	 * @return Population selected and operated
	 */
	@Override
	public PopulationInterface evolve(PopulationInterface population, List<OperatorInterface> operators, List<SelectorInterface> selectors) {

		long startTime = System.currentTimeMillis();
		
		PopulationInterface studiedPopulation = population.clone();
		this.applyOperators(studiedPopulation, operators);
		PopulationInterface selectedPopulation = this.applySelectors(studiedPopulation, selectors);
		
		this.history.add(selectedPopulation);

		long stopTime = System.currentTimeMillis();
		this.timeElapse += stopTime - startTime;
		
		return selectedPopulation;
	}

	/**
	 * Apply selectors from selectors on the population
	 *
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
	 *
	 * @param population
	 * @param operators
	 */
	protected void applyOperators(PopulationInterface population, List<OperatorInterface> operators) {
		for (OperatorInterface operator : operators) {
			operator.evaluate(population);
		}
	}

	/**
	 * The number of generations correspond to the number of population
	 * generated and saved in the history
	 *
	 * @return int
	 */
	@Override
	public int getNumberGenerations() {
		//number -1 because this number DO NOT HAVE TO contain the initial population.
		return this.history.size()-1;
	}

	/**
	 * Time elapse for generating the populations (selecting, operating, ...)
	 *
	 * @return Time elapse during applying operators and selector
	 */
	@Override
	public double getTimeElapse() {
		return this.timeElapse;
	}

	@Override
	public List<PopulationInterface> getPopulationsHistory() {
		return this.history;
	}

	@Override
	public PopulationInterface getLastPopulation() {
		if (this.history.size() < 1) {
			return null;
		}

		return this.history.get(this.history.size() - 1);
	}

}
