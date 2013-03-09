package fr.isen.cir56.group3_genetic.Configuration;

import fr.isen.cir56.group3_genetic.AbstractFitnessFunction;
import fr.isen.cir56.group3_genetic.Population;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface ConfigurationInterface {
	
	void setPopulationSize(int populationSize);
	int getPopulationSize();
	
	void setFitnessFunction(AbstractFitnessFunction fitnessFunctionInterface);
	void setInitialPopulation(Population population);
	
	
}
