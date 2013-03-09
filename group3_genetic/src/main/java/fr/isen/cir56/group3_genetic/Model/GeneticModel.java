package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Population;
import java.util.Random;

public class GeneticModel extends Model {

	private Configuration configuration;
	private int numberCycle;
	Population population;
	private final Random randomGenerator;

	public GeneticModel(Configuration configuration) {
		super();
		this.configuration = configuration;
		this.population = new Population(configuration.getPopulationSize());
		
		this.randomGenerator = new Random();
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
	
}