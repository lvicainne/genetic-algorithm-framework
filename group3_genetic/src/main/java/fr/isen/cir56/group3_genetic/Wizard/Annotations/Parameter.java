/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Wizard.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
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
