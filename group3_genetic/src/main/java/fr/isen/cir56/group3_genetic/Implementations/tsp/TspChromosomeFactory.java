package fr.isen.cir56.group3_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.AbstractFactory;
import fr.isen.cir56.group3_genetic.Genotype.Chromosome;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.EmptyChromosomeException;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Wizard.AssociatedView;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TspChromosomeFactory extends AbstractFactory {
	private static int chromosomeSize = 25;
	private static int rayon = 400;
	private final ConfigurationInterface configuration;
	
	@AssociatedView(TspPopulationView.class)
	public TspChromosomeFactory(ConfigurationInterface configuration) {
		this.configuration = configuration;
	}
	
	public static List<City> getCities() {
		List<City> list = new LinkedList<>();
		/*list.add(new City("Toulouse", new Point(1,1)));
		list.add(new City("Marseille", new Point(1,5)));
		list.add(new City("Valence", new Point(1,10)));
		list.add(new City("Lyon", new Point(1,20)));
		list.add(new City("Paris", new Point(1,40)));
		list.add(new City("Amiens", new Point(5,40)));
		list.add(new City("Lille", new Point(100,100)));*/
		
		for(int i = 0; i < chromosomeSize; i++) {
			int x = (int) Math.round(rayon+rayon*Math.cos(i));
			int y = (int) Math.round(rayon+rayon*Math.sin(i));
			list.add(new City(""+i, new Point(x, y)));
		}
		

		return list;
	}
	
	public double distance(City city1, City city2) {
		Point point1 = city1.getPoint();
		Point point2 = city2.getPoint();
		
		return Point.distance(point1.getX(), point1.getY(), point2.getX(), point2.getY());
	}

	@Override
	public PopulationInterface getNewPopulation() {
		int numberChromosomes = this.configuration.getPopulationSize();
		PopulationInterface population = new Population(numberChromosomes);
		List<City> cities = TspChromosomeFactory.getCities();
		
		
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
	

	@Override
	protected double evaluate(ChromosomeInterface chromosome) throws EmptyChromosomeException {
		List<City> genes = chromosome.getGenes();
		Iterator<City> iterator = genes.iterator();
		
		if(genes.size() < 1) {
			throw new EmptyChromosomeException();
		}
		
		City geneSrc = iterator.next();
		City geneInit = geneSrc;
		
		double distanceSum = 0;
		while(iterator.hasNext()) {
			City geneDst = iterator.next();
			distanceSum += this.distance(geneSrc, geneDst);
			geneSrc = geneDst;
		}
		
		distanceSum += this.distance(geneSrc, geneInit);
		
		return distanceSum;
	}
}
