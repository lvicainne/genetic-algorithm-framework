package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;

/**
 *
 * @author Wasp
 */
public class Min1DConfiguration extends Configuration {
	
	private String algebricExpression;
	private int min, max;

	public Min1DConfiguration(ConfigurationInterface configuration) {
		
	}
	
	public Min1DConfiguration(String algebricExpression, int min, int max) {
		this.algebricExpression = algebricExpression;
		this.min = min;
		this.max = max;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setAlgebricExpression(String algebricExpression) {
		this.algebricExpression = algebricExpression;
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
