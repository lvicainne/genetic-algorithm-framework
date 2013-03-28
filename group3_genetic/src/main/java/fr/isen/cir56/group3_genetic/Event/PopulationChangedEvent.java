/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Event;

import fr.isen.cir56.group3_genetic.Model.GeneticModel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class PopulationChangedEvent extends Event<GeneticModel> {

	public PopulationChangedEvent(GeneticModel sender) {
		super(sender);
	}
}
