/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.Chromosome;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeFactoryInterface;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Wasp
 */
public class Min1DChromosomeFactory implements ChromosomeFactoryInterface{
	private final Min1DConfiguration configuration;
	
	public Min1DChromosomeFactory(Min1DConfiguration configuration) {
		this.configuration = configuration;
	}
	
	@Override
	public ChromosomeInterface getNewChromosome() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PopulationInterface getNewPopulation() { // ne marche qu'avec x comme nom de variable
		int numberChromosomes = this.configuration.getPopulationSize();
		PopulationInterface population = new Population(numberChromosomes);
		
		
		
		for (int i = 0; i < numberChromosomes; i++) {
			
			//org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
			
			//myParser.addStandardFunctions();
			//myParser.addStandardConstants();
			
			//myParser.parseExpression(this.min1DConfiguration.getAlgebricExpression());
			double x = this.generateRandomX();
			//myParser.addVariable("x",x);
			
			List<GeneInterface> value = new LinkedList<>();
			//value.add(new Min1DValue(x, myParser.getValue()));
			value.add(new Min1DValue(x));
			
			ChromosomeInterface ch = new Chromosome(this.configuration);
			
			ch.setGenes(value);
		
			population.addChromosome(ch);
		}
		return population;
	}
	
	public double generateRandomX(){ // génère un nombre aléatoire entre min et max
		double x;
		int max = this.configuration.getMax();
		int min = this.configuration.getMin();
		
		double range = max - min;
		
		Random random = new Random();
		x = random.nextDouble() * range + min;
		
		return x;
	}
	
}
