package fr.isen.cir56.group3_genetic.Event;

import fr.isen.cir56.group3_genetic.Model.GeneticModel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ResetPopulationEvent extends Event<GeneticModel> implements PopulationChangedEvent {

	public ResetPopulationEvent(GeneticModel sender) {
		super(sender);
	}
}
