/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import java.util.EventListener;

/**
 * Decribe the needed interfaces to update the views.
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface ChromosomeViewListener<GeneType extends GeneInterface> extends EventListener {
	public void chromosomeChanged(ChromosomeInterface<GeneType> chromosome);
	
	//In case of reset, the view is reseted.
	public void resetView();
}
