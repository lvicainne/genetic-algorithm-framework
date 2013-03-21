/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View.Configurator.Selector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class SelectorEditorChangedEvent extends SelectorPanelChangedEvent implements ActionListener {
		
		public SelectorEditorChangedEvent(SelectorPanel controller) {
			super(controller);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int newValue = Integer.valueOf(this.getController().getEditor().getText());
				this.getController().setCurrentValue(newValue);
			} catch(NumberFormatException ex) {
				//If it's not a correct value, we put the old one !
				//so, we do nothing
			}
		}
	}