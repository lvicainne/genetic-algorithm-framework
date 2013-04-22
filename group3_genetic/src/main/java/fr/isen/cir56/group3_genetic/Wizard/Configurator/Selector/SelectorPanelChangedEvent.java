/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Wizard.Configurator.Selector;

import fr.isen.cir56.group3_genetic.Wizard.Configurator.JPanelChangedEvent;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class SelectorPanelChangedEvent extends JPanelChangedEvent {
	
	public SelectorPanelChangedEvent(SelectorPanel controller) {
		super(controller);
	}
	
	@Override
	public SelectorPanel getController() {
		return (SelectorPanel) this.controller;
	}
}
