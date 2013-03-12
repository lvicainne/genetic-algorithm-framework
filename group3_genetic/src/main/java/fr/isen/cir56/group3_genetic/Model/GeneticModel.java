package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Monitor.AbstractMonitor;
import fr.isen.cir56.group3_genetic.Monitor.Monitor;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;

public class GeneticModel extends Model {
	private PopulationInterface initialPopulation;


	private AbstractMonitor monitor;
	private final ConfigurationInterface configuration;

	public GeneticModel(ConfigurationInterface configuration) {
		super();
		this.configuration = configuration;
		this.initialPopulation = configuration.getInitialPopulation();
		this.monitor = new Monitor(configuration, this);
	}

	public ConfigurationInterface getConfiguration() {
		return this.configuration;
	}

	public AbstractMonitor getMonitor() {
		return monitor;
	}
	
	public PopulationInterface getInitialPopulation() {
		return initialPopulation;
	}

	/**
	 * Reset the InitialPopulation with a new class with new datas.
	 */
	public void resetInitialPopulation() {
		//Create a new population from the configuration and the factory
		this.initialPopulation = new Population(this.getConfiguration(), true);
	}
	
}