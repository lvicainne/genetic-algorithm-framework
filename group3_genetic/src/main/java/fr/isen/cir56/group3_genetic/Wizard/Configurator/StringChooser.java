package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Wizard.ParameterChooserInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
class StringChooser implements ParameterChooserInterface {

	JTextField text = new JTextField();
	JLabel label;
	public StringChooser(String name, String defaultValue) {
		this.label = new JLabel(name);
		this.text.setText(defaultValue);
	}

	@Override
	public JPanel getJPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.add(label, BorderLayout.WEST);
		panel.add(text);
		return panel;
	}
	
	public Object getValue() {
		return this.text.getText();
	}
	
}
