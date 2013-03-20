/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Configuration;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class InvalidConfigurationException extends Exception {
	private final String message;

	InvalidConfigurationException(String string) {
		this.message = string;
	}
	
}
