/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View.Configurator;

import fr.isen.cir56.group3_genetic.View.Configurator.ClassFilters.CrossoverClassFilter;
import fr.isen.cir56.group3_genetic.View.Configurator.ClassFilters.MutationClassFilter;
import fr.isen.cir56.group3_genetic.View.Configurator.ClassFilters.SelectorClassFilter;
import fr.isen.cir56.group3_genetic.View.Configurator.Selector.SelectorPanel;
import java.util.LinkedList;
import java.util.List;
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
	
	public ConfiguratorLauncher() {
		List<String> liste = new LinkedList<>();
		liste.add("fr.isen.cir56");
		
		this.comboSelector = new ComboBoxSelector(liste, new SelectorClassFilter());
		this.comboMutation = new ComboBoxSelector(liste, new MutationClassFilter());
		this.comboCrossover = new ComboBoxSelector(liste, new CrossoverClassFilter());
		
		this.crossoverProbability = new SelectorPanel("Crossover probability", 0, 100, 50);
		this.mutationProbability = new SelectorPanel("Mutation probability", 0, 100, 50);
		this.populationSize = new SelectorPanel("Population Size", 0, 500, 10);
		
		this.add(comboSelector);
		this.add(comboMutation);
		this.add(comboCrossover);
		this.add(crossoverProbability);
		this.add(mutationProbability);
		this.add(populationSize);
	}
	
}
