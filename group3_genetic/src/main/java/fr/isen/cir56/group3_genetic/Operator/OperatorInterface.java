package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Math.Probability.InvalidProbabilityValueException;


public interface OperatorInterface {

	void evaluate(PopulationInterface population);
	void setProbability(float probability) throws InvalidProbabilityValueException;
	float getProbability();
	
}