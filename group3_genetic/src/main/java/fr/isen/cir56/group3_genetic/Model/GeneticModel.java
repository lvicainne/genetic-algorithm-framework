package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Monitor.Monitor;
import fr.isen.cir56.group3_genetic.PopulationInterface;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class GeneticModel extends Model {
	private Monitor monitor;

	public GeneticModel(ConfigurationInterface configuration) {
		super();
		this.monitor = new Monitor(configuration, this);
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public PopulationInterface getLastPopulation() {
		PopulationInterface lastPopulation = ((Monitor) this.monitor).getBreeder().getLastPopulation();

		if (lastPopulation == null) {
			return this.getMonitor().getConfiguration().getInitialPopulation();
		} else {
			return lastPopulation;
		}
	}

}