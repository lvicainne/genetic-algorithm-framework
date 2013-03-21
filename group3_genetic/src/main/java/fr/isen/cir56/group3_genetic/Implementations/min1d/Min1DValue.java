/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Genotype.AbstractGene;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Genotype.InvalidSizeChromosomeCrossoverException;
import fr.isen.cir56.group3_genetic.Operator.AbstractOperator;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Wasp
 */
public class Min1DValue extends AbstractGene implements GeneInterface {

	private final double x;
	private final double y;

	public Min1DValue(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void setData(Object data) {
		this.x = (double)data;
	}

	@Override
	public Object getData() {
		return this.x;
	}
	
}
