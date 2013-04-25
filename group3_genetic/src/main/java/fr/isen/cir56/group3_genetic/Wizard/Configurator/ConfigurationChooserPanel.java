package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Genotype.AbstractFactory;
import fr.isen.cir56.group3_genetic.Operator.OperatorInterface;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import fr.isen.cir56.group3_genetic.Utils.Reflection.PackageHelper;
import fr.isen.cir56.group3_genetic.Utils.Spring.SpringUtilities;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.ConstraintParameter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.ChromosomeFactoryClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.ConstraintClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.CrossoverClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.MutationClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.SelectorClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ParameterPanel.ParameterPanel;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.Selector.SelectorPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ConfigurationChooserPanel extends JPanel {
	public static double DEFAULT_PROBA_MUTATION = 30;
	public static double DEFAULT_PROBA_CROSSOVER = 80;
	public static double DEFAULT_VALUE_CONSTRAINT = 60;
	private final UniqueParameterPanel<SelectorInterface> comboSelector;
	private final MultipleParametersPanel<OperatorInterface> comboMutation;
	private final MultipleParametersPanel<OperatorInterface> comboCrossover;
	private final MultipleParametersPanel<ConstraintInterface> comboConstraint;
	private final SelectorPanel populationSize;
	private Configuration config;

	public ConfigurationChooserPanel() {
		List<String> liste = new LinkedList<>();
		liste.add("fr.isen.cir56");

		this.comboSelector = new UniqueParameterPanel(liste, new SelectorClassFilter());
		this.comboMutation = new MultipleParametersPanel(liste, new MutationClassFilter(), DEFAULT_PROBA_MUTATION);
		this.comboCrossover = new MultipleParametersPanel(liste, new CrossoverClassFilter(), DEFAULT_PROBA_CROSSOVER);
		this.comboConstraint = new MultipleParametersPanel(liste, new ConstraintClassFilter(), DEFAULT_VALUE_CONSTRAINT);

		this.populationSize = new SelectorPanel("Population Size", 0, 1000, 10);

		JPanel comboPanel = new JPanel();
		comboPanel.setLayout(new SpringLayout());
		JLabel labelSelector = new JLabel("Selector :", JLabel.TRAILING);
		JLabel labelMutation = new JLabel("Mutation :", JLabel.TRAILING);
		JLabel labelCrossover = new JLabel("Crossover :", JLabel.TRAILING);
		JLabel labelConstraint = new JLabel("Constraint :", JLabel.TRAILING);
				

		comboPanel.add(labelSelector);
		labelSelector.setLabelFor(comboSelector);
		comboPanel.add(comboSelector);

		comboPanel.add(labelCrossover);
		labelCrossover.setLabelFor(comboCrossover);
		comboPanel.add(comboCrossover);
		
		comboPanel.add(labelMutation);
		labelMutation.setLabelFor(comboMutation);
		comboPanel.add(comboMutation);
		
		comboPanel.add(labelConstraint);
		labelConstraint.setLabelFor(comboConstraint);
		comboPanel.add(comboConstraint);

		//Lay out the panel.
		SpringUtilities.makeCompactGrid(comboPanel,
				8, 1, //rows, cols
				6, 6, //initX, initY
				6, 6);       //xPad, yPad

		JPanel probabilitiesPanel = new JPanel();
		probabilitiesPanel.setLayout(new BoxLayout(probabilitiesPanel, BoxLayout.PAGE_AXIS));
		probabilitiesPanel.add(populationSize);


		this.setLayout(new BorderLayout());
		this.add(comboPanel);
		this.add(probabilitiesPanel, BorderLayout.SOUTH);


	}
	
	/**
	 * Update the graphic view with the current configuration settings
	 * @param configuration 
	 */
	public void updatePanel(ConfigurationInterface configuration) {
		//update the constraint
		List<ConstraintInterface> constraints = configuration.getConstraints();
		List<ParameterPanel> choosen = this.comboConstraint.getParametersPanel();
		for (ConstraintInterface constraintInterface : constraints) {
			for (ParameterPanel parameterPanel : choosen) {
				if(constraintInterface.getClass() == parameterPanel.getParameterClass()) {
					parameterPanel.setSelected(true);
					parameterPanel.setValue(constraintInterface.getValue());
				}
			}
		}
		
		//update the operators
		List<OperatorInterface> operators = configuration.getOperators();
		List<ParameterPanel> mutations = this.comboMutation.getParametersPanel();
		List<ParameterPanel> crossovers = this.comboCrossover.getParametersPanel();
		for (OperatorInterface operatorInterface : operators) {
			
			for (ParameterPanel parameterPanel : mutations) {
				if(operatorInterface.getClass() == parameterPanel.getParameterClass()) {
					parameterPanel.setSelected(true);
					parameterPanel.setValue(operatorInterface.getProbability());
				}
			}
			
			for (ParameterPanel parameterPanel : crossovers) {
				if(operatorInterface.getClass() == parameterPanel.getParameterClass()) {
					parameterPanel.setSelected(true);
					parameterPanel.setValue(operatorInterface.getProbability());
				}
			}
		}
		
		//update the selectors
		List<SelectorInterface> selectors = configuration.getSelectors();
		for (SelectorInterface selectorInterface : selectors) {
			this.comboSelector.setSelectedItem(selectorInterface.getClass());
		}
	}
/*
	protected OperatorInterface getCrossoverOperators() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		double currentCrossoverProbability = this.crossoverProbability.getCurrentValue();
		Class crossoverClass = (Class) this.comboCrossover.getSelectedItem();
		Constructor c = crossoverClass.getConstructor(double.class);
		OperatorInterface crossoverOperator = (OperatorInterface) c.newInstance(currentCrossoverProbability);
		return crossoverOperator;
	}

	protected OperatorInterface getMutationOperators() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		double currentMutationProbability = this.mutationProbability.getCurrentValue();
		Class operatorClass = (Class) this.comboMutation.getSelectedItem();
		Constructor c = operatorClass.getConstructor(double.class);
		OperatorInterface operator = (OperatorInterface) c.newInstance(currentMutationProbability);
		return operator;
	}

	protected SelectorInterface getSelectors() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		Class selectorClass = (Class) this.comboSelector.getSelectedItem();
		Constructor c = selectorClass.getConstructor();
		SelectorInterface selector = (SelectorInterface) c.newInstance();
		return selector;
	}

	protected ConstraintInterface getConstraints() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		Class selectorClass = (Class) this.comboConstraint.getSelectedItem();

		Constructor[] constructors = selectorClass.getConstructors();
		ConstraintInterface constraintInstance = null;
		for (Constructor constructor : constructors) {
			if(constructor.getParameterTypes().length == 0) {
				constraintInstance = (ConstraintInterface) constructor.newInstance();

			} else {
				Annotation[] annotations = constructor.getAnnotations();
			
				for (Annotation annotation : annotations) {
					if(annotation instanceof ConstraintParameter) {
						ConstraintParameter parameterAnotation = (ConstraintParameter) annotation;

						String s = null;
						
						while(s == null) {
							s = (String)JOptionPane.showInputDialog(
							this,
							"Please, choose a value for "+parameterAnotation.name()[0]+" :",
							"Select a value for "+parameterAnotation.name()[0],
							JOptionPane.QUESTION_MESSAGE,
							null,
							null,
							parameterAnotation.defaultValue()[0]); // valeur initiale
						}

						if(constructor.getParameterTypes()[0] == int.class) {
							int myValue = Integer.parseInt(s);
							constraintInstance = (ConstraintInterface) constructor.newInstance(myValue);
						} else if(constructor.getParameterTypes()[0] == double.class) {
							double myValue =  Double.parseDouble(s);
							constraintInstance = (ConstraintInterface) constructor.newInstance(myValue);
						} else if(constructor.getParameterTypes()[0] == long.class) {
							long myValue =  Long.parseLong(s);
							constraintInstance = (ConstraintInterface) constructor.newInstance(myValue);
						} else if(constructor.getParameterTypes()[0] == boolean.class) {
							boolean myValue =  Boolean.parseBoolean(s);
							constraintInstance = (ConstraintInterface) constructor.newInstance(myValue);
						} else if(constructor.getParameterTypes()[0] == float.class) {
							float myValue =  Float.parseFloat(s);
							constraintInstance = (ConstraintInterface) constructor.newInstance(myValue);
						} else if(constructor.getParameterTypes()[0] == short.class) {
							short myValue = Short.parseShort(s);
							constraintInstance = (ConstraintInterface) constructor.newInstance(myValue);
						}
										
					}
				}
			}
		}
		
		return constraintInstance;
	}

	protected AbstractFactory getFactory(ConfigurationInterface config) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		selectorClass = this.comboFactory.getSelectedItem();
		Constructor c = selectorClass.getConstructor(ConfigurationInterface.class);
		AbstractFactory factory = (AbstractFactory) c.newInstance(config);
		return factory;
	}
*/
	public ConfigurationInterface getConfiguration() throws InvalidConfigurationException, NoSuchMethodException {
		config = new Configuration();

		try {
/*
			config.addConstraints(this.getConstraints());
			config.addOperators(this.getCrossoverOperators());
			config.addOperators(this.getMutationOperators());
			config.addSelectors(this.getSelectors());
			config.setChromosomeFactory(this.getFactory(config));*/
			config.setPopulationSize(this.populationSize.getCurrentValue());
/*
		} catch (InstantiationException ex) {
			Logger.getLogger(ConfigurationChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ConfigurationChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(ConfigurationChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(ConfigurationChooserPanel.class.getName()).log(Level.SEVERE, null, ex);*/
		} catch (SecurityException ex) {
			Logger.getLogger(ConfigurationChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
		}

		return config;
	}
	
	/**
	 * Update the configuration with the new parameters.
	 * @param configuration 
	 */
	public void updateConfiguration(ConfigurationInterface configuration) {
		try {
			configuration.unlockSettings();
			configuration.removeConstraints();
			configuration.removeOperators();
			configuration.removeSelectors();
		} catch(InvalidConfigurationException ex) {
			//may be thrown if the configuration has not been unlocked
			Logger.getLogger(ConfigurationChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		
	}
}
