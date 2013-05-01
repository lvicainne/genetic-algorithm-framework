package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Genotype.AbstractChromosomeFactory;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ClassFilters.ChromosomeFactoryClassFilter;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * This panel is the first tab for the configuration.
 * This one permits choose the correct factory for the problem and its parameters
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ChooserProblemPanel extends JPanel implements ItemListener {

	private final UniqueSelectableParameterPanel comboFactory;
	private ProblemConfigurationPanel parametersPanel;

	public ChooserProblemPanel() {
		List<String> liste = new LinkedList<>();
		liste.add("fr");

		this.comboFactory = new UniqueSelectableParameterPanel(liste, new ChromosomeFactoryClassFilter());
		this.comboFactory.addItemListener(this);
		
		
		this.add(comboFactory, BorderLayout.NORTH);
		this.add(new JPanel());
		
		this.updateParametersPanel();
	}

	/**
	 * 
	 * @return null if no factory selected in the GUI
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalAccessException 
	 */
	protected AbstractChromosomeFactory getFactory(ConfigurationInterface configuration) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalAccessException {
		if(parametersPanel != null) {
			return this.parametersPanel.getFactory(configuration);
		}
		
		//if no factory selected, we return null !
		return null;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		this.updateParametersPanel();
	}
	
	protected void updateParametersPanel() {
		if(parametersPanel != null) {
			this.remove(parametersPanel);
		}
		
		Class classFactory = (Class) this.comboFactory.getSelectedItem();

		if(classFactory != null) {
			this.parametersPanel = new ProblemConfigurationPanel(classFactory);
			this.add(parametersPanel, BorderLayout.SOUTH);
			this.repaint();
			this.validate();
			this.repaint();
		}
	}
	
	public ConfigurationInterface updateConfiguration(ConfigurationInterface configuration) {
		try {
			configuration.unlockSettings();
			configuration.setChromosomeFactory(this.getFactory(configuration));
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | InvalidConfigurationException ex) {
			Logger.getLogger(ChooserConfigurationPanel.class.getName()).log(Level.SEVERE, null, ex);
		}
		return configuration;
	}
	
}
