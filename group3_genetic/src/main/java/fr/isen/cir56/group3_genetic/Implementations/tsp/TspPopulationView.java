package fr.isen.cir56.group3_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.Event.Interfaces.PopulationChangedEvent;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.View.ChromosomeViewListener;
import fr.isen.cir56.group3_genetic.View.ViewInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.security.InvalidParameterException;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TspPopulationView extends JPanel implements ViewInterface<GeneticController> {
	private JPanel bottomPanel;
	private final int numberGraphs;
	private final EventListenerList listeners = new EventListenerList();
	private PopulationInterface population;

	public TspPopulationView(int numberGraphs) {
		super();
		if (numberGraphs < 1) {
			throw new InvalidParameterException("Minimum graphs number is 1 !");
		}

		this.numberGraphs = numberGraphs;

		this.setLayout(new GridLayout(2,1));
		GridLayout gridLayout = new GridLayout(1, numberGraphs - 1);
		this.bottomPanel = new JPanel(gridLayout);

		for (int i = 0; i < numberGraphs; i++) {
			TSPChromosomeView mySubView = new TSPChromosomeView();
			this.addChromosomeViewListener(mySubView);

			if (i == 0) {
				mySubView.setMaximumSize(new Dimension(10, 10));
				this.add(mySubView);
			} else {
				this.bottomPanel.add(mySubView);
			}

		}

		this.add(this.bottomPanel);
	}

	public int getNumberGraphs() {
		return numberGraphs;
	}

	@Override
	public void refresh(Event event) {
		if (event instanceof PopulationChangedEvent) {
			GeneticModel model = ((PopulationChangedEvent) event).getSource();
			this.population = model.getLastPopulation();
			this.firePopulationChanged(this.population);
		}

	}

	public final void addChromosomeViewListener(ChromosomeViewListener listener) {
		listeners.add(ChromosomeViewListener.class, listener);
	}

	public final void removeChromosomeViewListener(ChromosomeViewListener listener) {
		listeners.remove(ChromosomeViewListener.class, listener);
	}

	public final ChromosomeViewListener[] getChromosomeViewListener() {
		return listeners.getListeners(ChromosomeViewListener.class);
	}

	protected void firePopulationChanged(PopulationInterface population) {
		if (population == null) {
			//in case of reset

			for (ChromosomeViewListener listener : getChromosomeViewListener()) {
				listener.resetView();
			}

		} else {
			//population is not empty
			
			population.sortChromosomes();
			List<ChromosomeInterface> chromosomes = population.getChromosomes();

			int i = 0;
			for (ChromosomeViewListener listener : getChromosomeViewListener()) {
				listener.chromosomeChanged(chromosomes.get(i));
				i++;
			}
		}
	}
}
