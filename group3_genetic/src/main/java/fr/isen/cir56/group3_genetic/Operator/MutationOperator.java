package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Population;

public abstract class MutationOperator implements EvolutionOperator {
	public abstract void mutation(Population population);
}