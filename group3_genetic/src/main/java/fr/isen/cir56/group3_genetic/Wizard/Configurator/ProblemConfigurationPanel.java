package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.AbstractFactory;
import fr.isen.cir56.group3_genetic.Utils.Reflection.AnnotationFilters;
import fr.isen.cir56.group3_genetic.Utils.Reflection.Generalization;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;

/**
 * This sub-panel ofConfigurationProblemPanel permits choose each parameter
 * specific to the choosen factory
 * Create a panel by introspection to define parameters froms constructors
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ProblemConfigurationPanel extends JPanel {
	private final Class classFactory;
	private List<Class> parameters = new LinkedList<>();
	
	public ProblemConfigurationPanel(Class classFactory) {
		this.classFactory = classFactory;
		JPanel panel = this.generateJPanelFromClass(classFactory);
		if(panel != null) {
			this.add(panel);
		}
	}
	
	/**
	 * Generate a subpanel which define components for creating the class
	 * @param class1
	 * @return null if the class is a Configuration (because its attributes are definined with another tab)
	 */
	public JPanel generateJPanelFromClass(Class class1) {
		if(class1 == Configuration.class || class1 == ConfigurationInterface.class) {
			return null;
		}
				
		Constructor constructor = AnnotationFilters.getDefaultConstructor(class1);
		if(constructor == null) {
				throw new UnsupportedOperationException("You have to define a default constructor for "
				+ "your class " + classFactory.toString()
				+ " with @DefaultConstructor annotation");
		}
		
		
		Class[] parameterTypes = constructor.getParameterTypes();
		for (Class parameterType : parameterTypes) {
			if(parameterType.isPrimitive() || parameterType == String.class) {
				System.out.println("TYPE PRIMITIF");
				this.addClassParameter(parameterType);
			} else {
				generateJPanelFromClass(parameterType);
			}
		}

		
		System.out.println("GENERATE PANEL");
		Set<Class> superClasses = Generalization.getGeneralizations(class1);
		for (Class superClass : superClasses) {
			if(superClass == Configuration.class) {
				
			}
				
		}
			
					
		return null;
	}
	
	public AbstractFactory getFactory() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		//this.getFactory();
		return null;
	}

	private void addClassParameter(Class parameter) {
		
		this.parameters.add(parameter);
	}
}
