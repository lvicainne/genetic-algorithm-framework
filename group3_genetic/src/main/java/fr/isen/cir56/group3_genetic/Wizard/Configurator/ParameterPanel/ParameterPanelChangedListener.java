package fr.isen.cir56.group3_genetic.Wizard.Configurator.ParameterPanel;

import java.util.EventListener;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface ParameterPanelChangedListener extends EventListener {
	public void parameterChanged(boolean isSelected, double newProbability);
}
