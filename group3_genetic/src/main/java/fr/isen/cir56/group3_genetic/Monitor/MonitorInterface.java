package fr.isen.cir56.group3_genetic.Monitor;

import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.GeneticCommandsInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface MonitorInterface extends GeneticCommandsInterface {
	
	/**
	 * Start the generations.
	 * @param population
	 * @throws InvalidConfigurationException when we lock the configuration from editing
	 */
	@Override
	public abstract void start();
	
	/**
	 * Determine if the system is ended
	 * @param population
	 * @return true if there is other cycles to compute, false if a constraint is fullfilled
	 */
	public abstract boolean hasNextCycle(PopulationInterface population);
	
}
