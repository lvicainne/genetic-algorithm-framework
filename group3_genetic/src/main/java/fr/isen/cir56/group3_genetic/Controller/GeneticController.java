package fr.isen.cir56.group3_genetic.Controller;

import fr.isen.cir56.group3_genetic.GeneticCommandsInterface;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.SaveCommandsInterface;
import fr.isen.cir56.group3_genetic.View.ViewInterface;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class GeneticController extends AbstractController<GeneticModel> implements GeneticCommandsInterface, SaveCommandsInterface {
	private JPanel specialView;
	private ViewInterface specialSubView;

	public GeneticController(GeneticModel model) {
		super(model);
		specialView = new JPanel();
	}

	@Override
	public void stop() {
		getModel().getMonitor().stop();
	}

	@Override
	public void start() {
		this.getModel().getMonitor().setSourcePopulation(this.getModel().getMonitor().getConfiguration().getInitialPopulation());
		this.getModel().getMonitor().start();
	}
	
	@Override
	public void stepByStep() {
		if(this.getModel().getMonitor().getPopulationComputed() == null) {
			this.getModel().getMonitor().setSourcePopulation(this.getModel().getMonitor().getConfiguration().getInitialPopulation());
		}
		this.getModel().getMonitor().stepByStep();
	}

	@Override
	public void reset() {
		this.getModel().getMonitor().reset();
		
		//Update the new data to the monitor
		this.getModel().getMonitor().setSourcePopulation(this.getModel().getMonitor().getConfiguration().getInitialPopulation());
	}

	@Override
	public void suspend() {
		this.getModel().getMonitor().suspend();
	}

	@Override
	public void resume() {
		this.getModel().getMonitor().resume();
	}
	
	@Override
	public void save() {
		this.getModel().save();
	}
	
	@Override
	public void restore() {
		this.getModel().restore();
	}

	/**
	 * The special view is a graphic view for watching the current population
	 * It is a view made by the user of the lib
	 * @param view the JPanel view
	 * @param view2 the same view, but with the ViewInterface caracteristics
	 */
	public void setSpecialView(JPanel view, ViewInterface view2) {
		if(specialSubView != null) {
			this.getModel().removeView(specialSubView);
		}
		specialSubView = view2;
		this.specialView.setLayout(new GridLayout(1,1));
		this.specialView.removeAll();
		this.specialView.add(view);
		this.specialView.validate();
		this.specialView.repaint();
		this.getModel().addView(view2);
	}

	/**
	 * Return the JPanel wich contain the current view
	 * It is useful for the main frame panel
	 * @return 
	 */
	public JPanel getContainerSpecialView() {
		return specialView;
	}
	
	

}