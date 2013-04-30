package fr.isen.cir56.group3_genetic.Model;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.Genotype.Chromosome;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Monitor.Monitor;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.SaveCommandsInterface;
import fr.isen.cir56.group3_genetic.Utils.XMLTools.XMLTools;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class GeneticModel extends Model implements SaveCommandsInterface {

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

	/**
	 * Save the population in XML
	 */
	@Override
	public void save() {

		PopulationInterface pop = this.getMonitor().getBreeder().getLastPopulation();
		if (pop != null) {
			Chromosome chromosome = (Chromosome) pop.getBestChromosome();

			//Create a file chooser
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					File file = fc.getSelectedFile();
					String filename = file.getAbsolutePath() + ".xml";
					XMLTools.encodeToFile(chromosome, filename);
				} catch (IOException ex) {
					this.refreshViews(new Event(this, ex));
				}
			}
		}
	}

	/**
	 * Restore the population from XLM
	 */
	@Override
	public void restore() {
		//Create a file chooser
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fc.getSelectedFile();

				ChromosomeInterface myChromosome = (ChromosomeInterface) XMLTools.decodeFromFile(file.getAbsolutePath());

				//myChromosome.getGene(0)
				System.out.println(myChromosome.getGene(0).getClass().getCanonicalName());
				//@TODO open a view POpulation panel from here.
				
			} catch (IOException ex) {
				this.refreshViews(new Event(this, ex));
			}
		}
	}
}