package fr.isen.cir56.group3_genetic.Wizard.Configurator.ParameterPanel;

import fr.isen.cir56.group3_genetic.Wizard.Configurator.JPanelChangedListener;
import fr.isen.cir56.group3_genetic.Wizard.Configurator.Selector.ValueChangedListener;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ParameterProbabilityChangedListener extends JPanelChangedListener<ParameterPanel>implements ValueChangedListener{

	public ParameterProbabilityChangedListener(ParameterPanel controller) {
		super(controller);
	}
	
	@Override
	public void valueChanged(int oldValue, int newValue) {
		this.controller.setValue(newValue);
	}
	
}
