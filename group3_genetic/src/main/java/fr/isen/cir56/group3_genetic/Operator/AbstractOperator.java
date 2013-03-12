/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.Math.Probability.UtilsProbability;
import fr.isen.cir56.group3_genetic.Population;
import java.util.Random;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public abstract class AbstractOperator implements OperatorInterface {
	protected float probability;
	protected static Random randomGenerator = new Random();
	
	public AbstractOperator(float probability) throws InvalidProbabilityValueException {
		this.setProbability(probability);
	}

	public final void setProbability(float probability) throws InvalidProbabilityValueException {
		UtilsProbability.checkProbabilityValue(probability);
		this.probability = probability;
	}

	public final float getProbability() {
		return this.probability;
	}

	public final void evaluate(Population population) {
		if(AbstractOperator.randomGenerator.nextDouble() <= this.getProbability()) { 
			this.operate(population);
		}
	}
	
	protected abstract void operate(Population population);
	
}
