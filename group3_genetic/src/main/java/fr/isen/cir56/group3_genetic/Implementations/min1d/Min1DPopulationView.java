package fr.isen.cir56.group3_genetic.Implementations.min1d;

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
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.List;
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
public class Min1DPopulationView extends JPanel implements ViewInterface{
	
	private  final EventListenerList listeners = new EventListenerList();
	public  static int DEFAULT_MAX = 10;
	public static int DEFAULT_MIN = -10;
	public static String DEFAULT_ALGEBRIC_EXPRESSION = "x";
	
	private org.nfunk.jep.JEP parser;

	@DefaultConstructor
	public Min1DPopulationView(){
		this(new Min1DConfiguration(DEFAULT_ALGEBRIC_EXPRESSION, DEFAULT_MIN, DEFAULT_MAX));
	}
	
	public Min1DPopulationView(Min1DConfiguration configuration){
		
		this.parser = new org.nfunk.jep.JEP();
			
		this.parser.addStandardFunctions();
		this.parser.addStandardConstants();
		
		double min = configuration.getMin();
		double max = configuration.getMax();
		String algebricExpression = configuration.getAlgebricExpression();
		
		XYDataset dataset = createDataset(min, max, algebricExpression);
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);

		this.add(chartPanel);
		
	}
	
	@Override
	public void refresh(Event event) {
		if(event instanceof PopulationChangedEvent){
			
			GeneticModel model = ((PopulationChangedEvent) event).getSource();
			PopulationInterface population = model.getLastPopulation();
			
			XYDataset dataset = createDataset(model);
			JFreeChart chart = createChart(dataset);
			ChartPanel chartPanel = new ChartPanel(chart);
			
			this.removeAll();
			this.revalidate();
			
			this.add(chartPanel);
		
			this.firePopulationChanged(population);
			
		}
		
		
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
			for (ChromosomeViewListener listener : getChromosomeViewListener())			   {
				listener.chromosomeChanged(chromosomes.get(i));
				i++;
			}
		}
	}
	
	private XYDataset createDataset(double min, double max, String algebricExpression){
		XYSeries baseFunction = new XYSeries("Base function");
				
		this.parser.addVariable("x", min);
		this.parser.parseExpression(algebricExpression);
		
		for(double i = (double)min; i <= (double)max; i+=0.1){
			this.parser.addVariable("x", i);
			baseFunction.add(i, this.parser.getValue());
		}
		
		XYSeriesCollection col = new XYSeriesCollection();
		col.addSeries(baseFunction);
		
		return col;
		
	}
	
	private XYDataset createDataset(GeneticModel model){
		
		Min1DConfiguration configuration = null;
		try {
			configuration = ((Min1DChromosomeFactory) model.getMonitor().getConfiguration().getChromosomeFactory()).getMin1Dconfig();
		} catch (UnexistingFactoryException ex) {
		}
		
			
		// Ajout de la fonction de base dans le graphique
			
		String algebricExpression = configuration.getAlgebricExpression();
		int min = configuration.getMin();
		int max = configuration.getMax();
			
		XYSeries baseFunction = new XYSeries("Base function");
				
		this.parser.addVariable("x", min);
		this.parser.parseExpression(algebricExpression);
		
		for(double i = (double)min; i <= (double)max; i+=0.1){
			this.parser.addVariable("x", i);
			baseFunction.add(i, this.parser.getValue());
		}
		
		//penser à le mettre dans une classe à part.
		
		XYSeries points = new XYSeries("Points");
		
		// Ajout des points
		int size = model.getLastPopulation().size();
		for (int i = 0; i < size; i++) {
			// est ce que ça vaut le coup de faire une vue pour chaque point ?
			
//			Min1DChromosomeView point = new Min1DChromosomeView(points);
//			this.addChromosomeViewListener(point);
			
			//********** PROBLEME DE NULL AVEC LES LIGNES DE DESSUS ************//
			
			GeneInterface min1DValue = model.getLastPopulation().getChromosome(i).getGene(0);
			DoublePoint point = (DoublePoint) min1DValue.getData();
			points.add(point.x, point.y);
		}
		
		
		//ajout du meilleur point
		XYSeries bestPoint = new XYSeries("Best Point");
		
		GeneInterface bestPointValue = model.getLastPopulation().getBestChromosome().getGene(0);
		DoublePoint bestDoublePoint = (DoublePoint) bestPointValue.getData();
		bestPoint.add(bestDoublePoint.x, bestDoublePoint.y);
					
		XYSeriesCollection col = new XYSeriesCollection();
		col.addSeries(baseFunction);
		col.addSeries(bestPoint);
		col.addSeries(points);
		
		return col;
	}
	
	private JFreeChart createChart(final XYDataset dataset){
		        
		JFreeChart chart = ChartFactory.createXYLineChart(
                "Min1D",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
         
		final XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
		renderer.setSeriesLinesVisible(0,true);
		renderer.setSeriesLinesVisible(1,false);//on enlève les lignes pour la série des points
		renderer.setSeriesShapesVisible(1,true);//et on active les points
		renderer.setSeriesPaint(1,Color.red);
		renderer.setSeriesLinesVisible(2,false);
		renderer.setSeriesShapesVisible(2,true);
		renderer.setSeriesShape(2, new Ellipse2D.Float(-2.5f, -2.5f, 5.0f, 5.0f));
		renderer.setSeriesPaint(2, Color.YELLOW);
		plot.setRenderer(renderer);

		return chart;
	}

}
