package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.AbstractFactory;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.DefaultConstructor;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.ChromosomeFactoryClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.ConstraintClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.CrossoverClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.MutationClassFilter;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.SelectorClassFilter;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ProblemChooserPanel extends JPanel implements ItemListener {

	private final UniqueParameterPanel comboFactory;
	private JPanel parametersPanel;

	public ProblemChooserPanel() {
		List<String> liste = new LinkedList<>();
		liste.add("fr.isen.cir56");

		this.comboFactory = new UniqueParameterPanel(liste, new ChromosomeFactoryClassFilter());
		//this.comboFactory.addItemListener(this);
		
		parametersPanel = new JPanel();
		
		this.add(comboFactory, BorderLayout.NORTH);
		this.add(parametersPanel);
	}

	protected AbstractFactory getFactory(ConfigurationInterface config) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		return null;
/*		Class selectorClass = (Class) this.comboFactory.getSelectedItem();
		Constructor c = selectorClass.getConstructor(ConfigurationInterface.class);
		AbstractFactory factory = (AbstractFactory) c.newInstance(config);
		return factory;
*/	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		this.remove(parametersPanel);
		/*
		Class classFactory = (Class) this.comboFactory.getSelectedItem();
		Constructor[] constructors = classFactory.getDeclaredConstructors();
		for (Constructor constructor : constructors) {
			Annotation annotation = constructor.getAnnotation(DefaultConstructor.class);
			if(annotation != null) {
				this.parametersPanel = new ConfigurationParameterPanel(constructor);
				this.add(parametersPanel);
			}

		}*/
		
	}
}
