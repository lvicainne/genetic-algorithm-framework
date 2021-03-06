package fr.isen.cir56.group3_genetic.Configuration;

import fr.isen.cir56.group3_genetic.Genotype.AbstractChromosomeFactory;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Operator.OperatorInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Configuration implements GeneticConfigurationInterface {

	private List<ConstraintInterface> constraints;
	private List<SelectorInterface> selectors;
	private List<OperatorInterface> operators;
	private PopulationInterface initialPopulation;
	private AbstractChromosomeFactory chromosomeFactory;
	private int populationSize;
	private boolean isLocked;

	public Configuration() {
		this.constraints = new LinkedList<>();
		this.selectors = new LinkedList<>();
		this.operators = new LinkedList<>();
	}
	
	
	@Override
	public void addConstraint(ConstraintInterface Constraint) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		
		if(Constraint == null) {
			throw new NullPointerException();
		}
		this.constraints.add(Constraint);
	}
	
	@Override
	public void addConstraints(List<ConstraintInterface> constraints) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();

		this.constraints.addAll(constraints);
	}

	@Override
	public void removeConstraint(ConstraintInterface constraint) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.constraints.remove(constraint);
	}

	@Override
	public void removeConstraints() throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.constraints.clear();
	}

	@Override
	public List<ConstraintInterface> getConstraints() {
		return this.constraints;
	}
	
	@Override
	public void addSelector(SelectorInterface selector) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		if(selector == null) {
			throw new NullPointerException();
		}
		this.selectors.add(selector);
	}

	@Override
	public void addSelectors(List<SelectorInterface> selectors) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();

		this.selectors.addAll(selectors);
	}
		
	@Override
	public void removeSelector(SelectorInterface selector) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.selectors.remove(selector);
	}

	@Override
	public void removeSelectors() throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.selectors.clear();
	}

	@Override
	public List<SelectorInterface> getSelectors() {
		return this.selectors;
	}

	@Override
	public void addOperator(OperatorInterface operator) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		if(operator == null) {
			throw new NullPointerException();
		}
		this.operators.add(operator);
	}
	
	@Override
	public void addOperators(List<OperatorInterface> operator) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();

		this.operators.addAll(operator);
	}

	@Override
	public void removeOperator(OperatorInterface operator) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.operators.remove(operator);
	}
	
	@Override
	public void removeOperators() throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.operators.clear();
	}

	@Override
	public List<OperatorInterface> getOperators() {
		return this.operators;
	}

	@Override
	public void setChromosomeFactory(AbstractChromosomeFactory chromosomeFactory) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.chromosomeFactory = chromosomeFactory;
	}

	@Override
	public AbstractChromosomeFactory getChromosomeFactory() throws UnexistingFactoryException {
		if(this.chromosomeFactory == null) {
			throw new UnexistingFactoryException();
		}
		return this.chromosomeFactory;
	}

	/**
	 * The a new size for the population
	 * @param populationSize
	 * @throws InvalidConfigurationException 
	 */
	@Override
	public void setPopulationSize(int populationSize) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.populationSize = populationSize;
	}
	
	/**
	 * Return the configured population size
	 * @return 
	 */
	@Override
	public int getPopulationSize() {
		return populationSize;
	}	

	/**
	 * Set an initial population
	 * @param initialPopulation 
	 */
	@Override
	public void setInitialPopulation(PopulationInterface initialPopulation) {
		this.initialPopulation = initialPopulation;
	}
	
	@Override
	public PopulationInterface getInitialPopulation() {
		if(this.initialPopulation == null) {
			this.initialPopulation = this.chromosomeFactory.getNewPopulation();
		}
		
		return this.initialPopulation;
	}
	
	/**
	 * Reset the initial population to use dor the generation. It is from
	 * the chromosome factory getNewPopulation
	 */
	@Override
	public void resetInitialPopulation() {
		this.initialPopulation = this.chromosomeFactory.getNewPopulation();
	}

	/**
	 * Get if the configuration has been locked, in other words that we can no 
	 * more change parameters.
	 * @return 
	 */
	@Override
	public boolean isLocked() {
		return this.isLocked;
	}

	/**
	 * Lock settings and avoid editing parameters
	 * You hav to unlock its to edit.
	 * @throws InvalidConfigurationException 
	 */
	@Override
	public void lockSettings() throws InvalidConfigurationException {
		this.checkSettings();
		this.isLocked = true;
	}
	
	/**
	 * Unlock settings and permits modify its parameters
	 */
	@Override
	public void unlockSettings() {
		this.isLocked = false;
	}

	/**
	 * Chack settings for a configuration. Thus, at the end of this method
	 * the configuration may be okay with as minium a chomosom factory,
	 * 1 contraint, 1 operator (at leat crossover or mutation), 1 selector
	 * a positive size of population.
	 * @throws InvalidConfigurationException 
	 */
	@Override
	public void checkSettings() throws InvalidConfigurationException {
		
		if(this.chromosomeFactory == null) {
			throw new InvalidConfigurationException("There is no chromosome factory");
		}
		
		if(this.constraints.size() < 1) {
			throw new InvalidConfigurationException("There is no constraint for determining the end of the processing !");
		}

		if(this.operators.size() < 1) {
			throw new InvalidConfigurationException("There is no genetic operator !");
		}
		
		if(this.selectors.size() < 1) {
			throw new InvalidConfigurationException("There is no selector !");
		}
		
		if(this.populationSize < 1) {
			throw new InvalidConfigurationException("The size of the population to use is too weak");
		}
	}
	
	/**
	 * Is called before edition of a parameter.
	 * If the parameter can not be edited (i.e. settings are locked), send an exception
	 * @throws InvalidConfigurationException 
	 */
	private void checkBeforeEditSettings() throws InvalidConfigurationException {
		if(this.isLocked) {
			throw new InvalidConfigurationException("The current configuration is locked !");
		}
	}

}