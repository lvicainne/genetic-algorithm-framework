package fr.isen.cir56.group3_genetic.View;

import fr.isen.cir56.group3_genetic.Controller.ControllerInterface;

public interface ViewInterface {

	public void setController(ControllerInterface controller);
	public ControllerInterface getController();
	
	public void refresh(Event event);
}