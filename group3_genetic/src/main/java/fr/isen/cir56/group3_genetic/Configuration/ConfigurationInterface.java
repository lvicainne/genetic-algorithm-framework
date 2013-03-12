package fr.isen.cir56.group3_genetic.Configuration;

import fr.isen.cir56.group3_genetic.AbstractFitnessFunction;
import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeFactoryInterface;
import fr.isen.cir56.group3_genetic.Operator.OperatorInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface ConfigurationInterface {
	
	void addConstraint(ConstraintInterface constraint) throws InvalidConfigurationException;
	void removeConstraint(ConstraintInterface contraint) throws InvalidConfigurationException;
	List<ConstraintInterface> getConstraints();
	
	void addSelector(SelectorInterface selector) throws InvalidConfigurationException;
	void removeSelector(SelectorInterface selector) throws InvalidConfigurationException;
	List<SelectorInterface> getSelectors();
	
	void addOperator(OperatorInterface operator) throws InvalidConfigurationException;
	void removeOperator(OperatorInterface operator) throws InvalidConfigurationException;
	List<OperatorInterface> getOperators();
	
	/**
	 * Function which will create the best Chromosome
	 * @param fitnessFunctionInterface
	 * @throws InvalidConfigurationException 
	 */
	void setFitnessFunction(AbstractFitnessFunction fitnessFunctionInterface) throws InvalidConfigurationException;
	AbstractFitnessFunction getFitnessFunction();
	
	/*
	 * Evolves the population
	 */
	void setBreeder(BreederInterface breeder) throws InvalidConfigurationException;
	BreederInterface getBreeder();
	
	/*
	 * Generate a population at the beginning if the initial population is empty
	 */
	void setChromosomeFactory(ChromosomeFactoryInterface chromosomeFactory) throws InvalidConfigurationException;
	ChromosomeFactoryInterface getChromosomeFactory() throws UnexistingFactoryException;
	
	/**
	 * Set the size of the population to use
	 * @param populationSize 
	 */
	void setPopulationSize(int populationSize)  throws InvalidConfigurationException;
	int getPopulationSize();
	PopulationInterface getInitialPopulation();
	
	/*
	 * Locking the settings
	 * Permit a light constructor by passing parameters with accessors
	 */
	boolean isLocked();
	void lockSettings() throws InvalidConfigurationException;
	void checkSettings() throws InvalidConfigurationException;
	
	
}
