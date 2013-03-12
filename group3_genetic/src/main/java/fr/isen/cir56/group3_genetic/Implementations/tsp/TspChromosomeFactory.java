/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.Configuration.ConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.Chromosome;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeFactoryInterface;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TspChromosomeFactory implements ChromosomeFactoryInterface {
	private final ConfigurationInterface configuration;
	
	
	public TspChromosomeFactory(ConfigurationInterface configuration) {
		this.configuration = configuration;
	}
	
	char matrice [][]= {{'a', 'b', 'c'}, 
{'d', 'e', 'f'}, 
{'g', 'h', 'i'}};
	
	public static List<GeneInterface> getCities() {
		List<GeneInterface> list = new LinkedList<GeneInterface>();
		list.add(new City("Toulouse"));
		list.add(new City("Marseille"));
		list.add(new City("Valence"));
		list.add(new City("Lyon"));
		list.add(new City("Paris"));
		list.add(new City("Amiens"));
		list.add(new City("Lille"));

		return list;
	}
	
	public double distance(GeneInterface geneSrc, GeneInterface geneDst) {
		City city1 = (City) geneSrc;
		City city2 = (City) geneDst;
		
		if(city1.getId() > city2.getId()) {
			City temp = city1;
			city1 = city2;
			city2 = city1;
		}
		
		if(city2.getId() - city1.getId() == 1) {
			return 1;
		} else {
			return 4;
		}
		
		//return 1;
//		return 99999 -(city1.getId()+city2.getId());
		//return (city1.getId()-city2.getId());
	}

	public ChromosomeInterface getNewChromosome() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public PopulationInterface getInitialPopulation() {
		PopulationInterface population = new Population(this.configuration);
		List<GeneInterface> cities = TspChromosomeFactory.getCities();
		
		int numberChromosomes = this.configuration.getPopulationSize();
		for (int i = 0; i < numberChromosomes; i++) {
			
			//Shuffle the cities
			Collections.shuffle(cities);
			
			List<GeneInterface> cities2 = new ArrayList<GeneInterface>();
			cities2.addAll(cities);
			
			ChromosomeInterface ch = new Chromosome(this.configuration);
			ch.setGenes(cities2);
			
			System.out.println(cities);
			
			population.addChromosome(ch);

		}
		
		return population;
	}
	
}
