/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import java.util.Collections;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class UnorderedMutationOperator1 extends AbstractMutationOperator {

	public UnorderedMutationOperator1(float p) throws InvalidProbabilityValueException {
		super(p);
	}
		
	@Override
	public void mutation(ChromosomeInterface chromosome) {
		List<GeneInterface> genesCh1 = chromosome.getGenes();
			
		int size = chromosome.size();
		int alleleLocation1 = AbstractOperator.randomGenerator.nextInt(size);
		int alleleLocation2 = AbstractOperator.randomGenerator.nextInt(size);
		
		//Reverse 2 allèles
		//So 4 new edges !
		Collections.swap(genesCh1, alleleLocation1, alleleLocation2);
	}
	
}
