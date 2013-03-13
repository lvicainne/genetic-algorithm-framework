/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Operator.AbstractMutationOperator;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TspMutationOperator extends AbstractMutationOperator {

	public TspMutationOperator(float p) throws InvalidProbabilityValueException {
		super(p);
	}
	
	@Override
	public void mutation(ChromosomeInterface chromosome) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
