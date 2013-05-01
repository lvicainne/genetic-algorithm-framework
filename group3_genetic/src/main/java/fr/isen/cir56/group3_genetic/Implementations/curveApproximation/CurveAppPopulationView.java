/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.curveApproximation;

import fr.isen.cir56.group3_genetic.Configuration.UnexistingFactoryException;
import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.Event.Interfaces.PopulationChangedEvent;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Geometry.DoublePoint;
import fr.isen.cir56.group3_genetic.View.ChromosomeViewListener;
import fr.isen.cir56.group3_genetic.View.ViewInterface;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.DefaultConstructor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Wasp
 */
public class CurveAppPopulationView extends JPanel implements ViewInterface {

	private  final EventListenerList listeners = new EventListenerList();
	private PopulationInterface population;
	
	public  static int DEFAULT_X_MAX = 10;
	public static int DEFAULT_X_MIN = -10;
	public static int DEFAULT_Y_MAX = 10;
	public static int DEFAULT_Y_MIN = -10;
	public static String DEFAULT_ALGEBRIC_EXPRESSION = "x";
	
	private org.nfunk.jep.JEP parser;

	@DefaultConstructor
	public CurveAppPopulationView(){
		this(new CurveAppConfiguration(DEFAULT_ALGEBRIC_EXPRESSION, DEFAULT_X_MIN, DEFAULT_X_MAX, DEFAULT_Y_MIN, DEFAULT_Y_MAX));
	}
	
	public CurveAppPopulationView(CurveAppConfiguration configuration){
		
		this.parser = new org.nfunk.jep.JEP();
			
		this.parser.addStandardFunctions();
		this.parser.addStandardConstants();
		
		double min = configuration.getxMin();
		double max = configuration.getxMax();
		String algebricExpression = configuration.getAlgebricExpression();
		
		XYDataset dataset = createDataset(min, max, algebricExpression);
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);

		this.add(chartPanel);
		
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
	
	private XYDataset createDataset(double min, double max, String algebricExpression){
		XYSeries baseFunction = new XYSeries("Base function");
				
		this.parser.addVariable("x", min);
		this.parser.parseExpression(algebricExpression);
		
		for(double i = (double)min; i <= (double)max; i+=0.5){
			this.parser.addVariable("x", i);
			baseFunction.add(i, this.parser.getValue());
		}
		
		XYSeriesCollection col = new XYSeriesCollection();
		col.addSeries(baseFunction);
		
		return col;
		
	}
	
	private XYDataset createDataset(GeneticModel model){
		
		CurveAppConfiguration configuration = null;
		try {
			configuration = ((CurveAppChromosomeFactory) model.getMonitor().getConfiguration().getChromosomeFactory()).getCurveConfig();
		} catch (UnexistingFactoryException ex) {
			Logger.getLogger(CurveAppPopulationView.class.getName()).log(Level.SEVERE, null, ex);
		}
			
		// Ajout de la fonction de base dans le graphique
			
		String algebricExpression = configuration.getAlgebricExpression();
		int min = configuration.getxMin();
		int max = configuration.getxMax();
			
		XYSeries baseFunction = new XYSeries("Base function");
				
		this.parser.addVariable("x", min);
		this.parser.parseExpression(algebricExpression);
		
		for(double i = (double)min; i <= (double)max; i+=0.5){
			this.parser.addVariable("x", i);
			baseFunction.add(i, this.parser.getValue());
		}
		
		//penser à le mettre dans une classe à part.
		
		XYSeries points = new XYSeries("Points");
		
		// Ajout des points
		int size = model.getLastPopulation().size();
		for (int i = 0; i < size; i++) {
			//est-ce que ça vaut le coup de faire une vue pour chaque point ?
			
//			CurveAppChromosomeView point = new CurveAppDChromosomeView(points);
//			this.addChromosomeViewListener(point);
			
			//********** PROBLEME DE NULL AVEC LES LIGNES DE DESSUS ************//
			
			GeneInterface curveAppValue = model.getLastPopulation().getChromosome(i).getGene(0);
			DoublePoint point = (DoublePoint) curveAppValue.getData();
			points.add(point.x, point.y);
		}
			
		XYSeriesCollection col = new XYSeriesCollection();
		col.addSeries(baseFunction);
		col.addSeries(points);
		
		return col;
	}
	
	private JFreeChart createChart(final XYDataset dataset){
		        
		JFreeChart chart = ChartFactory.createXYLineChart(
                "CurveApp",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
         
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
		renderer.setSeriesLinesVisible(0,true);
		renderer.setSeriesLinesVisible(1,false);//on enlève les lignes pour la série des points
		renderer.setSeriesShapesVisible(1,true);//et on active les points
		plot.setRenderer(renderer);

		return chart;
	}
	
	@Override
	public void refresh(Event event) {
		if(event instanceof PopulationChangedEvent){
			
			GeneticModel model = (GeneticModel)event.getSource(); // on obtient un geneticModel
			
			XYDataset dataset = createDataset(model);
			JFreeChart chart = createChart(dataset);
			ChartPanel chartPanel = new ChartPanel(chart);
			
			this.removeAll();
			this.revalidate();
			
			this.add(chartPanel);
		
			this.firePopulationChanged(this.population);
			
		}
	}
	
}
