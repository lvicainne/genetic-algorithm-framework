package fr.isen.cir56.group3_genetic.Wizard.Configurator.Selector;

import fr.isen.cir56.group3_genetic.Wizard.Configurator.JPanelChangedListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class SelectorSliderChangedListener extends JPanelChangedListener<SelectorPanel> implements ChangeListener {
	
		public SelectorSliderChangedListener(SelectorPanel controller) {
			super(controller);
		}

		@Override
		public void stateChanged(ChangeEvent e) {		
			this.getController().setCurrentValue(this.getController().getSlider().getValue());
		}	
}
