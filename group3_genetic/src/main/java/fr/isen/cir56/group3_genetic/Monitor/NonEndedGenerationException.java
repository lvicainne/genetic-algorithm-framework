package fr.isen.cir56.group3_genetic.Monitor;

/**
 * Sent when someone want to access to a functionality ONLY available 
 * when the generation of the population is ended.
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class NonEndedGenerationException extends Exception {

	public NonEndedGenerationException() {
		super("The generation of the populations is not ended !");
	}
	

}
