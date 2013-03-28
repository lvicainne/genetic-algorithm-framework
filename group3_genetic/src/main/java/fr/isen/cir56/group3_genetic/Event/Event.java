package fr.isen.cir56.group3_genetic.Event;

import java.util.EventObject;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Event<SenderType extends Object> extends EventObject {
	Exception exception;
	
	public Event(SenderType source) {
		this(source, null);
	}
	
	/**
	 * Construct an event with an exception in order to process errors easily
	 * @param source
	 * @param exception
	 */
	public Event(SenderType source, Exception exception) {
		super(source);
		this.exception = exception;
	}
	
	public boolean hasException() {
		return (this.exception != null);
	}
	
	public Exception getException() {
		return this.exception;
	}

	public SenderType getSender() {
		return (SenderType) this.source;
	}
	
	
}
