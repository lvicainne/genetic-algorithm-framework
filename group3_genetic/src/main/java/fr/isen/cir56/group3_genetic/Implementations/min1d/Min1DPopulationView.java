/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.Event.Interfaces.PopulationChangedEvent;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.View.ChromosomeViewListener;
import fr.isen.cir56.group3_genetic.View.ViewInterface;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author Wasp
 */
public class Min1DPopulationView implements ViewInterface<GeneticController>{
	
	private JPanel panel;
	private  final EventListenerList listeners = new EventListenerList();
	private PopulationInterface population;
	private JFreeChart chart;
	private DefaultXYDataset dataset;
	
	private org.nfunk.jep.JEP parser;

	public Min1DPopulationView(){
		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());
		
		this.dataset = new DefaultXYDataset();
		
		this.chart = ChartFactory.createXYLineChart("Studied function","X" ,"Y", this.dataset, PlotOrientation.VERTICAL, true, true, true);
	ChartPanel chartPanel = new ChartPanel(this.chart);
	
		this.parser = new org.nfunk.jep.JEP();
			
		this.parser.addStandardFunctions();
		this.parser.addStandardConstants();
		
		this.panel.add(chartPanel);
	}
	
	@Override
	public void refresh(Event event) {
		System.out.println("Salut 1");
		if(event instanceof PopulationChangedEvent){
			System.out.println("Salut 2");
			GeneticModel model = (GeneticModel)event.getSource(); // on obtient un geneticModel
			
			Min1DConfiguration configuration = (Min1DConfiguration)model.getMonitor().getConfiguration();
			
			String algebricExpression = configuration.getAlgebricExpression();
			int min = configuration.getMin();
			int max = configuration.getMax();
			
			XYSeries baseFunction = new XYSeries("Base function");
				
			this.parser.addVariable("x", min);
			this.parser.parseExpression(algebricExpression);
			
			for(double i = (double)min; i <= (double)max; i+=0.5){
				this.parser.addVariable("x", i);
				baseFunction.add(i, this.parser.getValue());
			}
			
			this.dataset.removeSeries("");
			this.dataset.addSeries("", baseFunction.toArray());
			
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

}
