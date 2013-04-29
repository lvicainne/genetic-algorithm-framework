package fr.isen.cir56.group3_genetic.Genotype;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface ChromosomeInterface<GeneType extends GeneInterface> extends Comparable<ChromosomeInterface>, Cloneable, Serializable  {
	
	public ConfigurationInterface getConfiguration();
	
	public void setFitnessValue(double value);
	public double getFitnessValue();
	
	public void setGenes(List<GeneType> genes);
	public GeneType getGene(int index);
	public List<GeneType> getGenes();
	
	/**
	 * Get the number of genes saved in the chromosome
	 * @return int
	 */
	public int size();
	
	int getAge();
	void setAge(int age);
	void resetAge();
	void increaseAge();
	
	ChromosomeInterface<GeneType> clone();
	
}
