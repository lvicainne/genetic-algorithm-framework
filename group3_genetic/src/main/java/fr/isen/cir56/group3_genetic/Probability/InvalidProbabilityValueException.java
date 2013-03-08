package fr.isen.cir56.group3_genetic.Probability;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class InvalidProbabilityValueException extends Exception {
	public final float p;

	public InvalidProbabilityValueException(float p) {
		this.p = p;
	}
	
}
