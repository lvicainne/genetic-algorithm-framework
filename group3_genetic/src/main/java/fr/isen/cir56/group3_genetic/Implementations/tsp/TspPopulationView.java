package fr.isen.cir56.group3_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.Event.PopulationChangedEvent;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.View.AbstractView;
import fr.isen.cir56.group3_genetic.View.ChromosomeViewListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.security.InvalidParameterException;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TspPopulationView extends AbstractView {

	private JPanel panel;
	private JPanel bottomPanel;
	private final int numberGraphs;
	private final EventListenerList listeners = new EventListenerList();
	private PopulationInterface population;

	public TspPopulationView(int numberGraphs) {
		if (numberGraphs < 1) {
			throw new InvalidParameterException("Minimum graphs number is 1 !");
		}

		this.numberGraphs = numberGraphs;

		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());

		this.bottomPanel = new JPanel();
		this.bottomPanel = new JPanel(new GridLayout(1, numberGraphs - 1));

		for (int i = 0; i < numberGraphs; i++) {
			TSPChromosomeView mySubView = new TSPChromosomeView();
			this.addChromosomeViewListener(mySubView);

			if (i == 0) {
				this.panel.add(mySubView.getJPanel());
			} else {
				this.bottomPanel.add(mySubView.getJPanel());
			}

		}

		this.panel.add(this.bottomPanel, BorderLayout.SOUTH);
	}

	public int getNumberGraphs() {
		return numberGraphs;
	}

	@Override
	public void refresh(Event event) {
		if (event instanceof PopulationChangedEvent) {
			GeneticModel model = ((PopulationChangedEvent) event).getSender();
			this.population = model.getLastPopulation();
			this.firePopulationChanged(this.population);
		}

		GeneticModel model = (GeneticModel) event.getSource();
		PopulationInterface lastPopulation = model.getLastPopulation();
		if (this.population != lastPopulation) {
			this.population = lastPopulation;
			this.firePopulationChanged(this.population);
		}
	}

	public JPanel getJPanel() {
		return this.panel;
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
		population.sortChromosomes();
		List<ChromosomeInterface> chromosomes = population.getChromosomes();

		int i = 0;
		for (ChromosomeViewListener listener : getChromosomeViewListener()) {
			listener.chromosomeChanged(chromosomes.get(i));
			i++;
		}
	}
}
