package fr.isen.cir56.group3_genetic.View.Graph;

import fr.isen.cir56.group3_genetic.Model.GeneticModel;
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
public class PopulationSizeGraph extends AbstractGraphView {

	@Override
	public void refreshModel(GeneticModel model) {
		List<PopulationInterface> history = model.getMonitor().getBreeder().getPopulationsHistory();

		XYSeries series = new XYSeries("");
		
		int i = 0;
		for (PopulationInterface populationInterface : history) {
			series.add(i, populationInterface.size());
			i++;
		}

		this.getXyDataset().removeSeries("");
		this.getXyDataset().addSeries("", series.toArray());
	}

	@Override
	public JFreeChart createChart() {
		return ChartFactory.createXYLineChart("Iterations",
				"Time", "Size of the population", this.getXyDataset(), PlotOrientation.VERTICAL, true, true, false);
	}
}
