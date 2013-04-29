package fr.isen.cir56.group3_genetic.Implementations.curveApproximation;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Genotype.InvalidSizeChromosomeCrossoverException;
import fr.isen.cir56.group3_genetic.Implementations.min1d.Min1DConfiguration;
import fr.isen.cir56.group3_genetic.Implementations.min1d.Min1DValue;
import fr.isen.cir56.group3_genetic.Operator.AbstractCrossoverOperator;
import fr.isen.cir56.group3_genetic.Operator.AbstractOperator;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Geometry.DoublePoint;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Adrien Stadler adrien.stadler@gmail.com
 */
public class CurveAppCrossoverOperator extends AbstractCrossoverOperator {
	public static final float probability = 0.5F;
	
	public CurveAppCrossoverOperator() throws InvalidProbabilityValueException {
		super(probability);
	}

	public CurveAppCrossoverOperator(double probability) throws InvalidProbabilityValueException {
		super(probability);
	}
		
	@Override
	public void crossover(ChromosomeInterface ch1, ChromosomeInterface ch2) {
		List<GeneInterface> genes1 = ch1.getGenes();
		List<GeneInterface> genes2 = ch2.getGenes();
		
		DoublePoint data1 = (DoublePoint)genes1.get(0).getData();
		double y1 = data1.y;
		double x1 = data1.x;
		DoublePoint data2 = (DoublePoint)genes2.get(0).getData();
		double y2 = data2.y;
		double x2 = data2.x;
		
		List<GeneInterface> newGenesCh1 = new ArrayList<>(1);
		newGenesCh1.addAll(genes1);
//		newGenesCh1.set(0, new CurveAppValue(x1,(y1+y2)/2));
		newGenesCh1.set(0, new CurveAppValue((x1+x2)/2,(y1+y2)/2));
		
		ch1.setGenes(newGenesCh1);
		
		//gene1.setData(new DoublePoint((x1+x2)/2,(y1+y2)/2));
		
	
	}
	
	 @Override
	protected void operate(PopulationInterface population) throws InvalidSizeChromosomeCrossoverException {
		
		int populationSize = population.size();
		int numberOfCrossovers = (int) ((double) this.getProbability())*populationSize;
		List<ChromosomeInterface> newChromosomes = new LinkedList<>();
		
		for (int i = 0; i < numberOfCrossovers; i++) {
			ChromosomeInterface ch1 = population.getChromosome(AbstractOperator.randomGenerator.nextInt(populationSize));
			ChromosomeInterface ch2 = population.getChromosome(AbstractOperator.randomGenerator.nextInt(populationSize));
			
			if(ch1.size() != ch2.size()) {
				throw new InvalidSizeChromosomeCrossoverException();
			}
			
			ChromosomeInterface ch1new = ch1.clone();
			ChromosomeInterface ch2new = ch2.clone();
			
			this.crossover(ch1new, ch2new);
			
			newChromosomes.add(ch1new);

		}
		
		population.addChromosomes(newChromosomes);
		
	}
	
}
