package fr.isen.cir56.group3_genetic.Event;

/**
 * Tag name for the subclasses of Event<T>
 * Indeed, permits tag the subclasses.
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
interface EventInterface<SourceType extends Object> {
	public SourceType getSource();
}
