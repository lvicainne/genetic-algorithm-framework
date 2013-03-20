package fr.isen.cir56.group3_genetic.Analyzer;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.security.InvalidParameterException;
import java.util.List;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class Analyzer implements AnalyzerInterface {
	private BreederInterface breeder;
	

	@Override
	public void setBreeder(BreederInterface breeder) {
		this.breeder = breeder;
	}

	@Override
	public BreederInterface getBreeder() {
		return this.breeder;
	}

	@Override
	public double getCorrelationCoefficient() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public double getNumberGenerations() throws UnexistingBreederException {
		this.checkValidityBreeder();
		return this.breeder.getNumberGenerations();
	}

	@Override
	public double getInvolving(int index) throws UnexistingBreederException {
		this.checkValidityBreeder();
		
		List<PopulationInterface> history = this.breeder.getPopulationsHistory();
		if(index < 1 || index > history.size()) {
			throw new InvalidParameterException("The index of the population-involving is not correct. Must be between 1 and size()-1");
		}
		
		double popEnd = history.get(index).getBetterChromosome().getFitnessValue();
		double popStart = history.get(index - 1).getBetterChromosome().getFitnessValue();
		
		return popEnd-popStart;
	}
	
	public double getPercentageInvolving(int index) {
		double involving = this.getPercentageInvolving(index);
		
		List<PopulationInterface> history = this.breeder.getPopulationsHistory();
		double endPopulation = history.get(index).getBetterChromosome().getFitnessValue();
		
		return involving/endPopulation;
	}

	@Override
	public void analyse() throws UnexistingBreederException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	protected void checkValidityBreeder() throws UnexistingBreederException {
		if(!(this.breeder != null && (this.breeder instanceof BreederInterface))) {
			throw new UnexistingBreederException();
		}
	}
}
