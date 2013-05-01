package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Wizard.Configurator.SelectorPanel.SelectorPanel;
import fr.isen.cir56.group3_genetic.Wizard.ParameterChooserInterface;
import javax.swing.JPanel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
class NumberChooser implements ParameterChooserInterface {
	SelectorPanel selector;
	public NumberChooser(String label, int min, int max, int defaultValue) {
		selector = new SelectorPanel(label, min, max, defaultValue);
	}

	@Override
	public JPanel getJPanel() {
		return this.selector;
	}

	@Override
	public Object getValue() {
		return this.selector.getCurrentValue();
	}
	
}
