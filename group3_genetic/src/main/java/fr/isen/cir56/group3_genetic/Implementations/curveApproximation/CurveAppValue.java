/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.curveApproximation;

import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Geometry.DoublePoint;

/**
 *
 * @author Wasp
 */
public class CurveAppValue implements GeneInterface{

	private DoublePoint point;


	public CurveAppValue(double x, double y) {
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
