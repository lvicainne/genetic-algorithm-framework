package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.UtilsProbability;
import java.util.Random;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public abstract class AbstractOperator implements OperatorInterface {
	private float probability;
	protected static Random randomGenerator = new Random();
	
	public AbstractOperator(float probability) throws InvalidProbabilityValueException {
		this.setProbability(probability);
	}

	@Override
	public final void setProbability(float probability) throws InvalidProbabilityValueException {
		UtilsProbability.checkProbabilityValue(probability);
		this.probability = probability;
	}

	@Override
	public final float getProbability() {
		return this.probability;
	}

	/**
	 * Call the evaluate methods with probability p
	 * @param population 
	 */
	@Override
	public void evaluate(PopulationInterface population) {
		double p = AbstractOperator.randomGenerator.nextDouble();
		if(p <= this.getProbability()) { 
			this.operate(population);
		}
	}
	
	protected abstract void operate(PopulationInterface population);
	
}