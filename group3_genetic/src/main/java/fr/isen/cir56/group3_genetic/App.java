package fr.isen.cir56.group3_genetic;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Constraint.NumberGenerationConstraint;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Implementations.tsp.City;
import fr.isen.cir56.group3_genetic.Implementations.tsp.TspChromosomeFactory;
import fr.isen.cir56.group3_genetic.Operator.OrderedCrossoverOperator;
import fr.isen.cir56.group3_genetic.Implementations.tsp.TspFitnessFunction;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.Operator.OperatorInterface;
import fr.isen.cir56.group3_genetic.Operator.OrderedMutationOperator;
import fr.isen.cir56.group3_genetic.Selector.RankSelector;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class App {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		TspChromosomeFactory  chromosomeFactory = new TspChromosomeFactory(configuration);
		AbstractFitnessFunction fitnessFunction = new TspFitnessFunction(chromosomeFactory);
		ConstraintInterface constraint = new NumberGenerationConstraint(10);
		SelectorInterface selector = new RankSelector();
		
		try {
			configuration.addConstraint(constraint);
			configuration.addOperator(new OrderedCrossoverOperator(1.0F));
			configuration.addOperator(new OrderedMutationOperator(1.0F));
			configuration.addSelector(selector);
			configuration.setFitnessFunction(fitnessFunction);
			configuration.setChromosomeFactory(chromosomeFactory);
			configuration.setPopulationSize(5);
			
		} catch (InvalidProbabilityValueException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidConfigurationException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}
		/*
		Genotype population = Genotype.randomInitialGenotype(configuration);
		for( int i = 0; i < 10; i++ ) {
		    population.evolve();
		}*/
		
		GeneticModel model = new GeneticModel(configuration);
		GeneticController controller = new GeneticController(model);
		
		controller.start();
		
		
		
		System.out.println("Hello World!");
		
		PopulationInterface pop = model.getLastPopulation();
		pop.sortChromosomes();
		List<ChromosomeInterface> chro = pop.getChromosomes();
		int i = 0;
		for (ChromosomeInterface chromosomeInterface : chro) {
			i++; 
			System.out.println(i+" : " + chromosomeInterface.getFitnessValue() + " " + chromosomeInterface.getGenes());
		}
		System.out.println(model.getLastPopulation().getBetterChromosome().getGenes());
	}
}
