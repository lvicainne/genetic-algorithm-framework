package fr.isen.cir56.group3_genetic.Wizard.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface ConstraintParameter {
	/*
	 * Names of the parameters
	 */
    public String[] name();
	
	/*
	 * Default values of the parameters
	 */
	public String[] defaultValue();
}
