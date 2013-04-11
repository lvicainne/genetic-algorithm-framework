package fr.isen.cir56.group3_genetic.Event;

import fr.isen.cir56.group3_genetic.Event.Interfaces.ModeChangedEvent;
import fr.isen.cir56.group3_genetic.Event.Interfaces.PopulationChangedEvent;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class StepGenerationEvent extends Event<GeneticModel> implements PopulationChangedEvent, ModeChangedEvent {

	public StepGenerationEvent(GeneticModel sender) {
		super(sender);
	}
	
}
