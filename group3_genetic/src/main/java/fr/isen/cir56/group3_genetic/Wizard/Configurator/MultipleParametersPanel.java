/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Utils.Reflection.AnnotationFilters;
import fr.isen.cir56.group3_genetic.Utils.Reflection.ClassFilter;
import fr.isen.cir56.group3_genetic.Utils.Reflection.Generalization;
import fr.isen.cir56.group3_genetic.Utils.Reflection.PackageHelper;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.ConstraintParameter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ParameterPanel.ParameterPanel;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class MultipleParametersPanel<ParameterType> extends JPanel {
	
		public static double MAX_VALUE_CONSTRAINT = 2000;
	
	private List<ParameterPanel> parametersPanel = new LinkedList<>();
	
	public MultipleParametersPanel(List<String> packageNames, ClassFilter filter, double defaultprobability) {
		List<Class> foundClasses = new LinkedList<>();
		
		for (String packageName : packageNames) {
			try {
				// Find classes accepted by the filter in the package "some.package" but not in its sub-package.
				Collection<Class<?>> classes = PackageHelper.getInstance().getClasses(packageName, true, filter);
				foundClasses.addAll(classes);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(MultipleParametersPanel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for (Class myClass : foundClasses) {
			String currentText = myClass.toString();
			double currentProbability = defaultprobability;
			double currentMaxValue = MAX_VALUE_CONSTRAINT;
			
			ConstraintParameter parameterAnnotation = AnnotationFilters.getConstraintParameterAnnotation(myClass);
			if(parameterAnnotation != null) {
				currentText = parameterAnnotation.name()[0];
				currentProbability = (new Double(parameterAnnotation.defaultValue()[0])).doubleValue();
				currentMaxValue = (new Double(parameterAnnotation.maxValue()[0])).doubleValue();
				
			}

			ParameterPanel parameter = null;
			if(Generalization.getGeneralizations(myClass).contains(ConstraintInterface.class)) {
				parameter = new ParameterPanel(myClass, currentText, currentProbability, currentMaxValue);
			} else {
				parameter = new ParameterPanel(myClass, currentText, currentProbability);
			}
			
			this.parametersPanel.add(parameter);
			this.add(parameter);		
		}

	}

	public List<ParameterPanel> getParametersPanel() {
		return parametersPanel;
	}
	
}