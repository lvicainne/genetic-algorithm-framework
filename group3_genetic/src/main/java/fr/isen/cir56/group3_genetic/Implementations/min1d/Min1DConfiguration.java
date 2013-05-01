package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;

/**
 *
 * @author Wasp
 */
public class Min1DConfiguration implements ConfigurationInterface {
	
	private final String algebricExpression;
	private final int min, max;
	
	public Min1DConfiguration(String algebricExpression, int min, int max) {
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
