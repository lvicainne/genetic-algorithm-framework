/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View.Configurator;

import fr.isen.cir56.group3_genetic.Utils.Reflection.ClassFilter;
import fr.isen.cir56.group3_genetic.Utils.Reflection.Generalization;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class AbstractClassFilter implements ClassFilter {
	private final Class myClass;
	
	public AbstractClassFilter(Class myClass) {
		this.myClass = myClass;
	}

	@Override
	public boolean accept(Class<?> p_Class) {
		if (Modifier.isAbstract(p_Class.getModifiers())) {
			//We do no search Abstract classes
			return false;
		}

		Set<Class> superClass = Generalization.getGeneralizations(p_Class);
		for (Class class1 : superClass) {
			if (class1 == myClass) {
				return true;
			}			
		}
		
		return false;
	}
}
