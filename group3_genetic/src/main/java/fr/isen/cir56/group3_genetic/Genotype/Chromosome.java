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
	
	public Chromosome(Configuration configuration) {
		this.configuration = configuration;
		this.genes = new LinkedList<GeneInterface>();
	}

	public Configuration getConfiguration() {
		return this.configuration;
	}
	
	protected double calcFitnessValue() {
		if(this.configuration != null) {
			this.fitnessValue = this.configuration.getFitnessFunction().getFitnessValue(this);
		}
		return this.fitnessValue;
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
	
	public int size() {
		return this.genes.size();
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

    public int compareTo(Object o) {
      double nombre1 = ((Chromosome)o).getFitnessValue(); 
      double nombre2 = this.getFitnessValue(); 
      if (nombre1 > nombre2)  return -1; 
      else if(nombre1 == nombre2) return 0; 
      else return 1; 
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
