/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Monitor;

import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.View.Event;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class EndGenerationEvent extends Event {
	private final PopulationInterface pop;

	public EndGenerationEvent(GeneticModel sender, PopulationInterface pop) {
		super(sender);
		this.pop = pop;
	}

	public PopulationInterface getPop() {
		return pop;
	}
	
	public GeneticModel getGeneticModel() {
		return (GeneticModel) this.getSource();
	}
}
