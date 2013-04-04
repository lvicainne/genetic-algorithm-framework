/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;

/**
 *
 * @author Wasp
 */
public class Min1DValue implements GeneInterface {

	private double x;


	public Min1DValue(double x) {
		this.x = x;
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
