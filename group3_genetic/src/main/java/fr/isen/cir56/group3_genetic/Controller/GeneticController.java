package fr.isen.cir56.group3_genetic.Controller;

import fr.isen.cir56.group3_genetic.GeneticCommandsInterface;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.Monitor.AbstractMonitor;
import fr.isen.cir56.group3_genetic.Monitor.Monitor;

public class GeneticController extends AbstractController implements GeneticCommandsInterface {
	private AbstractMonitor monitor;

	public GeneticController(GeneticModel model) {
		super(model);
		this.monitor = new Monitor(model.getConfiguration());
	}
	
	public GeneticModel getGeneticModel() {
		return (GeneticModel) this.getModel();
	}

	public void stop() {
		monitor.stop();
	}

	public void start() {
		monitor.start(this.getGeneticModel().getPopulation());
	}

	public void reset() {
		this.monitor.reset();
		
		this.getGeneticModel().resetPopulation();
		this.getGeneticModel().getPopulation();
		
		this.monitor.start(null);
	}

	public void suspend() {
		monitor.suspend();
	}

	public void resume() {
		monitor.resume();
	}

}