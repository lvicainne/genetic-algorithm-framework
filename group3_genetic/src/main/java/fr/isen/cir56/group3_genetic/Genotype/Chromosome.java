package fr.isen.cir56.group3_genetic.Genotype;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Configuration.UnexistingFactoryException;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Chromosome<GeneType extends GeneInterface> implements ChromosomeInterface<GeneType> {

	private transient ConfigurationInterface configuration;
	private List<GeneType> genes;
	private double fitnessValue = AbstractFactory.NO_FITNESS_VALUE;
	private int age = 0;

	public Chromosome(ConfigurationInterface configuration) {
		if(configuration == null) {
			throw new InvalidParameterException("You have to specify a configuration for creating a chromosome !");
		}
		this.configuration = configuration;
		this.genes = new LinkedList<>();
	}

	@Override
	public ConfigurationInterface getConfiguration() {
		return this.configuration;
	}

	protected double calcFitnessValue() {
		if (this.configuration != null) {
			try {
				this.configuration.lockSettings();
				this.fitnessValue = this.configuration.getChromosomeFactory().getFitnessValue(this);
			} catch (InvalidConfigurationException | UnexistingFactoryException ex) {
				throw new InvalidParameterException("You have to specify a configuration for creating a chromosome ! Please check your factory and your configuration !");
			}
			
		} else {
			throw new InvalidParameterException("You have to specify a configuration for creating a chromosome !");
		}
		return this.fitnessValue;
	}

	@Override
	public void setFitnessValue(double fitnessValue) {
		if (fitnessValue < 0) {
			throw new NegativeValueException();
		}
		this.fitnessValue = fitnessValue;
	}

	@Override
	public double getFitnessValue() {
		if (this.fitnessValue == AbstractFactory.NO_FITNESS_VALUE) {
			return this.calcFitnessValue();
		}
		return this.fitnessValue;
	}

	@Override
	public void setGenes(List<GeneType> genes) {
		if (genes == null) {
			throw new NullPointerException();
		}

		this.genes = genes;

		this.increaseAge();
		this.calcFitnessValue();
	}

	@Override
	public GeneType getGene(int index) {
		return this.genes.get(index);
	}

	@Override
	public List<GeneType> getGenes() {
		return this.genes;
	}

	@Override
	public int size() {
		return this.genes.size();
	}

	@Override
	public int getAge() {
		return this.age;
	}

	@Override
	public void setAge(int age) {
		if (age < 0) {
			throw new NegativeValueException();
		}
		this.age = age;
	}

	@Override
	public void resetAge() {
		this.age = 0;
	}

	@Override
	public void increaseAge() {
		this.age++;
	}

	/**
	 * Compare two chromosomes with their fitness values
	 * Note: this class has a natural ordering that is
     * inconsistent with equals.
	 * @param o
	 * @return 
	 */
	@Override
	public int compareTo(ChromosomeInterface ch) {
		double nombre1 = ch.getFitnessValue();
		double nombre2 = this.getFitnessValue();
		if (nombre1 > nombre2) {
			return -1;
		} else if (nombre1 == nombre2) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Chromosome) {
			Chromosome otherChromosome = (Chromosome) other;
			return (otherChromosome.configuration.equals(this.configuration)
					&& otherChromosome.age == this.age
					&& otherChromosome.getFitnessValue() == this.getFitnessValue()
					&& otherChromosome.genes.equals(this.genes));
		}
		return false;
	}

	@Override
	public String toString() {
		List<GeneType> myGenes = this.getGenes();
		return myGenes.toString();
	}

	@Override
	public ChromosomeInterface<GeneType> clone() {
		Chromosome<GeneType> ch = new Chromosome(this.getConfiguration());

		List<GeneType> oldList = this.genes;
		for (GeneType item : oldList) {
			ch.genes.add(item);
		}

		ch.calcFitnessValue();
		ch.age = this.age;

		return ch;
	}
}
