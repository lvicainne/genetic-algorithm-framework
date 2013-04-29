package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Genotype.InvalidSizeChromosomeCrossoverException;
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
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Min1DCrossoverOperator extends AbstractCrossoverOperator {
	public static final float probability = 0.5F;
	
	public Min1DCrossoverOperator() throws InvalidProbabilityValueException {
		super(probability);
	}

	public Min1DCrossoverOperator(double probability) throws InvalidProbabilityValueException {
		super(probability);
	}
		
	@Override
	public void crossover(ChromosomeInterface ch1, ChromosomeInterface ch2) {
		List<GeneInterface> genes1 = ch1.getGenes();
		List<GeneInterface> genes2 = ch2.getGenes();
		
		DoublePoint data1 = (DoublePoint)genes1.get(0).getData();
		double x1 = data1.x;
		DoublePoint data2 = (DoublePoint)genes2.get(0).getData();
		double x2 = data2.x;
		
		double result = (x1+x2)/2;
		
		String algebricExpression = ((Min1DConfiguration)ch1.getConfiguration()).getAlgebricExpression();
		org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
			
		myParser.addStandardFunctions();
		myParser.addStandardConstants();
		myParser.addVariable("x", 0);
		
		myParser.parseExpression(algebricExpression);
		myParser.addVariable("x",result);
		
		List<GeneInterface> newGenesCh1 = new ArrayList<>(1);
		newGenesCh1.addAll(genes1);
		newGenesCh1.set(0, new Min1DValue(result, myParser.getValue()));
		
		ch1.setGenes(newGenesCh1);
		//genes1.get(0).setData(new DoublePoint(result, myParser.getValue()));

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
