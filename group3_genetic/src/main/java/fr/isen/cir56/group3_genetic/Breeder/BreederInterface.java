package fr.isen.cir56.group3_genetic.Breeder;

import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.List;

/**
 * A breeder evolves a population by performing genetic operations like
 * mutations, crossover, reproduction, etc.
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface BreederInterface {
	
	PopulationInterface evolve(PopulationInterface population);
	
	int getNumberGenerations();
	double getTimeElapse();
	
	public abstract List<PopulationInterface> getPopulationsHistory();
	public abstract PopulationInterface getLastPopulation();
	
	/**
	 * Reset the data about generated population, time elpase, ...
	 */
	public void reset();
}
