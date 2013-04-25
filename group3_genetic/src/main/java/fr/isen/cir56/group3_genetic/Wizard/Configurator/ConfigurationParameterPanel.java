package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Utils.Reflection.Generalization;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Set;
import javax.swing.JPanel;

/**
 * Create a panel by introspection to define parameters froms constructors
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ConfigurationParameterPanel extends JPanel {
	public ConfigurationParameterPanel(Constructor constructor) {
		Class[] constructorParameters = constructor.getParameterTypes();
		for (Class parameter : constructorParameters) {
			if(parameter.isPrimitive()) {
				System.out.println("TYPE PRIMITIF");
			} else {
				JPanel panel = this.generateJPanelFromClass(parameter);
				if(panel != null) {
					this.add(panel);
				}
			}

		}
	}
	
	/**
	 * Generate a subpanel which define components for creating the class
	 * @param class1
	 * @return null if the class is a Configuration (because its attributes are definined with another tab)
	 */
	public JPanel generateJPanelFromClass(Class class1) {
		if(class1 == Configuration.class) {
			return null;
		}
		
		System.out.println("GENERATE PANEL");
		Set<Class> superClasses = Generalization.getGeneralizations(class1);
		for (Class superClass : superClasses) {
			if(superClass == Configuration.class) {
				
			}
				
		}
			
					
		return null;
	}
}
