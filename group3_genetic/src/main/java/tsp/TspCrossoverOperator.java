/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import fr.isen.cir56.group3_genetic.Operator.CrossoverOperator;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.Math.Probability.InvalidProbabilityValueException;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TspCrossoverOperator extends CrossoverOperator {
	public static final float probability = 0.5F;

	public TspCrossoverOperator() throws InvalidProbabilityValueException {
		super(probability);
	}
		
	@Override
	public void crossover(Population population) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
