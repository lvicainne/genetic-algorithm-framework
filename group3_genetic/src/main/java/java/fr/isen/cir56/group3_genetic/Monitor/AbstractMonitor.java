package fr.isen.cir56.group3_genetic.Monitor;

import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.GeneticCommandsInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public abstract class AbstractMonitor implements GeneticCommandsInterface {
	private List<ConstraintInterface> constraints;
	
	public AbstractMonitor(List<ConstraintInterface> constraints) {
		this.constraints = new LinkedList<ConstraintInterface>();
		this.constraints.addAll(constraints);
	}
	
	public List<ConstraintInterface> getConstraints() {
		return this.constraints;
	}
	
	/**
	 * Start the generations.
	 * @param population
	 * @throws InvalidConfigurationException when we lock the configuration from editing
	 */
	public abstract void start(PopulationInterface population);
	public abstract boolean hasNextCycle(PopulationInterface population);
	
}
