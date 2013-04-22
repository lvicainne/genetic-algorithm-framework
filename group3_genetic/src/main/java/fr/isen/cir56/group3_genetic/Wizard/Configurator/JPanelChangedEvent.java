/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Wizard.Configurator;

import fr.isen.cir56.group3_genetic.Controller.ControllerInterface;


/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class JPanelChangedEvent {
	protected ControllerInterface controller;

	
	public JPanelChangedEvent(ControllerInterface controller) {
		this.controller = controller;
	}

	public ControllerInterface getController() {
		return controller;
	}

	public void setController(ControllerInterface controller) {
		this.controller = controller;
	}	
	
}
