package fr.isen.cir56.group3_genetic;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;

public abstract class AbstractFitnessFunction implements Cloneable {

	public static final double NO_FITNESS_VALUE = -1.0;
	public static final double DELTA = 1.0E-7;
	
	private double lastComputedFitnessValue = NO_FITNESS_VALUE;

	protected abstract double evaluate(ChromosomeInterface chromosome);

	public double getLastComputedFitnessValue() {
		return this.lastComputedFitnessValue;
	}

	public double getFitnessValue(ChromosomeInterface chromosome) {
		double fitnessValue = chromosome.getFitnessValue();
		
		if(fitnessValue == NO_FITNESS_VALUE) {
			fitnessValue = this.evaluate(chromosome);
			this.lastComputedFitnessValue = fitnessValue;
		}
		return fitnessValue;
	}
}