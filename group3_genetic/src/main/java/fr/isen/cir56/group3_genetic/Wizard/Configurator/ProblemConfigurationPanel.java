package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.AbstractChromosomeFactory;
import fr.isen.cir56.group3_genetic.Utils.Reflection.AnnotationFilters;
import fr.isen.cir56.group3_genetic.Utils.Reflection.Generalization;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This sub-panel ofConfigurationProblemPanel permits choose each parameter
 * specific to the choosen factory Create a panel by introspection to define
 * parameters froms constructors
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ProblemConfigurationPanel extends JPanel {

	private final Class classFactory;
	private List<List<Class>> parameters = new LinkedList<>();

	public ProblemConfigurationPanel(Class classFactory) {
		this.classFactory = classFactory;
		JPanel panel = this.generateJPanelFromClass(classFactory);
		if (panel != null) {
			this.add(panel);
		}
	}

	/**
	 * Generate a subpanel which define components for creating the class
	 *
	 * @param class1
	 * @return null if the class is a Configuration (because its attributes are
	 * definined with another tab)
	 */
	public JPanel generateJPanelFromClass(Class class1) {
		if (class1 == Configuration.class || class1 == ConfigurationInterface.class) {
			return null;
		}

		Constructor constructor = AnnotationFilters.getDefaultConstructor(class1);
		if (constructor == null) {
			throw new UnsupportedOperationException("You have to define a default constructor for "
					+ "your class " + class1.toString()
					+ " with @DefaultConstructor annotation");
		}


		Class[] parameterTypes = constructor.getParameterTypes();
		for (Class parameterType : parameterTypes) {
			if (parameterType.isPrimitive() || parameterType == String.class) {
				this.addClassParameter(class1, parameterType);
				System.out.println("TYPE PRIMITIF");
			} else {
				System.out.println("TYPE NON PRIMITIF");
				System.out.println(parameterType.getCanonicalName());
				//generateJPanelFromClass(parameterType);
			}
		}
		
		JPanel myPanel = null;
		
		int index1 = this.parameters.indexOf(class1);
		List<Class> subList = null;
		if (index1 > -1) {
			subList = this.parameters.get(index1);
			
			myPanel = new JPanel();
			for (Class class2 : subList) {
				myPanel.add(new JLabel("Truc"));
			}
		}
/*
		subList.add(parameter);		


		System.out.println("GENERATE PANEL");
		Set<Class> superClasses = Generalization.getGeneralizations(class1);
		for (Class superClass : superClasses) {
			if (superClass == Configuration.class) {
			}

		}/**/

		return myPanel;
	}

	public AbstractChromosomeFactory getFactory() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {

		Constructor constructor = AnnotationFilters.getDefaultConstructor(classFactory);
		//List<Class> subList = this.parameters.get(index1);
		return (AbstractChromosomeFactory) constructor.newInstance(this.parameters.toArray());
	}

	private void addClassParameter(Class mainClass, Class parameter) {
		int index1 = this.parameters.indexOf(mainClass);

		List<Class> subList = null;
		if (index1 < 0) {
			subList = new LinkedList<>();
			this.parameters.add(subList);
		} else {
			subList = this.parameters.get(index1);
		}

		subList.add(parameter);
	}
}
