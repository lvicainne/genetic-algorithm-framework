package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;


public interface OperatorInterface {

	void evaluate(PopulationInterface population);
	
	/**
	 * 
	 * @param probability between 0.00 and 100.0
	 * @throws InvalidProbabilityValueException 
	 */
	void setProbability(double probability) throws InvalidProbabilityValueException;
	double getProbability();
	
}