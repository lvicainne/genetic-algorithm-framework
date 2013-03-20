/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Constraint;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class EvolutionConstraint implements ConstraintInterface {
	private final float minimumPercentageEvolution;
	
	EvolutionConstraint(float minimumPercentageEvolution) {
		this.minimumPercentageEvolution = minimumPercentageEvolution;
	}

	public boolean isReached(BreederInterface breeder, PopulationInterface population) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
