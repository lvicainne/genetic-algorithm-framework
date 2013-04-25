package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.ConstraintParameter;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.DefaultConstructor;

/**
 *
 * @author Wasp
 */
public class Min1DConfiguration extends Configuration {
	
	private final String algebricExpression;
	private int min, max;

	@ConstraintParameter(name={"Expression","Min","Max"},defaultValue={"x^2","-1","+1"})
	@DefaultConstructor
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
