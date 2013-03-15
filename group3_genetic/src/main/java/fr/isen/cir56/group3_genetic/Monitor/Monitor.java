package fr.isen.cir56.group3_genetic.Monitor;

import fr.isen.cir56.group3_genetic.Analyzer.Analyzer;
import fr.isen.cir56.group3_genetic.Analyzer.AnalyzerInterface;
import fr.isen.cir56.group3_genetic.Breeder.Breeder;
import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Event.EndGenerationEvent;
import fr.isen.cir56.group3_genetic.Event.StartingGenerationEvent;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.View.Event;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Monitor extends AbstractMonitor {
	private BreederInterface breeder;
	private ThreadState state;
	private final GeneticModel model;
	private RuntimeThread thread;

	public Monitor(ConfigurationInterface configuration, GeneticModel model) {
		super(configuration.getConstraints());
		this.model = model;
		this.breeder = new Breeder(configuration);
		
	}

	public BreederInterface getBreeder() {
		return breeder;
	}
	
	@Override
	public void start(PopulationInterface population) {
		this.model.refreshViews(new StartingGenerationEvent(this.model));
		
		
		try {
			this.model.getConfiguration().lockSettings();
		} catch (InvalidConfigurationException ex) {
			this.model.refreshViews(new Event(this.model, ex));
		}
		
		this.state = ThreadState.STARTED;
		this.thread = new RuntimeThread(this);
		this.thread.setSourcePopulation(population);
		this.model.refreshViews(new Event(this.model));
		this.thread.start();
		

	}
	
	@Override
	public boolean hasNextCycle(PopulationInterface population) {
		List<ConstraintInterface> constraints = this.getConstraints();
		for (ConstraintInterface constraint : constraints) {
			if(constraint.isReached(this.breeder, population)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public synchronized void stop() {
		this.state = ThreadState.WAITING;
		AnalyzerInterface analyzer = new Analyzer();
		analyzer.setBreeder(this.model.getMonitor().getBreeder());
			
		this.model.refreshViews(new EndGenerationEvent(this.model, analyzer));
		this.thread = null;
	}

	public boolean isStopped() {
		return (this.state == ThreadState.WAITING);
	}
	
	/**
	 * Stop the breeder from generating new populations
	 * Then, wait for a start with a new population
	 */
	@Override
	public void reset() {
		this.stop();
		this.breeder.reset();
		this.state = ThreadState.WAITING;
	}

	/**
	 * Suspend the generations
	 * @throws StoppedGenerationException when the generation was stopped (interrupted or done)
	 */
	@Override
	public synchronized void suspend() {
		if(this.isStopped()) {
			this.model.refreshViews(new Event(this.model, new StoppedGenerationException()));
		}
		this.state = ThreadState.SUSPEND;
		this.model.refreshViews(new Event(this.model));
	}

	/**
	 * Try to resume the generations
	 * @throws StoppedGenerationException when the generation was stopped (interrupted or done)
	 */
	@Override
	public synchronized void resume() throws StoppedGenerationException {
		if(this.isStopped()) {
			this.model.refreshViews(new Event(this, new StoppedGenerationException()));
		}
		this.state = ThreadState.STARTED;
		this.model.refreshViews(new Event(this.model));
	}

/*	public PopulationInterface run(PopulationInterface population) {
		PopulationInterface pop = population;
		do {
			pop = breeder.evolve(pop);
			
			while(this.suspend) {
				//wait from the resume
			}
			
		} while(this.hasNextCycle(pop) && !this.stopped);
		return pop;
	}*/

	public boolean isSuspend() {
		return (this.state == ThreadState.SUSPEND);
	}

	public class RuntimeThread extends Thread {
		private Monitor monitor;
		PopulationInterface sourcePopulation;
		PopulationInterface destPopulation;

		public RuntimeThread(Monitor monitor) {
			this.monitor = monitor;
		}
		
		public void setSourcePopulation(PopulationInterface sourcePopulation) {
			this.sourcePopulation = sourcePopulation;
		}
		
		@Override
		public void run() {
			PopulationInterface pop = sourcePopulation;
			do {
				pop = breeder.evolve(pop);

				while(this.monitor.isSuspend()) {
					try {
						//wait from the resume
						this.sleep(200);
					} catch (InterruptedException ex) {
						Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			} while(this.monitor.hasNextCycle(pop) && !this.monitor.isStopped());
			
			this.destPopulation = pop;

			this.monitor.stop();
		
		}
		
		public synchronized PopulationInterface getPopulationComputed() {
			return this.destPopulation;
		}
	}
}