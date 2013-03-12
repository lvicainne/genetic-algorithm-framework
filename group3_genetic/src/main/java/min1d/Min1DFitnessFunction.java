/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package min1d;

import fr.isen.cir56.group3_genetic.AbstractFitnessFunction;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Min1DFitnessFunction extends AbstractFitnessFunction {

	@Override
	protected double evaluate(ChromosomeInterface chromosome) {
		List<GeneInterface> genes = chromosome.getGenes();
		double minValue = Double.MAX_VALUE;
		for (GeneInterface geneInterface : genes) {
			//if(geneInterface instanceof)
		}
		
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
