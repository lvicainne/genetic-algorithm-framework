package fr.isen.cir56.group3_genetic.Event;

import fr.isen.cir56.group3_genetic.Event.Interfaces.ModeChangedEvent;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class SuspendGenerationEvent extends Event<GeneticModel> implements ModeChangedEvent {

	public SuspendGenerationEvent(GeneticModel model) {
		super(model);
	}
}
