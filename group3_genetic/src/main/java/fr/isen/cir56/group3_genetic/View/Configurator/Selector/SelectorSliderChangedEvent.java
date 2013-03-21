/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View.Configurator.Selector;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class SelectorSliderChangedEvent extends SelectorPanelChangedEvent implements ChangeListener {
	
		public SelectorSliderChangedEvent(SelectorPanel controller) {
			super(controller);
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			if(!(controller instanceof SelectorPanel)) {
				throw new UnsupportedOperationException("SelectorPanel required as controller.");
			}
			
			this.getController().setCurrentValue(this.getController().getSlider().getValue());
		}	
}
