/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Wizard.Configurator.Selector;

import java.util.EventListener;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface ValueChangedListener extends EventListener {
	public void valueChanged(int oldValue, int newValue);
}
