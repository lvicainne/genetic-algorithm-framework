/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Utils.Collections.Collections;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class OrderedMutationOperator extends AbstractMutationOperator {

	public OrderedMutationOperator(float p) throws InvalidProbabilityValueException {
		super(p);
	}
		
	@Override
	public void mutation(ChromosomeInterface chromosome) {
		List<GeneInterface> genesCh1 = chromosome.getGenes();
			
		int size = chromosome.size();
		int alleleLocation1 = AbstractOperator.randomGenerator.nextInt(size);
		int alleleLocation2 = AbstractOperator.randomGenerator.nextInt(size);
		
		//Reverse the sublist from allèle 1 to allele 2.
		//Goal : have ONLY 2 new edges instead of 4 new edges
		Collections.reverse(genesCh1, alleleLocation1, alleleLocation2);
	}
	
}
