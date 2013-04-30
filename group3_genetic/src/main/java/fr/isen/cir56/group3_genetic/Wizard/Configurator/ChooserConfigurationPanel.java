package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Operator.OperatorInterface;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import fr.isen.cir56.group3_genetic.Utils.Reflection.AnnotationFilters;
import fr.isen.cir56.group3_genetic.Utils.Spring.SpringUtilities;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.ConstraintClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.CrossoverClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.MutationClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.SelectorClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ParameterPanel.ParameterPanel;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.SelectorPanel.SelectorPanel;
import java.awt.BorderLayout;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * This panel is the second tab for the configuration.
 * This one permits choose the correct parameters for a standard "Configuration"
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ChooserConfigurationPanel extends JPanel {

	public static int DEFAULT_PROBA_MUTATION = 30;
	public static int DEFAULT_PROBA_CROSSOVER = 80;
	public static int DEFAULT_VALUE_CONSTRAINT = 60;
	private final UniqueSelectableParameterPanel<SelectorInterface> comboSelector;
	private final MultipleSelectableParametersPanel<OperatorInterface> comboMutation;
	private final MultipleSelectableParametersPanel<OperatorInterface> comboCrossover;
	private final MultipleSelectableParametersPanel<ConstraintInterface> comboConstraint;
	private final SelectorPanel populationSize;

	public ChooserConfigurationPanel() {
		List<String> liste = new LinkedList<>();
		liste.add("fr");

		this.comboSelector = new UniqueSelectableParameterPanel(liste, new SelectorClassFilter());
		this.comboMutation = new MultipleSelectableParametersPanel(liste, new MutationClassFilter(), DEFAULT_PROBA_MUTATION);
		this.comboCrossover = new MultipleSelectableParametersPanel(liste, new CrossoverClassFilter(), DEFAULT_PROBA_CROSSOVER);
		this.comboConstraint = new MultipleSelectableParametersPanel(liste, new ConstraintClassFilter(), DEFAULT_VALUE_CONSTRAINT);

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
	 *
	 * @param configuration
	 */
	public void updatePanel(ConfigurationInterface configuration) {
		//update the constraint
		List<ConstraintInterface> constraints = configuration.getConstraints();
		List<ParameterPanel<Class>> choosen = this.comboConstraint.getParametersPanel();
		for (ConstraintInterface constraintInterface : constraints) {
			for (ParameterPanel parameterPanel : choosen) {
				if (constraintInterface.getClass() == parameterPanel.getParameterClass()) {
					parameterPanel.setSelected(true);
					parameterPanel.setValue((int) constraintInterface.getValue());
				}
			}
		}

		//update the operators
		List<OperatorInterface> operators = configuration.getOperators();
		List<ParameterPanel<Class>> mutations = this.comboMutation.getParametersPanel();
		List<ParameterPanel<Class>> crossovers = this.comboCrossover.getParametersPanel();
		for (OperatorInterface operatorInterface : operators) {

			for (ParameterPanel parameterPanel : mutations) {
				if (operatorInterface.getClass() == parameterPanel.getParameterClass()) {
					parameterPanel.setSelected(true);
					parameterPanel.setValue((int) operatorInterface.getProbability());
				}
			}

			for (ParameterPanel parameterPanel : crossovers) {
				if (operatorInterface.getClass() == parameterPanel.getParameterClass()) {
					parameterPanel.setSelected(true);
					parameterPanel.setValue((int) operatorInterface.getProbability());
				}
			}
		}

		//update the selectors
		List<SelectorInterface> selectors = configuration.getSelectors();
		for (SelectorInterface selectorInterface : selectors) {
			this.comboSelector.setSelectedItem(selectorInterface.getClass());
		}

		this.populationSize.setCurrentValue(configuration.getPopulationSize());
	}

	protected List<OperatorInterface> getCrossoverOperators() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		List<OperatorInterface> operators = new LinkedList<>();
		List<ParameterPanel<Class>> parameterPanels = this.comboCrossover.getParametersPanel();
		for (ParameterPanel parameterPanel : parameterPanels) {
			if (parameterPanel.isSelected()) {
				double prob = parameterPanel.getValue();
				Class crossoverClass = (Class) parameterPanel.getParameterClass();
				Constructor c = crossoverClass.getConstructor(double.class);
				OperatorInterface crossoverOperator = (OperatorInterface) c.newInstance(prob);
				operators.add(crossoverOperator);
			}
		}

		return operators;
	}

	protected List<OperatorInterface> getMutationOperators() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		List<OperatorInterface> operators = new LinkedList<>();
		List<ParameterPanel<Class>> parameterPanels = this.comboMutation.getParametersPanel();
		for (ParameterPanel parameterPanel : parameterPanels) {
			if (parameterPanel.isSelected()) {
				double prob = parameterPanel.getValue();
				Class crossoverClass = (Class) parameterPanel.getParameterClass();
				Constructor c = crossoverClass.getConstructor(double.class);
				OperatorInterface crossoverOperator = (OperatorInterface) c.newInstance(prob);
				operators.add(crossoverOperator);
			}
		}

		return operators;
	}

	protected SelectorInterface getSelector() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		Class selectorClass = (Class) this.comboSelector.getSelectedItem();
		Constructor c = selectorClass.getConstructor();
		SelectorInterface selector = (SelectorInterface) c.newInstance();
		return selector;
	}

	protected List<ConstraintInterface> getConstraints() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		List<ConstraintInterface> constraints = new LinkedList<>();
		List<ParameterPanel<Class>> parameterPanels = this.comboConstraint.getParametersPanel();
		for (ParameterPanel parameterPanel : parameterPanels) {
			if (parameterPanel.isSelected()) {
				int value = parameterPanel.getValue();
				Class crossoverClass = (Class) parameterPanel.getParameterClass();

				//get the default constructor defined by the annotation @DefaultConstructor
				Constructor constructor = AnnotationFilters.getDefaultConstructor(crossoverClass);
				
				if(constructor == null) {
					throw new UnsupportedOperationException("You have to define a default constructor for "
							+ "your contraint " + parameterPanel.getParameterClass().toString()
							+ " with @DefaultConstructor annotation");
				}

				ConstraintInterface constraintInstance = null;
				if (constructor.getParameterTypes().length == 0) {
					constraintInstance = (ConstraintInterface) constructor.newInstance();

				} else {
					if (constructor.getParameterTypes().length > 1) {
						throw new UnsupportedOperationException("The constraint may have only 1 parameter in its constructor to build it ! (This parameter may be a number)");
					}
					
					//We use constructor with only ONE parameter as number.	
					if(constructor.getParameterTypes()[0] == int.class) {
						int myValue = (int) value;
						constraintInstance = (ConstraintInterface) constructor.newInstance(myValue);
					} else if(constructor.getParameterTypes()[0] == double.class) {
						double myValue =  new Double(value).doubleValue();
						constraintInstance = (ConstraintInterface) constructor.newInstance(myValue);
					} else if(constructor.getParameterTypes()[0] == long.class) {
						long myValue =  (long) value;
						constraintInstance = (ConstraintInterface) constructor.newInstance(myValue);
					} else if(constructor.getParameterTypes()[0] == boolean.class) {
						boolean myValue;
						if(value > 0) {
							myValue = true;
						} else {
							myValue = false;
						}
						constraintInstance = (ConstraintInterface) constructor.newInstance(myValue);
					} else if(constructor.getParameterTypes()[0] == float.class) {
						float myValue =  (float) value;
						constraintInstance = (ConstraintInterface) constructor.newInstance(myValue);
					} else if(constructor.getParameterTypes()[0] == short.class) {
						short myValue = (short) value;
						constraintInstance = (ConstraintInterface) constructor.newInstance(myValue);
					}
				}
				
				if(constraintInstance != null) {
					constraints.add(constraintInstance);
				}
			}
		}

		return constraints;
	}

	/**
	 * Update the configuration with the new parameters.
	 *
	 * @param configuration
	 */
	public void updateConfiguration(ConfigurationInterface configuration) {
		try {
			configuration.unlockSettings();
			configuration.removeConstraints();
			configuration.removeOperators();
			configuration.removeSelectors();

			configuration.addConstraints(this.getConstraints());
			configuration.addOperators(this.getCrossoverOperators());
			configuration.addOperators(this.getMutationOperators());
			configuration.addSelector(this.getSelector());

			configuration.setPopulationSize(this.populationSize.getCurrentValue());
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | InvalidConfigurationException ex) {
			Logger.getLogger(ChooserConfigurationPanel.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
