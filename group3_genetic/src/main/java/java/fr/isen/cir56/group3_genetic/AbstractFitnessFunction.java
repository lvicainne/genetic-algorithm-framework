package fr.isen.cir56.group3_genetic;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public abstract class AbstractFitnessFunction implements Cloneable {

	public static final double NO_FITNESS_VALUE = -1.0;
	public static final double DELTA = 1.0E-7;
	
	private double lastComputedFitnessValue = NO_FITNESS_VALUE;

	protected abstract double evaluate(ChromosomeInterface chromosome);

	public double getLastComputedFitnessValue() {
		return this.lastComputedFitnessValue;
	}

	public double getFitnessValue(ChromosomeInterface chromosome) {
		double fitnessValue = this.evaluate(chromosome);
		this.lastComputedFitnessValue = fitnessValue;
		
		return fitnessValue;
	}
}