package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Operator.CrossoverOperator;
import fr.isen.cir56.group3_genetic.Operator.InvalidCrossoverOperatorException;
import fr.isen.cir56.group3_genetic.Operator.InvalidMutationOperatorException;
import fr.isen.cir56.group3_genetic.Operator.MutationOperator;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.Selector.Selector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneticModel extends Model implements GeneticAlgorithmInterface {

	private Configuration configuration;
	private int numberCycle;
	Population population;
	private final Random randomGenerator;

	public GeneticModel(Configuration configuration) {
		super();
		this.configuration = configuration;
		this.population = new Population(configuration.getInitialSizePopulation());
		this.configuration.getStopCriterion();
		
		this.randomGenerator = new Random();
	}

	public void selection() {
		Selector selector = this.configuration.getSelector();
		selector.select(population);
	}

	public void crossover() throws InvalidCrossoverOperatorException {
		if(this.randomGenerator.nextDouble() >= this.configuration.getCrossoverProbability()) { 
			//no crossover
			return;
		}

		try {
			Method method = configuration.getClass().getMethod("getCrossoverOperator");
			Object obj = method.invoke(configuration);
			
			if(obj instanceof CrossoverOperator) {
				CrossoverOperator crossoverOperator = (CrossoverOperator) obj;
				crossoverOperator.crossover(population);
			} else {
				throw new InvalidCrossoverOperatorException();
			}

		} catch (IllegalAccessException ex) {
			Logger.getLogger(GeneticModel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(GeneticModel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(GeneticModel.class.getName()).log(Level.SEVERE, null, ex);
			
		} catch (NoSuchMethodException ex) {
			System.err.println("There is NOT crossover operator for the current Alogrithm");
		}

		
	}

	public void mutation() throws InvalidMutationOperatorException {
		if(this.randomGenerator.nextDouble() >= this.configuration.getMutationProbability()) { 
			//no mutation
			return;
		}

		try {
			Method method = configuration.getClass().getMethod("getMutationOperator");
			Object obj = method.invoke(configuration);
			
			if(obj instanceof MutationOperator) {
				MutationOperator mutationOperator = (MutationOperator) obj;
				mutationOperator.mutation(population);
			} else {
				throw new InvalidMutationOperatorException();
			}

		} catch (IllegalAccessException ex) {
			Logger.getLogger(GeneticModel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(GeneticModel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(GeneticModel.class.getName()).log(Level.SEVERE, null, ex);
			
		} catch (NoSuchMethodException ex) {
			System.err.println("There is NOT mutation operator for the current Alogrithm");
		}
	}

	public boolean isFinished() {
		return true;
	}

	public int getNumberCycle() {
		return numberCycle;
	}

	public void incrementNumberCycle() {
		this.numberCycle++;
	}
	
	public void resetPopulation() {
		//@TODO
	}
	
}