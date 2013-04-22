/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View.Toolbar;

import fr.isen.cir56.group3_genetic.App;
import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.Wizard.DialogConfigurator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public enum ToolbarButtonEnum {

	CONFIGURE("Configure", "View/icons/settings.png", KeyEvent.VK_C, OpenConfigureView.class),
	START("Start", "View/icons/start.png", KeyEvent.VK_S, StartMouseListener.class),
	STEP("Step-by-Step", "View/icons/step.png", KeyEvent.VK_S, StepMouseListener.class),
	STOP("Stop", "View/icons/stop.png", KeyEvent.VK_E, StopMouseListener.class),
	SUSPEND("Suspend", "View/icons/suspend.png", KeyEvent.VK_P, SuspendMouseListener.class),
	RESUME("Resume", "View/icons/resume.png", KeyEvent.VK_R, ResumeMouseListener.class),
	RESET("Reset", "View/icons/reset.png", KeyEvent.VK_R, ResetMouseListener.class),
	EDIT("Edit", "View/icons/reset.png", KeyEvent.VK_E, EditMouseListener.class);
	
	private String text;
	private String filename;
	private ImageIcon icon;
	private int key;
	private ToolbarButtonView buttonView;
	private Class listenerClass;

	ToolbarButtonEnum(String text, String iconFilename, int key, Class listenerClass) {
		this.text = text;
		this.filename = iconFilename;

		if (!iconFilename.equals("")) {
			this.icon = new ImageIcon(
					((new ImageIcon(App.class.getResource(iconFilename)).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		}
		this.key = key;
		this.listenerClass = listenerClass;
	}

	public String getText() {
		return this.text;
	}

	public String getFilename() {
		return this.filename;
	}

	public int getKeyEvent() {
		return key;
	}

	public static ToolbarButtonEnum fromString(String text) {
		if (text != null) {
			for (ToolbarButtonEnum b : ToolbarButtonEnum.values()) {
				if (text.equalsIgnoreCase(b.text)) {
					return b;
				}
			}
		}
		return null;
	}

	public final ImageIcon getIcon() {
		return this.icon;
	}

	public ToolbarButtonView getButtonView() {
		if (this.buttonView == null) {
			this.buttonView = new ToolbarButtonView(this);
		}
		return buttonView;
	}

	public ActionListener getActionListener(GeneticController controller) {
		try {
			Constructor c = listenerClass.getConstructor(GeneticController.class);
			return (ActionListener) c.newInstance(controller);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			Logger.getLogger(ToolbarButtonEnum.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	private static class StartMouseListener implements ActionListener {

		private final GeneticController controller;

		public StartMouseListener(GeneticController controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.start();
		}
	}

	private static class StepMouseListener implements ActionListener {

		private final GeneticController controller;

		public StepMouseListener(GeneticController controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.stepByStep();
		}
	}

	private static class StopMouseListener implements ActionListener {

		private final GeneticController controller;

		public StopMouseListener(GeneticController controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.stop();
		}
	}

	private static class SuspendMouseListener implements ActionListener {

		private final GeneticController controller;

		public SuspendMouseListener(GeneticController controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.suspend();
		}
	}

	private static class ResumeMouseListener implements ActionListener {

		private final GeneticController controller;

		public ResumeMouseListener(GeneticController controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.resume();
		}
	}

	private static class ResetMouseListener implements ActionListener {

		private final GeneticController controller;

		public ResetMouseListener(GeneticController controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.reset();
		}
	}

	private static class OpenConfigureView implements ActionListener {

		private final GeneticController controller;

		public OpenConfigureView(GeneticController controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			DialogConfigurator dialog = new DialogConfigurator(new JFrame());
			dialog.pack();

			dialog.setVisible(true);

			ConfigurationInterface configuration = dialog.getConfiguration();
			
			if(configuration != null) {
				//Configuration may be null if we cancel the dialog
				controller.setModel(new GeneticModel(configuration));
				controller.reset();
			}

		}
	}
	
	private static class EditMouseListener implements ActionListener {

		private final GeneticController controller;

		public EditMouseListener(GeneticController controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			DialogConfigurator dialog = new DialogConfigurator(new JFrame());
			dialog.pack();

			dialog.setVisible(true);

			ConfigurationInterface configuration = dialog.getConfiguration();
			
			if(configuration != null) {
				//Configuration may be null if we cancel the dialog
				controller.setModel(new GeneticModel(configuration));
				controller.reset();
			}

		}
	}
}
