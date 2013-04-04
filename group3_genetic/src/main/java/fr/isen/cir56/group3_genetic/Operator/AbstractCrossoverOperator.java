package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.InvalidSizeChromosomeCrossoverException;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public abstract class AbstractCrossoverOperator extends AbstractOperator {

	public AbstractCrossoverOperator(double p) throws InvalidProbabilityValueException {
		super(p);
	}

	/**
	 * Redefine the evaluate function : operate method has to be called every
	 * time (contrary to the others operators)
	 *
	 * @param population
	 */
	@Override
	public void evaluate(PopulationInterface population) {
		this.operate(population);
	}

	/**
	 * Determine which chromosomes for making a crossver between them
	 *
	 * @param population
	 */
	@Override
	protected void operate(PopulationInterface population) throws InvalidSizeChromosomeCrossoverException {
		int populationSize = population.size();
		List<ChromosomeInterface> newChromosomes = new LinkedList<>();

		List<ChromosomeInterface> chromosomes = population.getChromosomes();
		double operatorProbability = this.getProbability();


		for (ChromosomeInterface chromosomeInterface : chromosomes) {
			int p = AbstractOperator.randomGenerator.nextInt(100);
			if (p < operatorProbability) {
				ChromosomeInterface ch1 = chromosomeInterface;
				ChromosomeInterface ch2 = population.getChromosome(AbstractOperator.randomGenerator.nextInt(populationSize));

				if (ch1.size() != ch2.size()) {
					throw new InvalidSizeChromosomeCrossoverException();
				}

				ChromosomeInterface ch1new = ch1.clone();
				ChromosomeInterface ch2new = ch2.clone();

				this.crossover(ch1new, ch2new);

				newChromosomes.add(ch1new);
				newChromosomes.add(ch2new);

			}
		}

		population.addChromosomes(newChromosomes);

	}

	/**
	 * Make a crossover between the 2 chromosomes passed in parameter
	 *
	 * @param ch1
	 * @param ch2
	 */
	public abstract void crossover(ChromosomeInterface ch1, ChromosomeInterface ch2);
}