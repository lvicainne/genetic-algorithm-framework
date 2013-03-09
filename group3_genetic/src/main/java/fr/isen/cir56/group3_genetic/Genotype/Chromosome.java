package fr.isen.cir56.group3_genetic.Genotype;

import fr.isen.cir56.group3_genetic.AbstractFitnessFunction;
import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Chromosome implements ChromosomeInterface {
	private Configuration configuration;
	private List<GeneInterface> genes;
	private double fitnessValue = AbstractFitnessFunction.NO_FITNESS_VALUE;
	private boolean isSelected = false;
	private int age = 0;
	
	Chromosome(Configuration configuration) {
		this.configuration = configuration;
		this.genes = new LinkedList<GeneInterface>();
	}

	public Configuration getConfiguration() {
		return this.configuration;
	}
	
	protected double calcFitnessValue() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void setFitnessValue(double fitnessValue) {
		if(fitnessValue < 0) {
			throw new NegativeValueException();
		}
		this.fitnessValue = fitnessValue;
	}

	public double getFitnessValue() {
		return this.fitnessValue;
	}

	public void setGenes(List<GeneInterface> genes) {
		if(genes == null) {
			throw new NullPointerException();
		}
		this.genes = genes;
	}

	public GeneInterface getGene(int index) {
		return this.genes.get(index);
	}

	public List<GeneInterface> getGenes() {
		return this.genes;
	}

	public void setIsSelectedForNextGeneration(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isSelectedForNextGeneration() {
		return this.isSelected;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		if(age < 0) {
			throw new NegativeValueException();
		}
		this.age = age;
	}

	public void resetAge() {
		this.age = 0;
	}

	public void increaseAge() {
		this.age++;
	}

	/**
	 * Compare two chromosomes with their number of genes
	 * and then with the value of their genes
	 * @param other
	 * @return 
	 * 
	 */
	public int compareTo(Object other) {
		if(other instanceof Chromosome) {
			Chromosome otherChromosome = (Chromosome) other;
			
			int otherNumberOfGenes = otherChromosome.genes.size();
			int currentNumberOfGenes = this.genes.size();
			
			if(otherNumberOfGenes > currentNumberOfGenes) {
				return -1; //this chromosome is less than the other
			}
			//@TODO
			throw new UnsupportedOperationException("Not supported yet.");
			
		}
		throw new UnsupportedOperationException("We can't compare Chromosome with other thing than Chromosome.");
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Chromosome) {
			Chromosome otherChromosome = (Chromosome) other;
			return (otherChromosome.configuration.equals(this.configuration)
					&& otherChromosome.age == this.age
					&& otherChromosome.fitnessValue == this.fitnessValue
					&& otherChromosome.genes.equals(this.genes));
		}
		return false;
	}

}
