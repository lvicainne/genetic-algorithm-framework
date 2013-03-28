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

	@Override
	public void start() {
		this.getModel().getMonitor().setSourcePopulation(this.getModel().getInitialPopulation());
		this.getModel().getMonitor().start();
	}
	
	@Override
	public void stepByStep() {
		this.getModel().getMonitor().stepByStep();
	}

	@Override
	public void reset() {
		this.getModel().getMonitor().reset();
		
		//Build a new population with new data
		this.getModel().resetInitialPopulation(); 
		
		//Update the new data to the monitor
		this.getModel().getMonitor().setSourcePopulation(this.getModel().getInitialPopulation());
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