package fr.isen.cir56.group3_genetic.View.Graph;

import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.View.ViewInterface;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public abstract class AbstractGraphView implements ViewInterface {
	private JFreeChart chart;
	private DefaultXYDataset xyDataset = new DefaultXYDataset();

	public AbstractGraphView() {
		this.chart = this.createChart();
	}

	public JFreeChart getChart() {
		return this.chart;
	}

	public JPanel getJPanel() {
		return new ChartPanel(this.chart);
	}

	public DefaultXYDataset getXyDataset() {
		return xyDataset;
	}

	@Override
	public void refresh(Event event) {
		Object source = event.getSource();
		if (source instanceof GeneticModel) {
			GeneticModel model = (GeneticModel) source;

			this.refreshModel(model);
		}
	}
	
	/**
	 * 
	 * @return JFreeCHart 
	 */
	public abstract JFreeChart createChart();
	
	/**
	 * Called when a refresh occurs
	 * @param model GeneticModel
	 */
	public abstract void refreshModel(GeneticModel model);
}
