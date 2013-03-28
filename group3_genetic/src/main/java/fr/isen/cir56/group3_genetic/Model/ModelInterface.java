package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.View.ViewInterface;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface ModelInterface {
	
	public void addView(ViewInterface view);
	public void removeView(ViewInterface view);
	public void refreshViews(Event event);
	
	
}