package fr.isen.cir56.group3_genetic;

import fr.isen.cir56.group3_genetic.Configuration.Configuration;
import fr.isen.cir56.group3_genetic.Controller.GeneticController;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;


public class App {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration(null, null, 1);
		
		GeneticModel model = new GeneticModel(configuration);
		GeneticController controller = new GeneticController(model);
		
		controller.start();
		
		System.out.println("Hello World!");
	}
}
