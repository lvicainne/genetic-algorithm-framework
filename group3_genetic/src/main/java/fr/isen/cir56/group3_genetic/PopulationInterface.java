package fr.isen.cir56.group3_genetic;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface PopulationInterface extends Cloneable, Serializable {
	
	
	void addChromosome(ChromosomeInterface chromosome);
	void addChromosomes(List<ChromosomeInterface> chromosomes);
	
	void setChromosome(int index, ChromosomeInterface chromosome);
	
	void removeChromosome(ChromosomeInterface chromosome);
	void removeChromosome(int index);

	ChromosomeInterface getChromosome(int index);
	List<ChromosomeInterface> getChromosomes();
	
	Iterator<ChromosomeInterface> chromosomesIterator();
	
	void sortChromosomes();
	
	
	ChromosomeInterface getBestChromosome();
	
	int getMaximumAgeChromosome();
	
	/**
	 * Return the current number of chromosomes stored in the Population
	 * @return 
	 */
	int size();
	/**
	 * Return the maximum number of chromosomes, defined in configuration.
	 * @return 
	 */
	int getMaximumSize();
	
	PopulationInterface clone();
}
