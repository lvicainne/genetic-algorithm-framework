package fr.isen.cir56.group3_genetic;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Configuration.InvalidConfigurationException;
import fr.isen.cir56.group3_genetic.Constraint.ConstraintInterface;
import fr.isen.cir56.group3_genetic.Constraint.NumberGenerationConstraint;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Implementations.tsp.TspChromosomeFactory;
import fr.isen.cir56.group3_genetic.Operator.OrderedCrossoverOperator;
import fr.isen.cir56.group3_genetic.Implementations.tsp.TspFitnessFunction;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import fr.isen.cir56.group3_genetic.Operator.OrderedMutationOperator;
import fr.isen.cir56.group3_genetic.Selector.RankSelector;
import fr.isen.cir56.group3_genetic.Selector.SelectorInterface;
import fr.isen.cir56.group3_genetic.View.Graph.AbstractGraphView;
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
		

		try {
			org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
			
			myParser.addStandardFunctions();
			myParser.addStandardConstants();
			myParser.addVariable("x", 10);
			myParser.parseExpression("1+x");

			double result = myParser.getValue();
			System.out.println("x + 1 = " + result);
			myParser.addVariable("x", 12);
			result = myParser.getValue();
			System.out.println("x + 1 = " + result);
		} catch (Exception e) {
			System.out.println("Error with evaluation");
		}



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
		MainFrameView graphicFrame = new MainFrameView();

		AbstractGraphView graphFitness = new FitnessEvolutionGraph();
		AbstractGraphView graphPopulationSize = new PopulationSizeGraph();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.add(graphFitness.getJPanel());
		panel.add(graphPopulationSize.getJPanel());
		panel.add(graphPopulationSize.getJPanel());
		panel.add(graphPopulationSize.getJPanel());
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

	}
}
