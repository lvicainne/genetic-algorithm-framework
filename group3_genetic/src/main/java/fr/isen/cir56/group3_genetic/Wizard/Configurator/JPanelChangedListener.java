package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Controller.ControllerInterface;


/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class JPanelChangedListener<ControllerType extends ControllerInterface> {
	protected ControllerType controller;

	
	public JPanelChangedListener(ControllerType controller) {
		this.controller = controller;
	}

	public ControllerType getController() {
		return controller;
	}

	public void setController(ControllerType controller) {
		this.controller = controller;
	}	
	
}
