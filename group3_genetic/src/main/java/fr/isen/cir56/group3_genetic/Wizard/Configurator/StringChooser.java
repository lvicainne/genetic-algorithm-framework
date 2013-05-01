package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Wizard.ParameterChooserInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
	public StringChooser(String name) {
		this.label = new JLabel(name);
	}

	@Override
	public JPanel getJPanel() {
		JPanel panel = new JPanel();
		panel.add(label, BorderLayout.WEST);
		this.text.setMinimumSize(new Dimension(50,100));
		panel.add(text);
		return panel;
	}
	
	public Object getValue() {
		return this.text.getText();
	}
	
}
