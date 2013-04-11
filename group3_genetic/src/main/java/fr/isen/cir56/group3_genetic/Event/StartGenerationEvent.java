package fr.isen.cir56.group3_genetic.Event;

import fr.isen.cir56.group3_genetic.Event.Interfaces.ModeChangedEvent;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class StartGenerationEvent extends Event<GeneticModel> implements ModeChangedEvent {

	public StartGenerationEvent(GeneticModel sender) {
		super(sender);
	}
}
