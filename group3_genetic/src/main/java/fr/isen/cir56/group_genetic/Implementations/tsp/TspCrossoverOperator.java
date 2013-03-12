/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Operator.CrossoverOperator;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.Operator.AbstractOperator;
import java.util.List;

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
	public void crossover(ChromosomeInterface ch1, ChromosomeInterface ch2) {
		throw new UnsupportedOperationException("Not supported yet.");
		/**
		 * 			List<GeneInterface> genesCh1 = ch1.getGenes();
			List<GeneInterface> genesCh2 = ch2.getGenes();
			
			int alleleLocation = AbstractOperator.randomGenerator.nextInt(ch1.size());
			
			gene1 = genesCh1.get()
			this.crossover(ch1, ch2);
		 */
	}
	
}
