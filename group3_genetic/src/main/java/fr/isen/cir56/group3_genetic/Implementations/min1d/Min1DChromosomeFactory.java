package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.AbstractChromosomeFactory;
import fr.isen.cir56.group3_genetic.Genotype.Chromosome;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Geometry.DoublePoint;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.AssociatedView;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.DefaultConstructor;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.Parameter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Wasp
 */
public class Min1DChromosomeFactory extends AbstractChromosomeFactory {
	private final Min1DConfiguration min1Dconfig;
	private final ConfigurationInterface configuration;
	
	public Min1DChromosomeFactory(Min1DConfiguration configuration) {
		this.configuration = configuration;
		this.min1Dconfig = configuration;
	}
	
	@Parameter(name={"Expression","Min","Max"},defaultValue={"x^2","0","+1"})
	@AssociatedView(Min1DChromosomeView.class)
	@DefaultConstructor
	public Min1DChromosomeFactory(ConfigurationInterface configuration, String algebricExpression, int min, int max) {
		Min1DConfiguration myConfiguration = new Min1DConfiguration(algebricExpression, min, max);
		this.min1Dconfig = myConfiguration;
		this.configuration = configuration;
	}

	@Override
	public PopulationInterface getNewPopulation() { // ne marche qu'avec x comme nom de variable
		int numberChromosomes = this.configuration.getPopulationSize();
		PopulationInterface population = new Population(numberChromosomes);
		
		org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
			
		myParser.addStandardFunctions();
		myParser.addStandardConstants();
		myParser.addVariable("x", 0);
			
		for (int i = 0; i < numberChromosomes; i++) {
			
			myParser.parseExpression(this.min1Dconfig.getAlgebricExpression());
			double x = this.generateRandomX();
			myParser.addVariable("x",x);
			
			List<GeneInterface> value = new LinkedList<>();
			value.add(new Min1DValue(x, myParser.getValue()));
			
		
			ChromosomeInterface ch = new Chromosome(this.configuration);
			
			ch.setGenes(value);
		
			population.addChromosome(ch);
		}
		return population;
	}
	
	public double generateRandomX(){ // génère un nombre aléatoire entre min et max
		double x;
		int max = this.min1Dconfig.getMax();
		int min = this.min1Dconfig.getMin();
		
		double range = max - min;
		
		Random random = new Random();
		x = random.nextDouble() * range + min;
		
		return x;
	}
	
		
	@Override
	protected double evaluate(ChromosomeInterface chromosome) {
		List<GeneInterface> genes = chromosome.getGenes();
		
		return ((DoublePoint)genes.get(0).getData()).y;
	}
	
}
