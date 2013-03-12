/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.PopulationInterface;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ReproductionOperator extends AbstractOperator {
	private final int minimumSizePopulation;


	
	ReproductionOperator(int minimumSizePopulation) throws InvalidProbabilityValueException {
		super(1.0F); //Probability of 1 = ALWAYS producted
		this.minimumSizePopulation = minimumSizePopulation;
	}
	
	public int getMinimumSizePopulation() {
		return minimumSizePopulation;
	}
	
	@Override
	protected void operate(PopulationInterface population) {
		/*while(minimumSizePopulation > population.size()) {
			population.addChromosome(null);
		}
		population.size();*/
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
