package fr.isen.cir56.group3_genetic.Constraint;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;

public interface ConstraintInterface {

	public boolean isReached(BreederInterface breeder, PopulationInterface population);

}