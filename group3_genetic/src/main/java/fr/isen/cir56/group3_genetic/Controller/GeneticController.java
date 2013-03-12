package fr.isen.cir56.group3_genetic.Controller;

import fr.isen.cir56.group3_genetic.GeneticCommandsInterface;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;

public class GeneticController extends AbstractController implements GeneticCommandsInterface {


	public GeneticController(GeneticModel model) {
		super(model);
	}
	
	public GeneticModel getGeneticModel() {
		return (GeneticModel) this.getModel();
	}

	public void stop() {
		this.getGeneticModel().getMonitor().stop();
	}

	public void start() {
		this.getGeneticModel().getMonitor().start(this.getGeneticModel().getInitialPopulation());
	}

	public void reset() {
		this.getGeneticModel().getMonitor().reset();
		this.getGeneticModel().resetInitialPopulation();
	}

	public void suspend() {
		this.getGeneticModel().getMonitor().suspend();
	}

	public void resume() {
		this.getGeneticModel().getMonitor().resume();
	}

}