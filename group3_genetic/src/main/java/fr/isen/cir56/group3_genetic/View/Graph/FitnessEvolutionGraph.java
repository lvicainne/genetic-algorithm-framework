package fr.isen.cir56.group3_genetic.View.Graph;

import fr.isen.cir56.group3_genetic.Analyzer.Analyzer;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.Monitor.NonEndedProcessingException;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class FitnessEvolutionGraph extends AbstractGraphView {

	@Override
	public JFreeChart createChart() {
		return ChartFactory.createXYLineChart("Fitness Value",
				"Iterations", "Fitness Value", this.getXyDataset(), PlotOrientation.VERTICAL, true, true, false);
	}

	@Override
	public void refreshModel(GeneticModel model) {
		List<PopulationInterface> history = model.getMonitor().getBreeder().getPopulationsHistory();
		Analyzer analyzer = null;
		try {
			analyzer = model.getMonitor().getAnalyzer();
		} catch (NonEndedProcessingException ex) {
			//The analyzer is not present. So we can't print new datas !
			return;
		}
		
		XYSeries series = new XYSeries("");
		
		int i = 0;
		for (PopulationInterface populationInterface : history) {
			series.add(i, populationInterface.getBetterChromosome().getFitnessValue());
			i++;
		}

		this.getXyDataset().removeSeries("");
		this.getXyDataset().addSeries("", series.toArray());
	}

}
