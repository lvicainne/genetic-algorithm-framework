package fr.isen.cir56.group3_genetic.Controller;

import fr.isen.cir56.group3_genetic.GeneticCommandsInterface;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class GeneticController extends AbstractController<GeneticModel> implements GeneticCommandsInterface {

	public GeneticController(GeneticModel model) {
		super(model);
	}

	@Override
	public void stop() {
		getModel().getMonitor().stop();
	}

	public void start() {
		this.getModel().getMonitor().start(this.getModel().getInitialPopulation());
	}

	@Override
	public void reset() {
		this.getModel().getMonitor().reset();
		this.getModel().resetInitialPopulation();
	}

	@Override
	public void suspend() {
		this.getModel().getMonitor().suspend();
	}

	@Override
	public void resume() {
		this.getModel().getMonitor().resume();
	}

}