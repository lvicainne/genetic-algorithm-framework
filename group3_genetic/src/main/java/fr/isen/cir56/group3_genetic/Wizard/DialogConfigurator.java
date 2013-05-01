package fr.isen.cir56.group3_genetic.Wizard;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.GeneticConfigurationInterface;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Event.ResetPopulationEvent;
import fr.isen.cir56.group3_genetic.Monitor.Monitor;
import fr.isen.cir56.group3_genetic.View.ViewInterface;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ChooserConfigurationPanel;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.ChooserProblemPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class DialogConfigurator extends JDialog implements ActionListener {

	private final ChooserConfigurationPanel chooseConfiguration;
	private ChooserProblemPanel chooseProblem = null;
	private final JButton okButton;
	private final GeneticController controller;

	public DialogConfigurator(GeneticController controller) {
		this.setTitle("Configurator");
		this.controller = controller;
	
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		chooseConfiguration = new ChooserConfigurationPanel();
		chooseConfiguration.updatePanel(this.controller.getModel().getMonitor().getConfiguration());
		
		this.okButton = new JButton("OK");
		okButton.addActionListener(this);
		
		if(!controller.getModel().getMonitor().isProcessing()) {
			chooseProblem = new ChooserProblemPanel();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == okButton) {
			GeneticConfigurationInterface configuration = this.controller.getModel().getMonitor().getConfiguration();
			if(chooseProblem != null) {
				
				configuration = this.chooseProblem.updateConfiguration(configuration);
				ViewInterface<GeneticController> view = this.chooseProblem.updateView();
				this.controller.getModel().resetModel(configuration);
				this.controller.setSpecialView((JPanel) view, view);
				
			}
			
			try {
				this.chooseConfiguration.updateConfiguration(this.controller.getModel().getMonitor().getConfiguration());
			} catch(java.lang.UnsupportedOperationException ex) {
				JOptionPane.showMessageDialog(this, ex.toString(), "ConfigurationError", JOptionPane.ERROR_MESSAGE);
				
				return; //we return so we DO NOT dispose, the user has to reiterate its actions or not click on the okButton
			}
			

			
			this.controller.getModel().refreshViews(new ConfigurationChangedEvent(this.controller.getModel()));
			this.controller.getModel().refreshViews(new ResetPopulationEvent(this.controller.getModel()));
			
			dispose();
		} else {
			dispose();
		}

	}
}
