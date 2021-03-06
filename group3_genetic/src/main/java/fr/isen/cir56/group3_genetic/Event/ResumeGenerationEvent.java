package fr.isen.cir56.group3_genetic.Event;

import fr.isen.cir56.group3_genetic.Event.Interfaces.ModeChangedEvent;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ResumeGenerationEvent extends Event<GeneticModel> implements ModeChangedEvent {

	public ResumeGenerationEvent(GeneticModel sender) {
		super(sender);
	}
}
