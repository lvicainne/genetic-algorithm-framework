package fr.isen.cir56.group3_genetic.Controller;

import fr.isen.cir56.group3_genetic.Breeder.Breeder;
import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.Monitor.AbstractMonitor;
import fr.isen.cir56.group3_genetic.Monitor.Monitor;

public class GeneticController extends AbstractController implements GeneticControllerInterface {
	private AbstractMonitor monitor;
	
	private long lastStartTime = -1;
	private long time = 0;

	public GeneticController(GeneticModel model) {
		super(model);
		monitor = new Monitor(model.getConfiguration());
	}
	
	public GeneticModel getGeneticModel() {
		return (GeneticModel) this.getModel();
	}

	public void stop() {
		long stopTime = System.currentTimeMillis();
		time += stopTime - this.lastStartTime;

		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void start() {
		this.lastStartTime = System.currentTimeMillis();
		monitor.start(this.getGeneticModel().getPopulation(), this.getGeneticModel().getConfiguration());

//		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void reset() {
		this.getGeneticModel().resetPopulation();
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

/*	public void run() {
		Thread thisThread = Thread.currentThread();
		while (blinker == thisThread) {
			try {
				Thread.sleep(interval);

				synchronized (this) {
					while (threadSuspended && blinker == thisThread) {
						wait();
					}
				}
			} catch (InterruptedException e) {
			}
			repaint();
		}
	}

	public synchronized void stop() {
		blinker = null;
		notify();
	}*/
	
}