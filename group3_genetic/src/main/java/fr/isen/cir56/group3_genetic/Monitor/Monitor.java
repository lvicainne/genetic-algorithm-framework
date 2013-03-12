package fr.isen.cir56.group3_genetic.Monitor;

import fr.isen.cir56.group3_genetic.Breeder.Breeder;
import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Monitor extends AbstractMonitor {
	ConfigurationInterface configuration;
	List<PopulationInterface> history;

	public Monitor(ConfigurationInterface configuration) {
		super(configuration.getConstraints());
		this.history = new LinkedList<PopulationInterface>();
	}
	
	public void start(PopulationInterface population, ConfigurationInterface configuration) {
		BreederInterface breeder = new Breeder();
		PopulationInterface pop = population;
		do {
			pop = breeder.evolve(pop, configuration);
			this.history.add(pop);
			
		} while(this.hasNextCycle(pop));
	}

	public boolean hasNextCycle(PopulationInterface population) {
		List<ConstraintInterface> constraints = this.getConstraints();
		for (ConstraintInterface constraint : constraints) {
			if(constraint.isReached(population)) {
				return false;
			}
		}
		return true;
	}

	public List<PopulationInterface> getPopulationsHistory() {
		return this.history;
	}

	public PopulationInterface getLastPopulation() {
		if(this.history.size() < 1) {
			return null;
		}
		
		return this.history.get(this.history.size()-1);
	}
	
}
