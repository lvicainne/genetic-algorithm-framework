package fr.isen.cir56.group3_genetic.Analyzer;

import fr.isen.cir56.group3_genetic.Breeder.BreederInterface;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface AnalyzerInterface {

	/**
	 * The breeder which contains all data computed
	 * @param breeder 
	 */
	void setBreeder(BreederInterface breeder);
	BreederInterface getBreeder();
	
	double getCorrelationCoefficient() throws UnexistingBreederException;
	
	/**
	 * Get the number of generations
	 * @return 
	 */
	double getNumberGenerations() throws UnexistingBreederException;
	
	/**
	 * Get the percentage of involving for the generation compared to the previous one
	 * @param index of the generation (0 for the first one, n for the last one)
	 * @return percentage
	 */
	double getInvolving(int index) throws UnexistingBreederException;
	
	/**
	 * Launch the analyse for getting datas
	 * @throws UnexistingBreederException if the breeder was not set
	 */
	void analyse() throws UnexistingBreederException;
	
}
