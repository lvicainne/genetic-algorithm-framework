/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View.Toolbar;

import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.View.AbstractGeneticView;
import fr.isen.cir56.group3_genetic.View.Event;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JToolBar;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ToolbarView extends AbstractGeneticView {
	private JToolBar toolbar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
	private List<ToolbarButtonEnum> buttons = new LinkedList<>();
	
	public ToolbarView(GeneticController controller) {
		for(ToolbarButtonEnum mode : ToolbarButtonEnum.values()) {
			ToolbarButtonView button = mode.getButtonView();
			button.addMouseListener(mode.getMouseListener(controller));
			this.buttons.add(mode);
			this.toolbar.add(button);
		}
	}

	@Override
	public void refresh(Event event) {
		GeneticModel model = (GeneticModel) event.getSource();
		if(model.getMonitor().isSuspend()) {
			ToolbarButtonEnum.SUSPEND.getButtonView().setEnabled(false);
			ToolbarButtonEnum.RESUME.getButtonView().setEnabled(true);
		}
		
		if(model.getMonitor().isStopped()) {
			ToolbarButtonEnum.STOP.getButtonView().setEnabled(false);
			ToolbarButtonEnum.START.getButtonView().setEnabled(true);
		}
		
		
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public JToolBar getJToolbar() {
		return this.toolbar;
	}
	
}
