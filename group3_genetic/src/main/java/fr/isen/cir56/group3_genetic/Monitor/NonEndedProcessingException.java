/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Monitor;

/**
 * Sent when someone want to access to a functionality ONLY available 
 * when the generation of the population is ended.
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class NonEndedProcessingException extends Exception {

	public NonEndedProcessingException() {
		super("The generation of the populations is not ended !");
	}
	

}
