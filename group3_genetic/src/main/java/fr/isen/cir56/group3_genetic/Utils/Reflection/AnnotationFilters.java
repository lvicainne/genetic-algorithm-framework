/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Utils.Reflection;

import fr.isen.cir56.group3_genetic.Wizard.Annotations.ConstraintParameter;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.DefaultConstructor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class AnnotationFilters {
	
	/*
	 * Return the default constructor, defined bu the annotation DefaultCOnstructor.
	 * If annotation DefaultConstructor is not defined, use the real default constructor,
	 * i.e. without any parameter or null if it does not exists nor.
	 */
	public static Constructor getDefaultConstructor(Class class1) {
		Constructor[] constructors = class1.getConstructors();
		
		for (Constructor constructor : constructors) {
			Annotation annotation = constructor.getAnnotation(DefaultConstructor.class);
			if(annotation != null) {
				return constructor;
			}
		}
		try {
			return class1.getConstructor();
		} catch (NoSuchMethodException ex) {
		} catch (SecurityException ex) {
		}
		
		//If there is no default constructor, return null;
		return null;
	}
	
	/**
	 * Get the first parameter annotation from a class
	 * This ConstraintParameter is searched in any constructor.
	 * Return null if there is no ConstraintParameter in the default constructor
	 * @param class1
	 * @return 
	 */
	public static ConstraintParameter getConstraintParameterAnnotation(Class class1) {
		Constructor[] constructors = class1.getConstructors();
		
		for (Constructor constructor : constructors) {
			Annotation annotation = constructor.getAnnotation(ConstraintParameter.class);
			if(annotation != null) {
				ConstraintParameter parameterAnnotation = (ConstraintParameter) annotation;
				return parameterAnnotation;
			}
		}
		
		return null;
	}
}
