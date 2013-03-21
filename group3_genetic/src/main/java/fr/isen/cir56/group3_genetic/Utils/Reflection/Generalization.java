/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Utils.Reflection;

import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * Copyright 2003 Sapient
 * @since carbon 2.0
 * @author Greg Hinkle, March 2003
 * @version $Revision: 1.5 $($Author: dvoet $ / $Date: 2003/05/05 21:21:23 $)
 */
public class Generalization {
	/**
     * Builds an <b>unordered</b> set of all interface and object classes that
     * are generalizations of the provided class.
     * @param classObject the class to find generalization of.
     * @return a Set of class objects.
     */
    public static Set<Class> getGeneralizations(Class classObject) {
        Set<Class> generalizations = new HashSet<>();

        generalizations.add(classObject);

        Class superClass = classObject.getSuperclass();
        if (superClass != null) {
            generalizations.addAll(getGeneralizations(superClass));
        }

        Class[] superInterfaces = classObject.getInterfaces();
        for (int i = 0; i < superInterfaces.length; i++) {
            Class superInterface = superInterfaces[i];
            generalizations.addAll(getGeneralizations(superInterface));
        }

        return generalizations;
    }
}
