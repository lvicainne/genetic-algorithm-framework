package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Geometry.DoublePoint;

/**
 *
 * @author Wasp
 */
public class Min1DValue implements GeneInterface<DoublePoint> {

	private DoublePoint point;

	public Min1DValue(double x, double y) {
		this.point = new DoublePoint(x, y);
	}

	@Override
	public void setData(DoublePoint data) {
		this.point.x = ((DoublePoint) data).x;
		this.point.y = ((DoublePoint) data).y;
	}

	@Override
	public DoublePoint getData() {
		return point;
	}
}
