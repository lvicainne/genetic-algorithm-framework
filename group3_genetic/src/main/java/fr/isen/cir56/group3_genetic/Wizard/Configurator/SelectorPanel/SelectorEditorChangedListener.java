/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Wizard.Configurator.SelectorPanel;

import fr.isen.cir56.group3_genetic.Wizard.Configurator.JPanelChangedListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class SelectorEditorChangedListener extends JPanelChangedListener<SelectorPanel> implements ActionListener {
		
		public SelectorEditorChangedListener(SelectorPanel controller) {
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