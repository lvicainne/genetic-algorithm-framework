package fr.isen.cir56.group3_genetic.Timer;

import fr.isen.cir56.group3_genetic.Event.Event;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TimeChangedEvent extends Event<TimerModel> {
	
	public TimeChangedEvent(TimerModel sender) {
		super(sender);
	}
}
