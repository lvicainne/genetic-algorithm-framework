/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.curveApproximation;

import fr.isen.cir56.group3_genetic.Genotype.AbstractFactory;
import fr.isen.cir56.group3_genetic.Genotype.Chromosome;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.DefaultConstructor;
import fr.isen.cir56.group3_genetic.Wizard.AssociatedView;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Wasp
 */
public class CurveAppChromosomeFactory extends AbstractFactory {

	private final CurveAppConfiguration configuration;
	
	@AssociatedView(CurveAppChromosomeView.class)
	@DefaultConstructor
	public CurveAppChromosomeFactory(CurveAppConfiguration configuration) {
		this.configuration = configuration;
	}

	
	@Override
	protected double evaluate(ChromosomeInterface chromosome) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PopulationInterface getNewPopulation() {
		int numberChromosomes = this.configuration.getPopulationSize();
		PopulationInterface population = new Population(numberChromosomes);
		
		double min = this.configuration.getMin();
		double max = this.configuration.getMax();
		double range = max - min;
		double disparity = range / numberChromosomes;
		
		double x = min;
		
		// à voir si c'est à rajouter dans la configuration
		double minY = -100;
		double maxY = 100;
		
		for (int i = 0; i < numberChromosomes; i++) {
			
			List<GeneInterface> value = new LinkedList<>();
			Random random = new Random();
			value.add(new CurveAppValue(x, random.nextDouble() * (maxY - minY) + minY));
			
		
			ChromosomeInterface ch = new Chromosome(this.configuration);
			
			ch.setGenes(value);
		
			population.addChromosome(ch);
			
			x += disparity;
		}
		
		return population;
	}
	
}
