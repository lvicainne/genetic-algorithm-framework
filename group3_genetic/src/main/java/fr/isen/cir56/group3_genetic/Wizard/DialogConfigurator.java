package fr.isen.cir56.group3_genetic.Wizard;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ConfigurationChooserPanel;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ProblemChooserPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class DialogConfigurator extends JDialog implements ActionListener {

	private final ConfigurationChooserPanel chooseConfiguration;
	private final ProblemChooserPanel chooseProblem;
	private final JButton okButton;
	private final GeneticController controller;

	public DialogConfigurator(GeneticController controller) {
		this.setTitle("Configurator");
		this.controller = controller;
	
		
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		chooseConfiguration = new ConfigurationChooserPanel();
		chooseConfiguration.updatePanel(this.controller.getModel().getMonitor().getConfiguration());
		chooseProblem = new ProblemChooserPanel();
		this.okButton = new JButton("OK");
		okButton.addActionListener(this);
		
		JPanel myPanel = new JPanel();
		
		if(!controller.getModel().getMonitor().isProcessing()) {
			JTabbedPane tabs = new JTabbedPane();
			tabs.addTab("Problem", chooseProblem);
			tabs.addTab("Configuration", chooseConfiguration);
			this.getContentPane().add(tabs);
		} else {
			this.getContentPane().add(chooseConfiguration);
		}
		
		this.getContentPane().add(okButton, BorderLayout.SOUTH);

		this.pack();
	}

	public ConfigurationInterface getConfiguration() {
		try {
			return this.chooseConfiguration.getConfiguration();
		} catch (InvalidConfigurationException ex) {
			JOptionPane.showInternalMessageDialog(this, "The selected configuration is not valid");
		} catch (NoSuchMethodException ex) {
			JOptionPane.showInternalMessageDialog(this, "The selected item cannot be instanciate. Please contact your developper.");
		}

		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == okButton) {

			this.chooseConfiguration.updateConfiguration(this.controller.getModel().getMonitor().getConfiguration());
			
			if(!controller.getModel().getMonitor().isProcessing()) {
				ConfigurationInterface configuration = this.getConfiguration();
				//this.controller.setModel(new GeneticModel(configuration));
				
				this.controller.reset();
				
			} else {
				this.controller.getModel().refreshViews(new ConfigurationChangedEvent(this.controller.getModel()));
			}
			
			dispose();
		} else {
			dispose();
		}

	}
}
