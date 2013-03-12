package fr.isen.cir56.group3_genetic.Monitor;

import fr.isen.cir56.group3_genetic.Breeder.Breeder;
import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Monitor extends AbstractMonitor {
	private BreederInterface breeder;
	private boolean suspend = false;
	private boolean stopped = false;
	
	private PopulationInterface lastPopulation = null;

	public Monitor(ConfigurationInterface configuration) {
		super(configuration.getConstraints());
		this.breeder = new Breeder(configuration);
	}
	
	public void start(PopulationInterface population) {
		this.run(population);
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

	public synchronized void stop() {
		this.stopped = true;
	}

	/**
	 * Stop the breeder from generating new populations
	 * Then, wait for a start with a new population
	 */
	public void reset() {
		this.stop();
		this.breeder.reset();
	}

	public synchronized void suspend() {
		this.suspend = true;
	}

	public synchronized void resume() {
		this.suspend = false;
	}

	public PopulationInterface run(PopulationInterface population) {
		PopulationInterface pop = population;
		do {
			pop = breeder.evolve(pop);
			
			while(this.suspend) {
				//wait from the resume
			}
			
		} while(this.hasNextCycle(pop) && !this.stopped);
		
		return pop;
	}
	
}
