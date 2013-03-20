package fr.isen.cir56.group3_genetic.Genotype;

/**
 * Sent when two chromosomes have to be compare (for crossover for example) 
 * and they don't match (different size)
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class InvalidSizeChromosomeCrossoverException extends RuntimeException {

	public InvalidSizeChromosomeCrossoverException() {
	}
	
}
