/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View.Toolbar;

import fr.isen.cir56.group3_genetic.App;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public enum ToolbarButtonEnum {
	
	START("Start", "View/icons/start.png", KeyEvent.VK_S, StartMouseListener.class),
	STOP("Stop", "View/icons/stop.png", KeyEvent.VK_E, StopMouseListener.class),
	SUSPEND("Suspend", "View/icons/suspend.png", KeyEvent.VK_P, StartMouseListener.class),
	RESUME("Resume", "View/icons/resume.png", KeyEvent.VK_R, StartMouseListener.class);
	
	private String text;
	private String filename;
	private ImageIcon icon;
	private int key;
	private ToolbarButtonView buttonView;
	private Class listenerClass;

	ToolbarButtonEnum(String text, String iconFilename, int key, Class listenerClass) {
		this.text = text;
		this.filename = iconFilename;
		
		if(!iconFilename.equals("")) {
			this.icon = new ImageIcon(
				((new ImageIcon((new File("").getAbsolutePath()+"/src/main/java/fr/isen/cir56/group3_genetic/"+iconFilename)).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH))
				);
			System.out.println(new File("").getAbsolutePath()+"/src/main/java/fr/isen/cir56/group3_genetic/"+iconFilename);
			System.out.println(App.class.getResource(iconFilename));
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
		if(this.buttonView == null) {
			this.buttonView = new ToolbarButtonView(this);
		}
		return buttonView;
	}
	
	public MouseListener getMouseListener(GeneticController controller) {
		try {
			Constructor c = listenerClass.getConstructor(GeneticController.class);
			return (MouseListener) c.newInstance(controller);
		} catch (NoSuchMethodException ex) {
			Logger.getLogger(ToolbarButtonEnum.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SecurityException ex) {
			Logger.getLogger(ToolbarButtonEnum.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(ToolbarButtonEnum.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ToolbarButtonEnum.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(ToolbarButtonEnum.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(ToolbarButtonEnum.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	private static class StartMouseListener extends ClickMouseListener {
		private final GeneticController controller;

		public StartMouseListener(GeneticController controller) {
			this.controller = controller;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			this.controller.start();
		}
	}

	private static class StopMouseListener extends ClickMouseListener {
		private final GeneticController controller;

		public StopMouseListener(GeneticController controller) {
			this.controller = controller;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			this.controller.stop();
		}
	}
}
