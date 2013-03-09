package fr.isen.cir56.group3_genetic;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Constraint.NumberGenerationConstraint;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeFactoryInterface;
import fr.isen.cir56.group3_genetic.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.Operator.OperatorInterface;
import fr.isen.cir56.group3_genetic.Selector.RankSelector;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import tsp.TspChromosomeFactory;
import tsp.TspCrossoverOperator;
import tsp.TspFitnessFunction;


public class App {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		AbstractFitnessFunction fitnessFunction = new TspFitnessFunction();
		ChromosomeFactoryInterface  chromosomeFactory = new TspChromosomeFactory();
		ConstraintInterface constraint = new NumberGenerationConstraint(10);
		OperatorInterface tspCrossoverOperator = null;
		SelectorInterface selector = new RankSelector();
		
		try {
			tspCrossoverOperator = new TspCrossoverOperator();
		} catch (InvalidProbabilityValueException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		try {
			configuration.addConstraint(constraint);
			configuration.addOperator(tspCrossoverOperator);
			configuration.addSelector(selector);
			configuration.setFitnessFunction(fitnessFunction);
			configuration.setChromosomeFactory(chromosomeFactory);
			configuration.setPopulationSize(5);
			
			
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
	}
}
