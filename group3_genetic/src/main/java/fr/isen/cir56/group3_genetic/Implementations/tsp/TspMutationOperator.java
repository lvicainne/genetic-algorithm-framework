/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.Operator.AbstractMutationOperator;
import fr.isen.cir56.group3_genetic.PopulationInterface;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TspMutationOperator extends AbstractMutationOperator {

	public TspMutationOperator(float p) throws InvalidProbabilityValueException {
		super(p);
	}
	
	@Override
	public void mutation(PopulationInterface population) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
