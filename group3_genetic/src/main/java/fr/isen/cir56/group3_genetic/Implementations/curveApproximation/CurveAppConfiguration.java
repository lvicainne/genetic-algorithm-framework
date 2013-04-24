/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.curveApproximation;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;

/**
 *
 * @author Wasp
 */
public class CurveAppConfiguration extends Configuration{
	
	private final String algebricExpression;
	private int min, max;

	public CurveAppConfiguration(String algebricExpression, int min, int max) {
		this.algebricExpression = algebricExpression;
		this.min = min;
		this.max = max;
	}

	public String getAlgebricExpression() {
		return this.algebricExpression;
	}

	public int getMin() {
		return this.min;
	}

	public int getMax() {
		return this.max;
	}

	
}
