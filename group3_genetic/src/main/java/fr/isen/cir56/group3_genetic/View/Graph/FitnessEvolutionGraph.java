package fr.isen.cir56.group3_genetic.View.Graph;

import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.View.AbstractGeneticView;
import fr.isen.cir56.group3_genetic.View.Event;
import java.util.Calendar;
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
	public void refreshModel(GeneticModel model) {
					List<PopulationInterface> history = model.getMonitor().getBreeder().getPopulationsHistory();
			
			XYSeries series = new XYSeries("");
			int i = 0;
			for (PopulationInterface populationInterface : history) {
				series.add(i, populationInterface.getBetterChromosome().getFitnessValue());
				i++;
			}
			
			Calendar calendar = Calendar.getInstance();
			this.getXyDataset().addSeries("Data à "
					+ calendar.get(Calendar.HOUR_OF_DAY)
					+ ":" + calendar.get(Calendar.MINUTE)
					+ ":" + calendar.get(Calendar.SECOND), series.toArray());
	}

	@Override
	public JFreeChart createChart() {
		return ChartFactory.createXYLineChart("Iterations", 
				"Time", "Fitness Value", this.getXyDataset(), PlotOrientation.VERTICAL, true, true, false);
	}

	
	
}
