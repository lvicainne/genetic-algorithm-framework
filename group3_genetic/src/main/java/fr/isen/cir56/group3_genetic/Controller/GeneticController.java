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

	@Override
	public void stop() {
		this.getGeneticModel().getMonitor().stop();
	}

	public void start() {
		this.getGeneticModel().getMonitor().start(this.getGeneticModel().getInitialPopulation());
	}

	@Override
	public void reset() {
		this.getGeneticModel().getMonitor().reset();
		this.getGeneticModel().resetInitialPopulation();
	}

	@Override
	public void suspend() {
		this.getGeneticModel().getMonitor().suspend();
	}

	@Override
	public void resume() {
		this.getGeneticModel().getMonitor().resume();
	}

}