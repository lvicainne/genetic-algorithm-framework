package fr.isen.cir56.group3_genetic.Breeder;

import fr.isen.cir56.group3_genetic.Operator.OperatorInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import java.util.List;

/**
 * A breeder evolves a population by performing genetic operations like
 * mutations, crossover, reproduction, etc.
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface BreederInterface {
	
	PopulationInterface evolve(PopulationInterface population, List<OperatorInterface> operators, List<SelectorInterface> selectors);
	
	int getNumberGenerations();
	double getTimeElapse();
	
	public abstract List<PopulationInterface> getPopulationsHistory();
	public abstract PopulationInterface getLastPopulation();
	
}
