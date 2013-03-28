package fr.isen.cir56.group3_genetic.View;

import fr.isen.cir56.group3_genetic.Controller.ControllerInterface;
import fr.isen.cir56.group3_genetic.Event.Event;

public interface ViewInterface<ControllerType extends ControllerInterface> {

	public void refresh(Event event);
}