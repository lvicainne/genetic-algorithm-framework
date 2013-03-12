package fr.isen.cir56.group3_genetic.View;

import java.util.EventObject;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Event extends EventObject {
	Exception exception;
	
	public Event(Object source) {
		this(source, null);
	}
	
	/**
	 * Construct an event with an exception in order to process errors easily
	 * @param source
	 * @param exception
	 */
	public Event(Object source, Exception exception) {
		super(source);
		this.exception = exception;
	}
	
	public boolean hasException() {
		return (this.exception != null);
	}
	
	public Exception getException() {
		return this.exception;
	}
}
