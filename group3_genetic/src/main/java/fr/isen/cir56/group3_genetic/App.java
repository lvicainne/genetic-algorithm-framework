package fr.isen.cir56.group3_genetic;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.NumberGenerationConstraint;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Event.Event;
import fr.isen.cir56.group3_genetic.Event.InitialPopulationLoadEvent;
import fr.isen.cir56.group3_genetic.Event.ResetPopulationEvent;
import fr.isen.cir56.group3_genetic.Genotype.AbstractChromosomeFactory;
import fr.isen.cir56.group3_genetic.Implementations.min1d.Min1DChromosomeFactory;
import fr.isen.cir56.group3_genetic.Implementations.min1d.Min1DConfiguration;
import fr.isen.cir56.group3_genetic.Implementations.min1d.Min1DCrossoverOperator;
import fr.isen.cir56.group3_genetic.Implementations.min1d.Min1DPopulationView;
import fr.isen.cir56.group3_genetic.Implementations.tsp.TspChromosomeFactory;
import fr.isen.cir56.group3_genetic.Implementations.tsp.TspPopulationView;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.Operator.OrderedCrossoverOperator;
import fr.isen.cir56.group3_genetic.Operator.OrderedMutationOperator;
import fr.isen.cir56.group3_genetic.Selector.RankSelector;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
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

		//Min1DConfiguration configuration = new Min1DConfiguration("x^5-x^2+2",-10,10);
		Configuration configuration = new Configuration();
		AbstractChromosomeFactory chromosomeFactory = new TspChromosomeFactory(configuration);
		try {
			configuration.addConstraint(new NumberGenerationConstraint(100));
			configuration.addOperator(new OrderedCrossoverOperator(80));
			//configuration.addOperator(new Min1DCrossoverOperator(80));
			configuration.addOperator(new OrderedMutationOperator(60));
			configuration.addSelector(new RankSelector());
			configuration.setChromosomeFactory(chromosomeFactory);
			configuration.setPopulationSize(150);

		} catch (InvalidProbabilityValueException | InvalidConfigurationException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}

		ViewInterface terminalView = new TerminalView(System.out);

		AbstractGraphView graphFitness = new FitnessEvolutionGraph();
		AbstractGraphView graphPopulationSize = new PopulationSizeGraph();
		AbstractGraphView graphEvolutionPercentage = new EvolutionPercentageGraph();

		JPanel panel = new JPanel();
		//JPanel specialView = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.add(graphFitness.getJPanel());
		panel.add(graphPopulationSize.getJPanel());
		panel.add(graphEvolutionPercentage.getJPanel());

		//DEBUT TEMP
		TspPopulationView salesmanView = new TspPopulationView(4);
		//Min1DPopulationView salesmanView = new Min1DPopulationView(4,5,"x");
		//specialView.add(salesmanView);
		//panel.add(specialView);
		//FIN TEMP

		graphicFrame.add(panel);

		GeneticModel model = new GeneticModel(configuration);
		GeneticController controller = new GeneticController(model);
		controller.setSpecialView(salesmanView, (ViewInterface) salesmanView);
		panel.add(controller.getContainerSpecialView());

		Container content = graphicFrame.getContentPane();
		ToolbarView toolbar = new ToolbarView(controller);
		content.add(toolbar, BorderLayout.NORTH);
		graphicFrame.componentsAdded();

//		model.addView(salesmanView);
		model.addView(terminalView);
		model.addView(toolbar);
		model.addView(graphFitness);
		model.addView(graphPopulationSize);
		model.addView(graphEvolutionPercentage);
		
		model.refreshViews(new ResetPopulationEvent(model));

	}
}
