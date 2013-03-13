/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View.Toolbar;

import fr.isen.cir56.group3_genetic.App;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public enum ToolbarButtonEnum {
	
	START("Start", "View/icons/start.png", KeyEvent.VK_S),
	STOP("Stop", "View/icons/stop.png", KeyEvent.VK_E),
	SUSPEND("Suspend", "View/icons/suspend.png", KeyEvent.VK_P),
	RESUME("Resume", "View/icons/resume.png", KeyEvent.VK_R);
	
	private String text;
	private String filename;
	private ImageIcon icon;
	private int key;
	private ToolbarButtonView buttonView;

	ToolbarButtonEnum(String text, String iconFilename, int key) {
		this.text = text;
		this.filename = iconFilename;
		
		if(!iconFilename.equals("")) {
			this.icon = new ImageIcon(
					
				(((new ImageIcon(App.class.getResource(iconFilename))).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH))
				);

		}
		this.key = key;
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
}
