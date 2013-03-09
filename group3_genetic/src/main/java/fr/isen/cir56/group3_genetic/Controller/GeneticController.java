package fr.isen.cir56.group3_genetic.Controller;

import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.Operator.InvalidOperatorException;

public class GeneticController implements ControllerInterface, GeneticControllerInterface {
	private GeneticModel model;
	
	private long lastStartTime = -1;
	private long time = 0;
	
	public GeneticController(GeneticModel model) {
		this.model = model;
	}
	
	public void stop() {
		long stopTime = System.currentTimeMillis();
		time += stopTime - this.lastStartTime;
				
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void start() {
		this.lastStartTime = System.currentTimeMillis();

		
//		throw new UnsupportedOperationException("Not supported yet.");
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