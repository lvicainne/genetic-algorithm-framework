package fr.isen.cir56.group3_genetic.Configuration;

import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Genotype.AbstractFactory;
import fr.isen.cir56.group3_genetic.Operator.OperatorInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import java.util.List;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
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
	
	/*
	 * Generate a population at the beginning if the initial population is empty
	 */
	void setChromosomeFactory(AbstractFactory chromosomeFactory) throws InvalidConfigurationException;
	AbstractFactory getChromosomeFactory() throws UnexistingFactoryException;
	
	/**
	 * Set the size of the population to use
	 * @param populationSize 
	 */
	void setPopulationSize(int populationSize) throws InvalidConfigurationException;
	int getPopulationSize();
	PopulationInterface getInitialPopulation();
	
	/**
	 * Set a new initial-population in case of reseting
	 * @param pop 
	 */
	void setInitialPopulation(PopulationInterface pop);
	
	/**
	 * Set an new **automatic** intial-population
	 */
	void resetInitialPopulation();
	
	/*
	 * Locking the settings
	 * Permit a light constructor by passing parameters with accessors
	 */
	boolean isLocked();
	void lockSettings() throws InvalidConfigurationException;
	void checkSettings() throws InvalidConfigurationException;
	
}
