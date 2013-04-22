package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.Controller.ControllerInterface;
import fr.isen.cir56.group3_genetic.Event.Event;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface ListenerInterface<ControllerType extends ControllerInterface> {
	public void refresh(Event event);
}
