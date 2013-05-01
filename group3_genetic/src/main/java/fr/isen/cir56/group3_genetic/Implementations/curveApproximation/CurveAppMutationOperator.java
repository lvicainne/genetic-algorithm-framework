/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.curveApproximation;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Operator.AbstractMutationOperator;
import fr.isen.cir56.group3_genetic.Utils.Math.Geometry.DoublePoint;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Wasp
 */
public class CurveAppMutationOperator extends AbstractMutationOperator{

		public CurveAppMutationOperator(double p) throws InvalidProbabilityValueException {
		super(p);
	}
	
	@Override
	public void mutation(ChromosomeInterface chromosome) {
		List<GeneInterface> genesCh1 = chromosome.getGenes();
			
		GeneInterface geneCh1 = genesCh1.get(0);
		
		CurveAppConfiguration configuration = (CurveAppConfiguration)chromosome.getConfiguration();
		
		double min = configuration.getyMin();
		double max = configuration.getyMax();
		
		double rangeY = max - min;
				
		DoublePoint data = (DoublePoint)geneCh1.getData();
		double x = data.x;
		Random random = new Random();
		double y = random.nextDouble() * rangeY + min;
		
		geneCh1.setData(new DoublePoint(x,y));
		
	}
	
}
