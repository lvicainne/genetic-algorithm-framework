package fr.isen.cir56.group3_genetic.Timer;

import fr.isen.cir56.group3_genetic.Model.Model;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TimerModel extends Model {
	private long timeElapsed;
	private long timeBeginTour;
	
	
	public double getTimeElapsed() {
		return this.timeElapsed;
	}
	
	public void start() {
		this.timeBeginTour = System.currentTimeMillis();
	}
	
	public void stop() {
		if(timeBeginTour > 0) {
			long timeTour = System.currentTimeMillis() - this.timeBeginTour;
			this.timeElapsed += timeTour;
			this.refreshViews(new TimeChangedEvent(this));
		}
		timeBeginTour = 0;
	}
	
	public void reset() {
		this.timeBeginTour = 0;
		this.timeElapsed = 0;
	}
}
