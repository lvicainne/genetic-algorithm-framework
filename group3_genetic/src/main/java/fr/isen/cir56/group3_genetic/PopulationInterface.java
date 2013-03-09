package fr.isen.cir56.group3_genetic;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface PopulationInterface {
	
	
	void addChromosome(ChromosomeInterface chromosome);
	void addChromosomes(List<ChromosomeInterface> chromosomes);
	
	void setChromosome(int index, ChromosomeInterface chromosome);
	
	void removeChromosome(ChromosomeInterface chromosome);
	void removeChromosome(int index);

	ChromosomeInterface getChromosome(int index);
	List<ChromosomeInterface> getChromosomes();
	
	Iterator<ChromosomeInterface> chromosomesIterator();
	
	void sortChromosomes();
	
	
	
	ConfigurationInterface getConfiguration();
	
	ChromosomeInterface getBetterChromosome();
}
