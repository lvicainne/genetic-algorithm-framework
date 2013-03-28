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
public class ResetPopulationEvent extends Event<GeneticModel> {

	public ResetPopulationEvent(GeneticModel sender) {
		super(sender);
	}
}
