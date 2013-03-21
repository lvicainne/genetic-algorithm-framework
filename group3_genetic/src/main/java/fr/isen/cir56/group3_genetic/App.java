package fr.isen.cir56.group3_genetic;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Constraint.NumberGenerationConstraint;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Implementations.tsp.TspChromosomeFactory;
import fr.isen.cir56.group3_genetic.Implementations.tsp.TspFitnessFunction;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.Operator.OrderedCrossoverOperator;
import fr.isen.cir56.group3_genetic.Operator.OrderedMutationOperator;
import fr.isen.cir56.group3_genetic.Selector.RankSelector;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.View.Configurator.ConfiguratorLauncher;
import fr.isen.cir56.group3_genetic.View.Graph.AbstractGraphView;
import fr.isen.cir56.group3_genetic.View.Graph.EvolutionPercentageGraph;
import fr.isen.cir56.group3_genetic.View.Graph.FitnessEvolutionGraph;
import fr.isen.cir56.group3_genetic.View.Graph.PopulationSizeGraph;
import fr.isen.cir56.group3_genetic.View.MainFrameView;
import fr.isen.cir56.group3_genetic.View.TerminalView;
import fr.isen.cir56.group3_genetic.View.Toolbar.ToolbarView;
import fr.isen.cir56.group3_genetic.View.ViewInterface;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class App {

	public static void main(String[] args) {
		MainFrameView graphicFrame = new MainFrameView();

		//DEBUT TEMP
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			/*Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
					30);
			Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
					80, 30);
			graph.insertEdge(parent, null, "Edge", v1, v2);
			
			*/
            // get stylesheet
            mxStylesheet stylesheet = graph.getStylesheet();

            // define stylename
            String styleName = "myImageStyle";

            // create image style
            Hashtable<String, Object> style = new Hashtable<>();
            style.put( mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
            style.put( mxConstants.STYLE_IMAGE, "/resources/clock.png");
            style.put( mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);

            // make new style available via stylename
            stylesheet.putCellStyle( styleName, style);

            // example 1: use style by stylename
            Object v1 = graph.insertVertex(parent, null, "v1", 100, 100, 80, 30, styleName);

            // example 2: use style by style definition
            Object v2 = graph.insertVertex(parent, null, "v2", 200, 200, 80, 30, "shape=image;image=/resources/clock.png");

            // create edge
            graph.insertEdge(parent, null, "", v1, v2);
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		//FIN TEMP
		
		Configuration configuration = new Configuration();
		TspChromosomeFactory chromosomeFactory = new TspChromosomeFactory(configuration);
		AbstractFitnessFunction fitnessFunction = new TspFitnessFunction(chromosomeFactory);
		ConstraintInterface constraint = new NumberGenerationConstraint(100);
		SelectorInterface selector = new RankSelector();

		try {
			configuration.addConstraint(constraint);
			configuration.addOperator(new OrderedCrossoverOperator(1.0F));
			configuration.addOperator(new OrderedMutationOperator(1.0F));
			configuration.addSelector(selector);
			configuration.setFitnessFunction(fitnessFunction);
			configuration.setChromosomeFactory(chromosomeFactory);
			configuration.setPopulationSize(5);

		} catch (InvalidProbabilityValueException | InvalidConfigurationException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}

		ViewInterface terminalView = new TerminalView(System.err);

		AbstractGraphView graphFitness = new FitnessEvolutionGraph();
		AbstractGraphView graphPopulationSize = new PopulationSizeGraph();
		AbstractGraphView graphEvolutionPercentage = new EvolutionPercentageGraph();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.add(graphFitness.getJPanel());
		panel.add(graphPopulationSize.getJPanel());
		panel.add(graphEvolutionPercentage.getJPanel());

		//DEBUT TEMP
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		panel.add(graphComponent);

		JPanel configurateurLauncher = new ConfiguratorLauncher();
		panel.add(configurateurLauncher);
		//FIN TEMP
		
		graphicFrame.add(panel);

		GeneticModel model = new GeneticModel(configuration);
		GeneticController controller = new GeneticController(model);

		Container content = graphicFrame.getContentPane();
		ToolbarView toolbar = new ToolbarView(controller);
		content.add(toolbar.getJToolbar(), BorderLayout.NORTH);
		graphicFrame.componentsAdded();

		model.addView(terminalView);
		model.addView(toolbar);
		model.addView(graphFitness);
		model.addView(graphPopulationSize);
		model.addView(graphEvolutionPercentage);

	}
}
