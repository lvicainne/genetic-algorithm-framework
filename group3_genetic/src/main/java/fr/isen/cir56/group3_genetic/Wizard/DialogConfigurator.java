package fr.isen.cir56.group3_genetic.Wizard;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.View.Configurator.ConfiguratorLauncher;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class DialogConfigurator extends JDialog implements ActionListener {
	private final ConfiguratorLauncher launcher;

	public DialogConfigurator(JFrame frame) {
		super(frame, "Configurator", true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		launcher = new ConfiguratorLauncher();
		JButton myButton = new JButton("OK");
		myButton.addActionListener(this);
		
		//this.getContentPane().add(launcher);
		this.getContentPane().add(myButton, BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
	}

	public ConfigurationInterface getConfiguration() {
		return this.launcher.getConfiguration();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
