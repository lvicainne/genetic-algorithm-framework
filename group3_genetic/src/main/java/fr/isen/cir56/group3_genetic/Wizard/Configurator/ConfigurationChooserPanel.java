package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Genotype.AbstractFactory;
import fr.isen.cir56.group3_genetic.Operator.OperatorInterface;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import fr.isen.cir56.group3_genetic.Utils.Spring.SpringUtilities;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.ConstraintParameter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.ChromosomeFactoryClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.ConstraintClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.CrossoverClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.MutationClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.SelectorClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.Selector.SelectorPanel;
import java.awt.BorderLayout;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

	private final ComboBoxSelector comboSelector;
	private final ComboBoxSelector comboMutation;
	private final ComboBoxSelector comboCrossover;
	private final ComboBoxSelector comboConstraint;
	private final SelectorPanel crossoverProbability;
	private final SelectorPanel mutationProbability;
	private final SelectorPanel populationSize;
	private final ComboBoxSelector comboFactory;
	private Configuration config;

	public ConfigurationChooserPanel() {
		List<String> liste = new LinkedList<>();
		liste.add("fr.isen.cir56");

		this.comboFactory = new ComboBoxSelector(liste, new ChromosomeFactoryClassFilter());
		this.comboSelector = new ComboBoxSelector(liste, new SelectorClassFilter());
		this.comboMutation = new ComboBoxSelector(liste, new MutationClassFilter());
		this.comboCrossover = new ComboBoxSelector(liste, new CrossoverClassFilter());
		this.comboConstraint = new ComboBoxSelector(liste, new ConstraintClassFilter());

		this.crossoverProbability = new SelectorPanel("Crossover probability", 0, 100, 50);
		this.mutationProbability = new SelectorPanel("Mutation probability", 0, 100, 50);
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
				4, 2, //rows, cols
				6, 6, //initX, initY
				6, 6);       //xPad, yPad

		JPanel probabilitiesPanel = new JPanel();
		probabilitiesPanel.setLayout(new BoxLayout(probabilitiesPanel, BoxLayout.PAGE_AXIS));
		probabilitiesPanel.add(crossoverProbability);
		probabilitiesPanel.add(mutationProbability);
		probabilitiesPanel.add(populationSize);


		this.setLayout(new BorderLayout());
		this.add(comboPanel);
		this.add(probabilitiesPanel, BorderLayout.SOUTH);


	}

	protected OperatorInterface getCrossoverOperator() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		double currentCrossoverProbability = this.crossoverProbability.getCurrentValue();
		Class crossoverClass = (Class) this.comboCrossover.getSelectedItem();
		Constructor c = crossoverClass.getConstructor(double.class);
		OperatorInterface crossoverOperator = (OperatorInterface) c.newInstance(currentCrossoverProbability);
		return crossoverOperator;
	}

	protected OperatorInterface getMutationOperator() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		double currentMutationProbability = this.mutationProbability.getCurrentValue();
		Class operatorClass = (Class) this.comboMutation.getSelectedItem();
		Constructor c = operatorClass.getConstructor(double.class);
		OperatorInterface operator = (OperatorInterface) c.newInstance(currentMutationProbability);
		return operator;
	}

	protected SelectorInterface getSelector() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		Class selectorClass = (Class) this.comboSelector.getSelectedItem();
		Constructor c = selectorClass.getConstructor();
		SelectorInterface selector = (SelectorInterface) c.newInstance();
		return selector;
	}

	protected ConstraintInterface getConstraint() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
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
		Class selectorClass = (Class) this.comboFactory.getSelectedItem();
		Constructor c = selectorClass.getConstructor(ConfigurationInterface.class);
		AbstractFactory factory = (AbstractFactory) c.newInstance(config);
		return factory;
	}

	public ConfigurationInterface getConfiguration() throws InvalidConfigurationException, NoSuchMethodException {
		config = new Configuration();

		try {

			config.addConstraint(this.getConstraint());
			config.addOperator(this.getCrossoverOperator());
			config.addOperator(this.getMutationOperator());
			config.addSelector(this.getSelector());
			config.setChromosomeFactory(this.getFactory(config));
			config.setPopulationSize(this.populationSize.getCurrentValue());

		} catch (InstantiationException ex) {
			Logger.getLogger(ConfigurationChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ConfigurationChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(ConfigurationChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(ConfigurationChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SecurityException ex) {
			Logger.getLogger(ConfigurationChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
		}

		return config;
	}
}
