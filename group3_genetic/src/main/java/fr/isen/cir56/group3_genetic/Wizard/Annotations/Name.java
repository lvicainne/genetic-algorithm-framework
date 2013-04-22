package fr.isen.cir56.group3_genetic.Wizard.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Define a name for the class, method or ...
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
	String value();
}
