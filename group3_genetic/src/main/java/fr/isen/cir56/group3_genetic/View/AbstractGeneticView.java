package fr.isen.cir56.group3_genetic.View;

import fr.isen.cir56.group3_genetic.Controller.ControllerInterface;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public abstract class AbstractGeneticView implements ViewInterface {
	protected ControllerInterface controller;
	
	@Override
	public void setController(ControllerInterface controller) {
		this.controller = controller;
	}

	@Override
	public ControllerInterface getController() {
		return this.controller;
	}

	public GeneticController getGeneticController() {
		return (GeneticController) this.controller;
	}
	
}
