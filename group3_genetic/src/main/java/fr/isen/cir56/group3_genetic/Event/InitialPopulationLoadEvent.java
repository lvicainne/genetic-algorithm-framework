package fr.isen.cir56.group3_genetic.Event;

import fr.isen.cir56.group3_genetic.Event.Interfaces.PopulationChangedEvent;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;

/**
 * Event lauched when the interface is loaded for the 1st time
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class InitialPopulationLoadEvent extends Event<GeneticModel> implements PopulationChangedEvent {
	
	public InitialPopulationLoadEvent(GeneticModel sender) {
		super(sender);
	}
}
