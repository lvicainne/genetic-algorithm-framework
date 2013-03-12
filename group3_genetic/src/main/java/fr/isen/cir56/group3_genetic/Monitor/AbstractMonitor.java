/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Monitor;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public abstract class AbstractMonitor {
	private List<ConstraintInterface> constraints;
	
	public AbstractMonitor(List<ConstraintInterface> constraints) {
		this.constraints = new LinkedList<ConstraintInterface>();
		this.constraints.addAll(constraints);
	}
	
	public List<ConstraintInterface> getConstraints() {
		return this.constraints;
	}
	
	public abstract void start(PopulationInterface population, ConfigurationInterface configuration);
	public abstract boolean hasNextCycle(PopulationInterface population);
	public abstract List<PopulationInterface> getPopulationsHistory();
	public abstract PopulationInterface getLastPopulation();
}
