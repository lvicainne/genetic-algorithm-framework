/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Wizard.Annotations;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public @interface Parameter {
	/*
	 * Names of the parameters
	 */
    public String[] name();
	
	/*
	 * Default values of the parameters
	 */
	public String[] defaultValue();
}
