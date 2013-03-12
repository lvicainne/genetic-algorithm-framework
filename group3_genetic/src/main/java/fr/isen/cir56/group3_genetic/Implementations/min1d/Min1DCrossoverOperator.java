/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.Operator.CrossoverOperator;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Min1DCrossoverOperator extends CrossoverOperator {
	public static final float probability = 0.5F;
	
	public Min1DCrossoverOperator() throws InvalidProbabilityValueException {
		super(probability);
	}

	@Override
	public void crossover(ChromosomeInterface ch1, ChromosomeInterface ch2) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
