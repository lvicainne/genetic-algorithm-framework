package fr.isen.cir56.group3_genetic.Wizard;

import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
class ConfigurationChangedEvent extends Event<GeneticModel> {

	public ConfigurationChangedEvent(GeneticModel model) {
		super(model);
	}
	
}
