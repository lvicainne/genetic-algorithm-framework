/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Genotype.InvalidSizeChromosomeCrossoverException;
import fr.isen.cir56.group3_genetic.Operator.AbstractCrossoverOperator;
import fr.isen.cir56.group3_genetic.Operator.AbstractOperator;
import fr.isen.cir56.group3_genetic.PopulationInterface;
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

	@Override
	public void crossover(ChromosomeInterface ch1, ChromosomeInterface ch2) {
		List<GeneInterface> genes1 = ch1.getGenes();
		List<GeneInterface> genes2 = ch2.getGenes();
		
		double data1 = (double)genes1.get(0).getData();
		double data2 = (double)genes2.get(0).getData();
		
		genes1.get(0).setData((data1+data2)/2);
	
		// si il y a  un bug, c'est que la valeur de y n'a pas été changée dans un update
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
