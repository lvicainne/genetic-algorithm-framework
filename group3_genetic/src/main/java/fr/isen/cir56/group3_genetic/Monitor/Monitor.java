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
	private BreederInterface breeder;
	private ConfigurationInterface configuration;


	public Monitor(ConfigurationInterface configuration) {
		super(configuration.getConstraints());
		this.breeder = new Breeder(configuration);
	}
	
	public void start(PopulationInterface population, ConfigurationInterface configuration) {
		
		PopulationInterface pop = population;
		do {
			pop = breeder.evolve(pop);

			
		} while(this.hasNextCycle(pop));
	}

	public boolean hasNextCycle(PopulationInterface population) {
		List<ConstraintInterface> constraints = this.getConstraints();
		for (ConstraintInterface constraint : constraints) {
			if(constraint.isReached(this.breeder, population)) {
				return false;
			}
		}
		return true;
	}


	
}
