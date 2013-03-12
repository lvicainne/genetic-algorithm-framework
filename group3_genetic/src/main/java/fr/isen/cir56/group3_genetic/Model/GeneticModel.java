package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Population;

public class GeneticModel extends Model {

	private int numberCycle;
	Population population;

	public GeneticModel(ConfigurationInterface configuration) {
		super();
		this.population = new Population(configuration);
	}


	public int getNumberCycle() {
		return numberCycle;
	}

	public void incrementNumberCycle() {
		this.numberCycle++;
	}
	
	public void resetPopulation() {
		//@TODO
	}
	
	public Population getPopulation() {
		return this.population;
	}
	
	public ConfigurationInterface getConfiguration() {
		return this.population.getConfiguration();
	}
}