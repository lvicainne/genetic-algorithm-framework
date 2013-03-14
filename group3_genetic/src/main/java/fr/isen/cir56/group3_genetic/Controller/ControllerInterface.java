package fr.isen.cir56.group3_genetic.Controller;

import fr.isen.cir56.group3_genetic.Model.ModelInterface;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface ControllerInterface {
	
	void setModel(ModelInterface model);
	ModelInterface getModel();
}