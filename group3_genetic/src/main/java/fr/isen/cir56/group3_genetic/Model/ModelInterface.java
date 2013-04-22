package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.Event.Event;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface ModelInterface {
	
	public void addListener(ListenerInterface view);
	public void removeListener(ListenerInterface view);
	public void refreshListener(Event event);
	
	
}