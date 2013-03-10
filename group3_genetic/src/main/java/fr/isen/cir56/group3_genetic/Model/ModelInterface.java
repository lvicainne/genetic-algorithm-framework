package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.View.Event;
import fr.isen.cir56.group3_genetic.View.ViewInterface;

public interface ModelInterface {
	
	public void addView(ViewInterface view);
	public void removeView(ViewInterface view);
	public void refreshViews(Event event);
	
	
}