package fr.isen.cir56.group3_genetic;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.AgeChromosomeComparator;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeFactoryInterface;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Population implements PopulationInterface {
	private ConfigurationInterface configuration;
	private List<ChromosomeInterface> chromosomes;
	
	public Population(ConfigurationInterface configuration) {
		this(configuration, null);
	}
	
	/**
	 * Instanciate a new population.
	 * @param configuration
	 * @param chromosomes
	 * @param createInitialChromosomes 
	 */
	public Population(ConfigurationInterface configuration, List<ChromosomeInterface> chromosomes) {
		this.configuration = configuration;
		this.chromosomes = chromosomes;
		
		int populationSize = configuration.getPopulationSize();
		if(this.chromosomes == null) {
			this.chromosomes = new ArrayList<ChromosomeInterface>(populationSize);
		}
		

	}

	public void addChromosome(ChromosomeInterface chromosome) {
		this.chromosomes.add(chromosome);
	}

	public void addChromosomes(List<ChromosomeInterface> chromosomes) {
		this.chromosomes.addAll(chromosomes);
	}

	public void setChromosome(int index, ChromosomeInterface chromosome) {
		this.chromosomes.set(index, chromosome);
	}

	public void removeChromosome(ChromosomeInterface chromosome) {
		this.chromosomes.remove(chromosome);
	}

	public void removeChromosome(int index) {
		this.chromosomes.remove(index);
	}

	public ChromosomeInterface getChromosome(int index) {
		return this.chromosomes.get(index);
	}
	
	public List<ChromosomeInterface> getChromosomes() {
		return this.chromosomes;
	}

	public Iterator<ChromosomeInterface> chromosomesIterator() {
		return this.chromosomes.iterator();
	}

	public void sortChromosomes() {
		Collections.sort(this.chromosomes);
	}

	public ConfigurationInterface getConfiguration() {
		return this.configuration;
	}

	public ChromosomeInterface getBetterChromosome() {
		this.sortChromosomes();
		return this.chromosomes.get(0);
	}

	public int getMaximumAgeChromosome() {
		return Collections.max(this.chromosomes, new AgeChromosomeComparator()).getAge();
	}
	
	public int size() {
		return this.chromosomes.size();
	}
	
	public Population clone() {
		Population clonePopulation = new Population(this.configuration);
		clonePopulation.addChromosomes(this.getChromosomes());
		return clonePopulation;
	}

}
