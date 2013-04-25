package fr.isen.cir56.group3_genetic.Constraint;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.ConstraintParameter;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.Name;

@Name("Number of generations Constraint")
public class NumberGenerationConstraint implements ConstraintInterface {

	private final int maximumGenerations;

	@ConstraintParameter(name = "Maximum number of generation", defaultValue = "100",maxValue="2000")
	public NumberGenerationConstraint(int maximumGenerations) {
		this.maximumGenerations = maximumGenerations;
	}

	public int getMaximumGenerations() {
		return maximumGenerations;
	}

	/*
	 *{@inheritdoc}
	 */
	@Override
	public boolean isReached(BreederInterface breeder, PopulationInterface population) {
		return (breeder.getNumberGenerations() >= this.maximumGenerations);
	}
}