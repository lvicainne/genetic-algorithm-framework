package fr.isen.cir56.group3_genetic;

import fr.isen.cir56.group3_genetic.Genotype.AgeChromosomeComparator;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Population implements PopulationInterface {
	private List<ChromosomeInterface> chromosomes;
	private final int maximumSize;

	/**
	 * Instanciate a new population.
	 * @param maximumSize is the max size of chromosomes allowed by the 
	 * population. If the pop is too numerous, the last chromosomes will be removed
	 */
	public Population(int maximumSize) {
		this(maximumSize, null);
	}
	
	/**
	 * Instanciate a new population.
	 * @param maximumSize is the max size of chromosomes allowed by the 
	 * population. If the pop is too numerous, the last chromosomes will be removed
	 * @param chromosomes
	 */
	public Population(int maximumSize, List<ChromosomeInterface> chromosomes) {
		this.chromosomes = chromosomes;
		this.maximumSize = maximumSize;
		
		if(this.chromosomes == null) {
			this.chromosomes = new ArrayList<>(maximumSize);
		}
		

	}

	@Override
	public void addChromosome(ChromosomeInterface chromosome) {
		this.chromosomes.add(chromosome);
	}

	@Override
	public void addChromosomes(List<ChromosomeInterface> chromosomes) {
		this.chromosomes.addAll(chromosomes);
	}

	@Override
	public void setChromosome(int index, ChromosomeInterface chromosome) {
		this.chromosomes.set(index, chromosome);
	}

	@Override
	public void removeChromosome(ChromosomeInterface chromosome) {
		this.chromosomes.remove(chromosome);
	}

	@Override
	public void removeChromosome(int index) {
		this.chromosomes.remove(index);
	}

	@Override
	public ChromosomeInterface getChromosome(int index) {
		return this.chromosomes.get(index);
	}
	
	@Override
	public List<ChromosomeInterface> getChromosomes() {
		return this.chromosomes;
	}

	@Override
	public Iterator<ChromosomeInterface> chromosomesIterator() {
		return this.chromosomes.iterator();
	}

	@Override
	public void sortChromosomes() {
		Collections.sort(this.chromosomes);
	}

	@Override
	public ChromosomeInterface getBestChromosome() {
		this.sortChromosomes();
		return this.chromosomes.get(0);
	}

	@Override
	public int getMaximumAgeChromosome() {
		return Collections.max(this.chromosomes, new AgeChromosomeComparator()).getAge();
	}
	
	@Override
	public int size() {
		return this.chromosomes.size();
	}
	
	@Override
	public int getMaximumSize() {
		return this.maximumSize;
	}
	
	@Override
	public Population clone() {
		Population clonePopulation = new Population(this.maximumSize);
		List<ChromosomeInterface> myChromosomes = this.getChromosomes();
		
		for (ChromosomeInterface chromosomeInterface : myChromosomes) {
			clonePopulation.addChromosome(chromosomeInterface.clone());
		}
		
		return clonePopulation;
	}

}
