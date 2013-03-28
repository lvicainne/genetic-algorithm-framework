/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Event;

import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.View.Event;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class PopulationChangedEvent extends Event {
	
	public PopulationChangedEvent(GeneticModel sender) {
		super(sender);
	}
		
	public GeneticModel getGeneticModel() {
		return (GeneticModel) this.getSource();
	}
	
}
