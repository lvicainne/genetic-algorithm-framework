/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View.Configurator;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.View.Configurator.ClassFilters.ChromosomeFactoryClassFilter;
import fr.isen.cir56.group3_genetic.View.Configurator.ClassFilters.CrossoverClassFilter;
import fr.isen.cir56.group3_genetic.View.Configurator.ClassFilters.MutationClassFilter;
import fr.isen.cir56.group3_genetic.View.Configurator.ClassFilters.SelectorClassFilter;
import fr.isen.cir56.group3_genetic.View.Configurator.Selector.SelectorPanel;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ConfiguratorLauncher extends JPanel {

	private final ComboBoxSelector comboSelector;
	private final ComboBoxSelector comboMutation;
	private final ComboBoxSelector comboCrossover;
	private final SelectorPanel crossoverProbability;
	private final SelectorPanel mutationProbability;
	private final SelectorPanel populationSize;
	private final ComboBoxSelector comboFactory;

	public ConfiguratorLauncher() {
		List<String> liste = new LinkedList<>();
		liste.add("fr.isen.cir56");

		this.comboFactory = new ComboBoxSelector(liste, new ChromosomeFactoryClassFilter());
		this.comboSelector = new ComboBoxSelector(liste, new SelectorClassFilter());
		this.comboMutation = new ComboBoxSelector(liste, new MutationClassFilter());
		this.comboCrossover = new ComboBoxSelector(liste, new CrossoverClassFilter());

		this.crossoverProbability = new SelectorPanel("Crossover probability", 0, 100, 50);
		this.mutationProbability = new SelectorPanel("Mutation probability", 0, 100, 50);
		this.populationSize = new SelectorPanel("Population Size", 0, 1000, 10);

		JPanel comboPanel = new JPanel();
		//comboPanel.setLayout(new GridLayout(2, 2));

		//comboPanel.add(new JLabel("Problem :"));
		comboPanel.add(comboFactory);

		//comboPanel.add(new JLabel("Selector"));
		comboPanel.add(comboSelector);

		//comboPanel.add(new JLabel("Mutation operator"));
		comboPanel.add(comboMutation);

		JLabel crossoverLabel = new JLabel("Crossover operator");
		crossoverLabel.setLabelFor(comboPanel);

		comboPanel.add(crossoverLabel);
		comboPanel.add(comboCrossover);

		JPanel probabilitiesPanel = new JPanel();
		probabilitiesPanel.setLayout(new BoxLayout(probabilitiesPanel, BoxLayout.PAGE_AXIS));
		probabilitiesPanel.add(crossoverProbability);
		probabilitiesPanel.add(mutationProbability);
		probabilitiesPanel.add(populationSize);


		this.add(comboPanel);
		this.add(probabilitiesPanel);


	}

	public ConfigurationInterface getConfiguration() {
		return new Configuration();
	}
}
