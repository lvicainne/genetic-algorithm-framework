package fr.isen.cir56.group3_genetic.Breeder;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;

/**
 * A breeder evolves a population by performing genetic operations like
 * mutations, crossover, reproduction, etc.
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface BreederInterface {
	
	PopulationInterface evolve(PopulationInterface population, ConfigurationInterface configuration);
	
}
