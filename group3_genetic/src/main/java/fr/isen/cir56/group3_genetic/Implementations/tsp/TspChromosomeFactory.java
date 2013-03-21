package fr.isen.cir56.group3_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.Chromosome;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeFactoryInterface;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *getInitialPopulation
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TspChromosomeFactory implements ChromosomeFactoryInterface {
	private final ConfigurationInterface configuration;
	
	
	public TspChromosomeFactory(ConfigurationInterface configuration) {
		this.configuration = configuration;
	}
	
	public static List<GeneInterface> getCities() {
		List<GeneInterface> list = new LinkedList<>();
		list.add(new City("Toulouse", new Point(1,1)));
		list.add(new City("Marseille", new Point(1,5)));
		list.add(new City("Valence", new Point(1,10)));
		list.add(new City("Lyon", new Point(1,20)));
		list.add(new City("Paris", new Point(1,40)));
		list.add(new City("Amiens", new Point(5,40)));
		list.add(new City("Lille", new Point(5,20)));

		return list;
	}
	
	public double distance(GeneInterface geneSrc, GeneInterface geneDst) {
		City city1 = (City) geneSrc;
		City city2 = (City) geneDst;
		
		Point point1 = city1.getPoint();
		Point point2 = city2.getPoint();
		
		return Point.distance(point1.getX(), point1.getY(), point2.getX(), point2.getY());
	}

	@Override
	public ChromosomeInterface getNewChromosome() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public PopulationInterface getInitialPopulation() {
		PopulationInterface population = new Population(this.configuration);
		List<GeneInterface> cities = TspChromosomeFactory.getCities();
		
		int numberChromosomes = this.configuration.getPopulationSize();
		for (int i = 0; i < numberChromosomes; i++) {
			
			//Shuffle the cities
			Collections.shuffle(cities);
			
			List<GeneInterface> cities2 = new ArrayList<>();
			cities2.addAll(cities);
			
			ChromosomeInterface ch = new Chromosome(this.configuration);
			ch.setGenes(cities2);
			
			population.addChromosome(ch);

		}
		
		return population;
	}
	
}
