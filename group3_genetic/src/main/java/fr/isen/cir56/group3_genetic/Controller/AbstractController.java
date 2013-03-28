package fr.isen.cir56.group3_genetic.Controller;

import fr.isen.cir56.group3_genetic.Model.ModelInterface;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class AbstractController<ModelType extends ModelInterface> implements ControllerInterface<ModelType> {
	private ModelType model;
	
	public AbstractController(ModelType model) {
		this.model = model;
	}

	@Override
	public void setModel(ModelType model) {
		this.model = model;
	}

	@Override
	public ModelType getModel() {
		return this.model;
	}
	
}
