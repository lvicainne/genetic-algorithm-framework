package fr.isen.cir56.group3_genetic.Wizard.Configurator.ParameterPanel;

import fr.isen.cir56.group3_genetic.Wizard.Configurator.JPanelChangedListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ParameterCheckboxChangedListener extends JPanelChangedListener<ParameterPanel> implements ActionListener {
	
	public ParameterCheckboxChangedListener(ParameterPanel controller) {
		super(controller);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.controller.setSelected(this.controller.getCheckbox().isSelected());
	}

}
