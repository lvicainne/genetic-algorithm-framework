package fr.isen.cir56.group3_genetic.Configuration;

import fr.isen.cir56.group3_genetic.AbstractFitnessFunction;
import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeFactoryInterface;
import fr.isen.cir56.group3_genetic.Operator.OperatorInterface;
import fr.isen.cir56.group3_genetic.Population;
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
	private AbstractFitnessFunction fitnessFunction;
	//private BreederInterface breeder;
	private ChromosomeFactoryInterface chromosomeFactory;
	private int populationSize;
	private boolean isLocked;

	public Configuration() {
		this.constraints = new LinkedList<ConstraintInterface>();
		this.selectors = new LinkedList<SelectorInterface>();
		this.operators = new LinkedList<OperatorInterface>();
	}
	
	
	public void addConstraint(ConstraintInterface Constraint) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		
		if(Constraint == null) {
			throw new NullPointerException();
		}
		this.constraints.add(Constraint);
	}

	public void removeConstraint(ConstraintInterface Constraint) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.removeConstraint(Constraint);
	}

	public List<ConstraintInterface> getConstraints() {
		return this.constraints;
	}
	
	public void addSelector(SelectorInterface selector) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		if(selector == null) {
			throw new NullPointerException();
		}
		this.selectors.add(selector);
	}

	public void removeSelector(SelectorInterface selector) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.removeSelector(selector);
	}

	public List<SelectorInterface> getSelectors() {
		return this.selectors;
	}

	public void addOperator(OperatorInterface operator) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		if(operator == null) {
			throw new NullPointerException();
		}
		this.operators.add(operator);
	}

	public void removeOperator(OperatorInterface operator) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.removeOperator(operator);
	}

	public List<OperatorInterface> getOperators() {
		return this.operators;
	}

	public AbstractFitnessFunction getFitnessFunction() {
		return this.fitnessFunction;
	}

	public void setFitnessFunction(AbstractFitnessFunction fitnessFunction) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.fitnessFunction = fitnessFunction;
	}

	/*public void setBreeder(BreederInterface breeder) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.breeder = breeder;
	}

	public BreederInterface getBreeder() {
		return this.breeder;
	}*/

	public void setChromosomeFactory(ChromosomeFactoryInterface chromosomeFactory) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.chromosomeFactory = chromosomeFactory;
	}

	public ChromosomeFactoryInterface getChromosomeFactory() throws UnexistingFactoryException {
		if(this.chromosomeFactory == null) {
			throw new UnexistingFactoryException();
		}
		return this.chromosomeFactory;
	}

	public void setPopulationSize(int populationSize) throws InvalidConfigurationException {
		this.checkBeforeEditSettings();
		this.populationSize = populationSize;
	}
	
	public int getPopulationSize() {
		return populationSize;
	}	

	public void setInitialPopulation(PopulationInterface initialPopulation) {
		this.initialPopulation = initialPopulation;
	}
	
	
	public PopulationInterface getInitialPopulation() {
		if(this.initialPopulation == null) {
			this.initialPopulation = this.chromosomeFactory.getInitialPopulation();
		}
		
		return this.initialPopulation;
	}

	public boolean isLocked() {
		return this.isLocked;
	}

	public void lockSettings() throws InvalidConfigurationException {
		this.checkSettings();
		this.isLocked = true;
	}

	public void checkSettings() throws InvalidConfigurationException {
		/*if(this.breeder == null) {
			throw new InvalidConfigurationException("There is no breeder");
		}*/
		
		if(this.chromosomeFactory == null) {
			throw new InvalidConfigurationException("There is no chromosome factory");
		}
		
		if(this.fitnessFunction == null) {
			throw new InvalidConfigurationException("There is no fitness function");
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