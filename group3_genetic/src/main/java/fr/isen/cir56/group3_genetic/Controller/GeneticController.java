package fr.isen.cir56.group3_genetic.Controller;

import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.Operator.InvalidOperatorException;

public class GeneticController implements ControllerInterface, GeneticControllerInterface {
	private GeneticModel model;
	
	public GeneticController(GeneticModel model) {
		this.model = model;
	}
	
	public void stop() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void start() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void reset() {
		this.model.resetPopulation();
		//this.model.resetClock();
		//reset le reste
		//@TODO
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void suspend() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void resume() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	protected void run() throws InvalidOperatorException {
		boolean notStop = true;
		
		while(!this.model.isFinished() && notStop) {
			this.model.selection();
			this.model.crossover();
			this.model.mutation();
			
		}
		
		//get the solution
	}
}