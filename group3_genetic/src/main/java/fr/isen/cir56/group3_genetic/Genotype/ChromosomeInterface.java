package fr.isen.cir56.group3_genetic.Genotype;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface ChromosomeInterface extends Comparable, Cloneable {
	Configuration getConfiguration();
	
	public void setFitnessValue(double value);
	public double getFitnessValue();
	
	public void setGenes(List<GeneInterface> genes);
	public GeneInterface getGene(int index);
	public List<GeneInterface> getGenes();
	
	/**
	 * Get the number of genes saved in the chromosome
	 * @return int
	 */
	public int size();
	
	void setIsSelectedForNextGeneration(boolean isSelected);
	boolean isSelectedForNextGeneration();
	
	int getAge();
	void setAge(int age);
	void resetAge();
	void increaseAge();
	
}
