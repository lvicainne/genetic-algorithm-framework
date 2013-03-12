/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Controller;

import fr.isen.cir56.group3_genetic.Model.ModelInterface;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class AbstractController implements ControllerInterface {
	private ModelInterface model;
	
	public AbstractController(ModelInterface model) {
		this.model = model;
	}

	public void setModel(ModelInterface model) {
		this.model = model;
	}

	public ModelInterface getModel() {
		return this.model;
	}
	
}
