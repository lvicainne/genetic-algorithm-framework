package fr.isen.cir56.group3_genetic.Configuration;

import fr.isen.cir56.group3_genetic.Genotype.AbstractFactory;
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
public class Configuration implements ConfigurationInterface {

	private List<ConstraintInterface> constraints;
	private List<SelectorInterface> selectors;
	private List<OperatorInterface> operators;
	private PopulationInterface initialPopulation;
	private AbstractFactory chromosomeFactory;
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
	public void removeConstraint(ConstraintInterface Constraint) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.removeConstraint(Constraint);
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
	public void removeSelector(SelectorInterface selector) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.removeSelector(selector);
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
	public void removeOperator(OperatorInterface operator) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.removeOperator(operator);
	}

	@Override
	public List<OperatorInterface> getOperators() {
		return this.operators;
	}

	@Override
	public void setChromosomeFactory(AbstractFactory chromosomeFactory) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.chromosomeFactory = chromosomeFactory;
	}

	@Override
	public AbstractFactory getChromosomeFactory() throws UnexistingFactoryException {
		if(this.chromosomeFactory == null) {
			throw new UnexistingFactoryException();
		}
		return this.chromosomeFactory;
	}

	@Override
	public void setPopulationSize(int populationSize) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.populationSize = populationSize;
	}
	
	@Override
	public int getPopulationSize() {
		return populationSize;
	}	

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
	
	@Override
	public void resetInitialPopulation() {
		this.initialPopulation = this.chromosomeFactory.getNewPopulation();
	}

	@Override
	public boolean isLocked() {
		return this.isLocked;
	}

	@Override
	public void lockSettings() throws InvalidConfigurationException {
		this.checkSettings();
		this.isLocked = true;
	}

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
	
	private void checkBeforeEditSettings() throws InvalidConfigurationException {
		if(this.isLocked) {
			throw new InvalidConfigurationException("The current configuration is locked !");
		}
	}

}