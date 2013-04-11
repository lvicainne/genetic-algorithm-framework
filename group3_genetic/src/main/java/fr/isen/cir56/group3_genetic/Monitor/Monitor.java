package fr.isen.cir56.group3_genetic.Monitor;

import fr.isen.cir56.group3_genetic.Analyzer.Analyzer;
import fr.isen.cir56.group3_genetic.Breeder.Breeder;
import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Event.AlreadyEndedEvent;
import fr.isen.cir56.group3_genetic.Event.EndGenerationEvent;
import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.Event.ResetPopulationEvent;
import fr.isen.cir56.group3_genetic.Event.StartGenerationEvent;
import fr.isen.cir56.group3_genetic.Event.StepGenerationEvent;
import fr.isen.cir56.group3_genetic.Event.SuspendGenerationEvent;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Monitor extends AbstractMonitor {

	protected PopulationInterface sourcePopulation;
	protected PopulationInterface generatedPopulation;
	private BreederInterface breeder;
	private ThreadState state;
	private final GeneticModel model;
	private RuntimeThread thread;
	private Analyzer analyzer;
	private final ConfigurationInterface configuration;

	public Monitor(ConfigurationInterface configuration, GeneticModel model) {
		super(configuration.getConstraints());
		this.model = model;
		this.configuration = configuration;
		this.breeder = new Breeder(configuration);

	}

	public ConfigurationInterface getConfiguration() {
		return configuration;
	}

	public BreederInterface getBreeder() {
		return breeder;
	}

	private void checkBeforeStart() {
		if (this.sourcePopulation == null) {
			throw new InvalidParameterException("The population is not initialized for the monitor");
		}

		try {
			this.getConfiguration().lockSettings();
		} catch (InvalidConfigurationException ex) {
			this.model.refreshViews(new Event(this.model, ex));
		}
	}

	@Override
	public void start() {
		checkBeforeStart();

		this.thread = new RuntimeThread(this);
		this.thread.start();
	}

	@Override
	public void stepByStep() {

		PopulationInterface pop;

		//We chose the population to evole
		if (this.generatedPopulation != null && this.state == ThreadState.SUSPEND) {
			//ONLY if the generated exists and satte is SUSPEND, we can use it
			pop = this.generatedPopulation;

		} else {
			pop = this.sourcePopulation;
		}

		checkBeforeStart();

		if (!this.hasNextCycle(pop)) {
			this.model.refreshViews(new AlreadyEndedEvent(this.model));
			return;
		}

		pop = breeder.evolve(pop);
		this.generatedPopulation = pop;

		if (this.hasNextCycle(pop)) {
			this.state = ThreadState.SUSPEND;
			this.model.refreshViews(new StepGenerationEvent(this.model));
		} else {
			this.stop();
		}

	}

	@Override
	public boolean hasNextCycle(PopulationInterface population) {
		List<ConstraintInterface> constraints = this.getConstraints();
		for (ConstraintInterface constraint : constraints) {
			if (constraint.isReached(this.breeder, population)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public synchronized void stop() {
		this.state = ThreadState.END;

		if (this.thread != null) {
			this.thread.forceStop();
			this.thread = null;
		}

		analyzer = new Analyzer(this.model.getMonitor().getBreeder());
		this.model.refreshViews(new EndGenerationEvent(this.model, analyzer));
		System.out.println("Nc");

	}

	public boolean isStopped() {
		return !((this.state != ThreadState.WAITING) && (this.state != ThreadState.END));
	}

	/**
	 * Stop the breeder from generating new populations Then, wait for a start
	 * with a new population
	 */
	@Override
	public void reset() {
		if (!this.isStopped()) {
			//We stop the monitor only if it is running
			this.stop();
			//Else, if there was no generation and reset, behavior unexpected
		}
		this.breeder = new Breeder(this.configuration);
		this.state = ThreadState.WAITING;

		this.model.refreshViews(new ResetPopulationEvent(this.model));
	}

	/**
	 * Suspend the generations
	 *
	 * @throws StoppedGenerationException when the generation was stopped
	 * (interrupted or done)
	 */
	@Override
	public synchronized void suspend() {
		if (this.isStopped()) {
			this.model.refreshViews(new Event(this.model, new StoppedGenerationException()));
		}
		this.state = ThreadState.SUSPEND;
		this.model.refreshViews(new SuspendGenerationEvent(this.model));
	}

	/**
	 * Try to resume the generations
	 *
	 * @throws StoppedGenerationException when the generation was stopped
	 * (interrupted or done)
	 */
	@Override
	public synchronized void resume() throws StoppedGenerationException {
		if (this.isStopped()) {
			this.model.refreshViews(new Event(this, new StoppedGenerationException()));
		}

		this.state = ThreadState.STARTED;
		if (this.thread == null) {
			this.thread = new RuntimeThread(this);
			this.thread.start();
		}


		this.model.refreshViews(new StartGenerationEvent(this.model));
	}

	public boolean isSuspend() {
		return (this.state == ThreadState.SUSPEND);
	}

	public boolean isEnd() {
		return (this.state == ThreadState.END);
	}

	private class RuntimeThread extends Thread {

		private Monitor monitor;
		private boolean forceStop = false;

		public RuntimeThread(Monitor monitor) {
			this.monitor = monitor;
		}

		@Override
		public void run() {
			state = ThreadState.STARTED;
			model.refreshViews(new StartGenerationEvent(model));

			PopulationInterface pop = sourcePopulation;
			do {
				pop = breeder.evolve(pop);

				while (this.monitor.isSuspend()) {
					try {
						//wait from the resume
						sleep(200);
					} catch (InterruptedException ex) {
						Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
					}

					if (forceStop) {
						return;
					}

				}

				if (forceStop) {
					return;
				}

			} while (this.monitor.hasNextCycle(pop) && !this.monitor.isStopped());

			generatedPopulation = pop;
			this.monitor.stop();
		}

		public void forceStop() {
			this.forceStop = true;
		}
	}

	public synchronized void setSourcePopulation(PopulationInterface sourcePopulation) {
		this.sourcePopulation = sourcePopulation;
	}

	public synchronized PopulationInterface getPopulationComputed() {
		return this.generatedPopulation;
	}

	public Analyzer getAnalyzer() throws NonEndedGenerationException {
		if (this.analyzer == null) {
			throw new NonEndedGenerationException();
		}

		return analyzer;
	}
}