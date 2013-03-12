package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.PopulationInterface;


public interface OperatorInterface {

	void evaluate(PopulationInterface population);
	void setProbability(float probability) throws InvalidProbabilityValueException;
	float getProbability();
	
}