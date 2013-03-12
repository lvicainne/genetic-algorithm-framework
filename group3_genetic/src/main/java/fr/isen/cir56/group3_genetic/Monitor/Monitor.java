package fr.isen.cir56.group3_genetic.Monitor;

import fr.isen.cir56.group3_genetic.Breeder.Breeder;
import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.View.Event;
import java.util.List;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Monitor extends AbstractMonitor {
	private BreederInterface breeder;
	private boolean suspend = false;
	private boolean stopped = false;
	private final GeneticModel model;

	public Monitor(ConfigurationInterface configuration, GeneticModel model) {
		super(configuration.getConstraints());
		this.model = model;
		this.breeder = new Breeder(configuration);
	}

	public BreederInterface getBreeder() {
		return breeder;
	}
	
	public void start(PopulationInterface population) {
		try {
			this.model.getConfiguration().lockSettings();
		} catch (InvalidConfigurationException ex) {
			this.model.refreshViews(new Event(this, ex));
		}
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

	public boolean isStopped() {
		return this.stopped;
	}
	
	/**
	 * Stop the breeder from generating new populations
	 * Then, wait for a start with a new population
	 */
	public void reset() {
		this.stop();
		this.breeder.reset();
		this.suspend = false;
	}

	/**
	 * Suspend the generations
	 * @throws StoppedGenerationException when the generation was stopped (interrupted or done)
	 */
	public synchronized void suspend() {
		if(this.isStopped()) {
			throw new StoppedGenerationException();
		}
		this.suspend = true;
	}

	/**
	 * Try to resume the generations
	 * @throws StoppedGenerationException when the generation was stopped (interrupted or done)
	 */
	public synchronized void resume() throws StoppedGenerationException {
		if(this.isStopped()) {
			throw new StoppedGenerationException();
		}
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
