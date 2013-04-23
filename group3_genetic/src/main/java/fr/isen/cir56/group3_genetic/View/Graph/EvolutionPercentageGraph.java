package fr.isen.cir56.group3_genetic.View.Graph;

import fr.isen.cir56.group3_genetic.Analyzer.Analyzer;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.Monitor.NonEndedGenerationException;
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
public class EvolutionPercentageGraph extends AbstractGraphView {

	@Override
	public void refreshModel(GeneticModel model) {
		Analyzer analyzer = null;
		try {
			analyzer = model.getMonitor().getAnalyzer();
		} catch (NonEndedGenerationException ex) {
			//The analyzer is not present. So we can't print new datas !
			return;
		}
		
		XYSeries series = new XYSeries("A");
		int size = analyzer.getNumberGenerations();
		
		for (int j = 1; j < size; j++) {
			series.add(j, analyzer.getPercentageInvolving(j));
			
		}

		try {
			this.getXyDataset().removeSeries("A");
			this.getXyDataset().addSeries("", series.toArray());
		} catch(java.lang.IndexOutOfBoundsException|java.lang.IllegalArgumentException ex) {
			//Bug from JfreeChartLibrary. Wa have to pay for checking source code...
			System.err.println("Erreur de JfreeChart...");
		}
		
	}

	@Override
	public JFreeChart createChart() {
		return ChartFactory.createXYLineChart("Fitness value evolution (Derivative)",
				"Iterations", "% Evolution", this.getXyDataset(), PlotOrientation.VERTICAL, true, true, false);
	}
}
