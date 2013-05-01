package fr.isen.cir56.group3_genetic.Implementations.curveApproximation;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;

/**
 *
 * @author Wasp
 */
public class CurveAppConfiguration implements ConfigurationInterface {
	
	private String algebricExpression;
	private int xMin, xMax, yMin, yMax; // correspondent au rectangle où l'on veut générer notre population

	public CurveAppConfiguration(String algebricExpression, int xMin, int xMax, int yMin, int yMax) {
		this.algebricExpression = algebricExpression;
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}
	
	public String getAlgebricExpression() {
		return this.algebricExpression;
	}

	public int getxMin() {
		return xMin;
	}

	public int getxMax() {
		return xMax;
	}

	public int getyMin() {
		return yMin;
	}

	public int getyMax() {
		return yMax;
	}

	
}
