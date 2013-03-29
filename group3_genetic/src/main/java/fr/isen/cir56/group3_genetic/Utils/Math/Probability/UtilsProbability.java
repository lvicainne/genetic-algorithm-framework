/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Utils.Math.Probability;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class UtilsProbability {
	
	public static void checkProbabilityValue(double p) throws InvalidProbabilityValueException {
		if(p > 100 || p < 0) {
			throw new InvalidProbabilityValueException(p);
		}
	}
}
