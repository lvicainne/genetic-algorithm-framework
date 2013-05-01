package fr.isen.cir56.group3_genetic.Monitor;

import fr.isen.cir56.group3_genetic.Analyzer.Analyzer;
import fr.isen.cir56.group3_genetic.Breeder.Breeder;
import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.Configuration.GeneticConfigurationInterface;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Event.AlreadyEndedEvent;
import fr.isen.cir56.group3_genetic.Event.EndGenerationEvent;
import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.Event.ResetPopulationEvent;
import fr.isen.cir56.group3_genetic.Event.ResumeGenerationEvent;
import fr.isen.cir56.group3_genetic.Event.StartGenerationEvent;
import fr.isen.cir56.group3_genetic.Event.StepGenerationEvent;
import fr.isen.cir56.group3_genetic.Event.SuspendGenerationEvent;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.security.InvalidParameterException;
import java.util.List;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Monitor implements MonitorInterface {

	protected PopulationInterface sourcePopulation;
	protected PopulationInterface generatedPopulation;
	private BreederInterface breeder;
	private ThreadState state;
	private final GeneticModel model;
	private RuntimeThread thread;
	private Analyzer analyzer;
	private final GeneticConfigurationInterface configuration;

	public Monitor(GeneticConfigurationInterface configuration, GeneticModel model) {
		this.configuration = configuration;
		this.model = model;
		this.state = ThreadState.WAITING;
		this.breeder = new Breeder();
	}

	public GeneticConfigurationInterface getConfiguration() {
		return configuration;
	}

	public BreederInterface getBreeder() {
		return breeder;
	}

	public Analyzer getAnalyzer() throws NonEndedGenerationException {
		if (this.analyzer == null) {
			throw new NonEndedGenerationException();
		}

		return analyzer;
	}

	@Override
	public boolean hasNextCycle(PopulationInterface population) {
		List<ConstraintInterface> constraints = this.configuration.getConstraints();
		for (ConstraintInterface constraint : constraints) {
			if (constraint.isReached(this.breeder, population)) {
				return false;
			}
		}
		return true;
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

	/**
	 * Start the system with creating an launching a thread
	 */
	@Override
	public void start() {
		checkBeforeStart();

		this.state = ThreadState.STARTED;

		this.thread = new RuntimeThread(this);
		this.thread.start();

		this.model.refreshViews(new StartGenerationEvent(this.model));
	}

	/**
	 * Try to resume the generations in other words, it is like start BUT in a
	 * system which has already been started and suspend in the past
	 *
	 * @throws StoppedGenerationException when the generation was stopped
	 * (interrupted or done)
	 */
	@Override
	public synchronized void resume() throws StoppedGenerationException {
		if (this.isStopped()) {
			//if stopped, we can't resume. We have to reset the system
			this.model.refreshViews(new Event(this, new StoppedGenerationException()));

		} else if (this.thread == null) {
			//if there is no thread, in other words if step-to-step before, we create the thread
			this.start();

		} else {
			//if a thread already exists, we change its state to "start" to resume it
			this.state = ThreadState.STARTED;
			this.model.refreshViews(new ResumeGenerationEvent(this.model));
		}
	}

	@Override
	public void stepByStep() {

		PopulationInterface pop;

		//We chose the population to evole
		if (this.generatedPopulation != null && this.state == ThreadState.SUSPEND) {
			//ONLY if the generated exists and state is SUSPEND, we can use it
			pop = this.generatedPopulation;

		} else {
			pop = this.sourcePopulation;
		}

		checkBeforeStart();

		if (!this.hasNextCycle(pop)) {
			this.model.refreshViews(new AlreadyEndedEvent(this.model));
			return;
		}

		pop = breeder.evolve(pop, configuration.getOperators(), configuration.getSelectors());
		this.generatedPopulation = pop;

		if (this.hasNextCycle(pop)) {
			this.state = ThreadState.SUSPEND;
			this.model.refreshViews(new StepGenerationEvent(this.model));
		} else {
			this.stop();
		}

	}

	/**
	 * Stop the thread and delete it
	 */
	@Override
	public synchronized void stop() {
		this.state = ThreadState.END;

		if (this.thread != null) {
			this.thread.forceStop();
			this.thread = null;
		}

		analyzer = new Analyzer(this.model.getMonitor().getBreeder());
		this.model.refreshViews(new EndGenerationEvent(this.model, analyzer));

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
		this.breeder = new Breeder();
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
	 * If return true, it's because the system is stopped In other words, the
	 * system CAN'T be resumed once stoped, it is the end but without the
	 * wondering result
	 *
	 * @return
	 */
	public boolean isStopped() {
		return !((this.state != ThreadState.WAITING) && (this.state != ThreadState.END));
	}

	public boolean isSuspend() {
		return (this.state == ThreadState.SUSPEND);
	}

	/**
	 * Used for creating a JDialog for the configuration wizard
	 *
	 * @return true if a process has begun (in other words, if process is not
	 * ended but a generation has already been)
	 */
	public boolean isProcessing() {
		return !(this.state != ThreadState.STARTED && this.state != ThreadState.SUSPEND);
	}

	/**
	 * Return true once the system is completed, in other words once a
	 * constraint has been fullfilled
	 *
	 * @return
	 */
	public boolean isEnd() {
		return (this.state == ThreadState.END);
	}

	/**
	 * synchronized methods for the Thread
	 */
	/**
	 * Set the source population used as input for the Thread
	 *
	 * @param sourcePopulation
	 */
	public synchronized void setSourcePopulation(PopulationInterface sourcePopulation) {
		this.sourcePopulation = sourcePopulation;
	}

	/**
	 * Get the computed population used as output for the thread
	 *
	 * @return
	 */
	public synchronized PopulationInterface getPopulationComputed() {
		return this.generatedPopulation;
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
				pop = breeder.evolve(pop, configuration.getOperators(), configuration.getSelectors());

				while (this.monitor.isSuspend()) {
					try {
						//wait from the resume
						//improve the performance during the suspend mode
						sleep(500);
					} catch (InterruptedException ex) {
						// catch this exception but we do nothing because there is nothing to do
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

		/**
		 * is an alias for the stop() method in Thread nowadays deprecated In
		 * fact, this stop method is an alternative for stopping our thread
		 */
		public void forceStop() {
			this.forceStop = true;
		}
	}
}