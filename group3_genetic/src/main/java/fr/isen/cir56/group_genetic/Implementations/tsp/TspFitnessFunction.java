package fr.isen.cir56.group_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.AbstractFitnessFunction;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TspFitnessFunction extends AbstractFitnessFunction {
	private TspChromosomeFactory factory;
	
	public TspFitnessFunction(TspChromosomeFactory factory) {
		this.factory = factory;
	}

	@Override
	protected double evaluate(ChromosomeInterface chromosome) {
		List<GeneInterface> genes = chromosome.getGenes();
		Iterator<GeneInterface> iterator = genes.iterator();
		
		GeneInterface geneSrc = iterator.next();
		GeneInterface geneInit = geneSrc;
		
		double distanceSum = 0;
		while(iterator.hasNext()) {
			GeneInterface geneDst = iterator.next();
			distanceSum += this.factory.distance(geneSrc, geneDst);
			geneSrc = geneDst;
		}
		
		distanceSum += this.factory.distance(geneSrc, geneInit);
		
		return (Integer.MAX_VALUE/10) - distanceSum;
	}
	
}
