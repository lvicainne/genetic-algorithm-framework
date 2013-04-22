package fr.isen.cir56.group3_genetic.Timer;

import fr.isen.cir56.group3_genetic.Controller.AbstractController;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TimerController extends AbstractController<TimerModel> {

	public TimerController(TimerModel model) {
		super(model);
	}
	
	/**
	 * Start the timer
	 */
	public void start() {
		this.getModel().start();
	}
	
	/**
	 * Sto the counter time
	 */
	public void stop() {
		this.getModel().stop();
	}
	
	/**
	 * Reset the counter time
	 */
	public void reset() {
		this.getModel().reset();
	}
	
}
