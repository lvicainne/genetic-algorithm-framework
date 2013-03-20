/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View;

import fr.isen.cir56.group3_genetic.Controller.ControllerInterface;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class MainFrameView extends JFrame implements ViewInterface {
	private ControllerInterface controller;

	public MainFrameView() {
		super();
		this.setTitle("Genetic Algorithm");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			//We don't care if the Look and Feel is not good
			//But we like it !
		}
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void refresh(Event event) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void setController(ControllerInterface controller) {
		this.controller = controller;
	}

	@Override
	public ControllerInterface getController() {
		return this.controller;
	}
	
	public void componentsAdded() {
		this.setVisible(true);
		
		//adapt the size to the best onr automatically
		this.pack();
	}
}
