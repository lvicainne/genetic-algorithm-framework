/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Geometry.DoublePoint;

/**
 *
 * @author Wasp
 */
public class Min1DValue implements GeneInterface {

	private DoublePoint point;


	public Min1DValue(double x, double y) {
		this.point = new DoublePoint(x,y);
	}
	
	@Override
	public void setData(Object data) {
		if(data instanceof DoublePoint) {
			this.point.x = ((DoublePoint)data).x;
			this.point.y = ((DoublePoint)data).y;
		}
	}

	@Override
	public Object getData() {
		return point;
	}
	
}
