package fr.isen.cir56.group3_genetic.Wizard.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Define a view associated to factory where this annotation is applied on
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface AssociatedView {
	public Class value();
}
