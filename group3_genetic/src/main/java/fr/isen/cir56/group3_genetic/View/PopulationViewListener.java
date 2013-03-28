/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View;

import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.EventListener;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface PopulationViewListener extends EventListener{
	public void populationChanged(PopulationInterface population);
}
