package fr.isen.cir56.group3_genetic.Event;

import fr.isen.cir56.group3_genetic.Event.Interfaces.EventInterface;
import java.util.EventObject;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Event<SourceType> extends EventObject implements EventInterface<SourceType> {
	Exception exception;
	
	public Event(SourceType source) {
		this(source, null);
	}
	
	/**
	 * Construct an event with an exception in order to process errors easily
	 * @param source
	 * @param exception
	 */
	public Event(SourceType source, Exception exception) {
		super(source);
		this.exception = exception;
	}
	
	public boolean hasException() {
		return (this.exception != null);
	}
	
	public Exception getException() {
		return this.exception;
	}

	@Override
	public SourceType getSource() {
		return (SourceType) this.source;
	}
	
	
}
