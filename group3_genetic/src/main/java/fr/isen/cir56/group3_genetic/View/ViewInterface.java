package fr.isen.cir56.group3_genetic.View;

import fr.isen.cir56.group3_genetic.Controller.ControllerInterface;
import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.Model.ListenerInterface;

public interface ViewInterface<ControllerType extends ControllerInterface> extends ListenerInterface {

	public void refresh(Event event);
}