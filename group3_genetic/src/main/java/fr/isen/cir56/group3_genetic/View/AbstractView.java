package fr.isen.cir56.group3_genetic.View;

import fr.isen.cir56.group3_genetic.Controller.ControllerInterface;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public abstract class AbstractView<ControllerType extends ControllerInterface> implements ViewInterface<ControllerType> {
	protected ControllerType controller;
	
}
