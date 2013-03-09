package fr.isen.cir56.group3_genetic;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;
import tsp.TspFitnessFunction;


public class App {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		AbstractFitnessFunction fitnessFunction = new TspFitnessFunction();
		
		
		
		GeneticModel model = new GeneticModel(configuration);
		GeneticController controller = new GeneticController(model);
		
		controller.start();
		
		System.out.println("Hello World!");
	}
}
