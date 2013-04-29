package fr.isen.cir56.group3_genetic.Implementations.curveApproximation;

import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Geometry.DoublePoint;

/**
 *
 * @author Wasp
 */
public class CurveAppValue implements GeneInterface<DoublePoint> {

	private DoublePoint point;


	public CurveAppValue(double x, double y) {
		this.point = new DoublePoint(x,y);
	}
	
	@Override
	public void setData(DoublePoint data) {
		this.point.x = ((DoublePoint)data).x;
		this.point.y = ((DoublePoint)data).y;
	}

	@Override
	public DoublePoint getData() {
		return point;
	}
	
}
