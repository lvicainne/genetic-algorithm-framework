/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.AbstractFitnessFunction;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Implementations.tsp.TspChromosomeFactory;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Min1DFitnessFunction extends AbstractFitnessFunction {

	private Min1DChromosomeFactory factory;
	
	public Min1DFitnessFunction(Min1DChromosomeFactory factory) {
		this.factory = factory;
	}
	
	@Override
	protected double evaluate(ChromosomeInterface chromosome) {
		List<GeneInterface> genes = chromosome.getGenes();
		
		return (double)genes.get(0).getData();
	}
	
}
