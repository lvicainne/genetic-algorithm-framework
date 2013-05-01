package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.AbstractChromosomeFactory;
import fr.isen.cir56.group3_genetic.Wizard.ParameterChooserInterface;
import fr.isen.cir56.group3_genetic.Utils.Reflection.AnnotationFilters;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.Parameter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;
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
	private List<ParameterChooserInterface> listChooser;

	public ProblemConfigurationPanel(Class classFactory) {
		this.classFactory = classFactory;
		this.listChooser = new LinkedList<>();
		JPanel panel = this.generateJPanelFromClass(classFactory);
		if (panel != null) {
			System.out.println("addPanel");
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

		
		
		Parameter annotation = (Parameter) constructor.getAnnotation(Parameter.class);

		Class[] parameterTypes = constructor.getParameterTypes();
		
		if (annotation == null) {
			throw new UnsupportedOperationException("You have to define a @Parameter annotation in your factory constructor "  + class1.toString());
		}
		if (annotation.name().length != (parameterTypes.length-1)) {
			throw new UnsupportedOperationException("You have to define the same number in your parameter annotation as in your constructor");
		}
				
		JPanel myPanel = new JPanel();
		
		for(int i = 1; i < parameterTypes.length;i++) {
			//we begin to 1 (0th is the Configuration parameter)
			Class parameterType = parameterTypes[i];
			if(!parameterType.isPrimitive() && parameterType != String.class) {
				throw new InvalidParameterException("You can only add primitive parameters for your default constructor of factory");
			}
			
			ParameterChooserInterface chooser = null;
			if(parameterType == String.class) {
				chooser = new StringChooser(annotation.name()[i-1]);
			} else {
				chooser = new NumberChooser(annotation.name()[i-1], 0 , 50, Integer.parseInt(annotation.defaultValue()[i-1]));
			}
			this.listChooser.add(chooser);
			myPanel.add(chooser.getJPanel());
			
		}

		return myPanel;
	}

	public AbstractChromosomeFactory getFactory(ConfigurationInterface configuration) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {

		Constructor constructor = AnnotationFilters.getDefaultConstructor(classFactory);
		
		int numberParameters = this.listChooser.size();
		Object[] parameterValues = new Object[numberParameters+1];
		parameterValues[0] = configuration;
		for (int i = 0; i < numberParameters; i++) {
			parameterValues[i+1] = this.listChooser.get(i).getValue();
		}
		
		return (AbstractChromosomeFactory) constructor.newInstance(parameterValues);
	}

}
