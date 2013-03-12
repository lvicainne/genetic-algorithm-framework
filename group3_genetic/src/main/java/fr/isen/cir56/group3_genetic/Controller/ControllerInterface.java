package fr.isen.cir56.group3_genetic.Controller;

import fr.isen.cir56.group3_genetic.Model.ModelInterface;

public interface ControllerInterface {
	
	void setModel(ModelInterface model);
	ModelInterface getModel();
}