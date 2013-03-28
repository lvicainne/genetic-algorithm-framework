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
	private final ConfigurationInterface configuration;
	private final Min1DConfiguration min1DConfiguration;
	
	public Min1DChromosomeFactory(ConfigurationInterface configuration, Min1DConfiguration min1DConfiguration) {
		this.configuration = configuration;
		this.min1DConfiguration = min1DConfiguration;
	}
	
	@Override
	public ChromosomeInterface getNewChromosome() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PopulationInterface getInitialPopulation() { // ne marche qu'avec x comme nom de variable
		PopulationInterface population = new Population(this.configuration);
		
		int numberChromosomes = this.configuration.getPopulationSize();
		
		for (int i = 0; i < numberChromosomes; i++) {
			
			org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
			
			myParser.addStandardFunctions();
			myParser.addStandardConstants();
			
			myParser.parseExpression(this.min1DConfiguration.getAlgebricExpression());
			double x = this.generateRandomX();
			myParser.addVariable("x",x);
			
			List<GeneInterface> value = new LinkedList<>();
			value.add(new Min1DValue(x, myParser.getValue()));
			
			ChromosomeInterface ch = new Chromosome(this.configuration);
			
			ch.setGenes(null);
		
			population.addChromosome(ch);
		}
		return population;
	}
	
	public double generateRandomX(){ // génère un nombre aléatoire entre min et max
		double x;
		
		double range = this.min1DConfiguration.getMax() - this.min1DConfiguration.getMin();
		
		Random random = new Random();
		x = random.nextDouble() * range + this.min1DConfiguration.getMin();
		
		return x;
	}
	
}
