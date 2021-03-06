package fr.isen.cir56.group3_genetic.View.Toolbar;

import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.Event.Interfaces.ModeChangedEvent;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.View.ViewInterface;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JToolBar;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class ToolbarView extends JToolBar implements ViewInterface<GeneticController> {

	private List<ToolbarButtonEnum> buttons = new LinkedList<>();

	public ToolbarView(GeneticController controller) {
		super("Toolbar", JToolBar.HORIZONTAL);
		for (ToolbarButtonEnum mode : ToolbarButtonEnum.values()) {
			ToolbarButtonView button = mode.getButtonView();
			button.addActionListener(mode.getActionListener(controller));
			this.buttons.add(mode);
			this.add(button);
		}
	}

	@Override
	public void refresh(Event event) {
		if (event instanceof ModeChangedEvent) {
			ModeChangedEvent myEvent = (ModeChangedEvent) event;
			GeneticModel model = myEvent.getSource();

			if (model.getMonitor().isSuspend()) {
				ToolbarButtonEnum.START.getButtonView().setEnabled(false);
				ToolbarButtonEnum.STEP.getButtonView().setEnabled(true);
				ToolbarButtonEnum.STOP.getButtonView().setEnabled(false);
				ToolbarButtonEnum.SUSPEND.getButtonView().setEnabled(false);
				ToolbarButtonEnum.RESUME.getButtonView().setEnabled(true);
				ToolbarButtonEnum.RESET.getButtonView().setEnabled(true);
				ToolbarButtonEnum.SAVE.getButtonView().setEnabled(true);
			} else if (model.getMonitor().isEnd()) {
				ToolbarButtonEnum.START.getButtonView().setEnabled(false);
				ToolbarButtonEnum.STEP.getButtonView().setEnabled(false);
				ToolbarButtonEnum.STOP.getButtonView().setEnabled(false);
				ToolbarButtonEnum.SUSPEND.getButtonView().setEnabled(false);
				ToolbarButtonEnum.RESUME.getButtonView().setEnabled(false);
				ToolbarButtonEnum.RESET.getButtonView().setEnabled(true);
				ToolbarButtonEnum.SAVE.getButtonView().setEnabled(true);
			} else if (model.getMonitor().isStopped()) {
				ToolbarButtonEnum.START.getButtonView().setEnabled(true);
				ToolbarButtonEnum.STEP.getButtonView().setEnabled(true);
				ToolbarButtonEnum.STOP.getButtonView().setEnabled(false);
				ToolbarButtonEnum.SUSPEND.getButtonView().setEnabled(false);
				ToolbarButtonEnum.RESUME.getButtonView().setEnabled(false);
				ToolbarButtonEnum.RESET.getButtonView().setEnabled(true);
				ToolbarButtonEnum.SAVE.getButtonView().setEnabled(true);
			} else {
				ToolbarButtonEnum.START.getButtonView().setEnabled(false);
				ToolbarButtonEnum.STEP.getButtonView().setEnabled(false);
				ToolbarButtonEnum.STOP.getButtonView().setEnabled(true);
				ToolbarButtonEnum.SUSPEND.getButtonView().setEnabled(true);
				ToolbarButtonEnum.RESUME.getButtonView().setEnabled(false);
				ToolbarButtonEnum.RESET.getButtonView().setEnabled(false);
				ToolbarButtonEnum.SAVE.getButtonView().setEnabled(false);
			}
		}
	}
}
