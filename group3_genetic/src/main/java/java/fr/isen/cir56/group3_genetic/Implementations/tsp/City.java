package fr.isen.cir56.group3_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.Genotype.AbstractGene;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class City extends AbstractGene implements GeneInterface {
	private final String name;
	private int id;
	private static int number = 0;
	

	
	public City(String name) {
		this.name = name;
		this.id = City.getNexId();
	}
	
	private static int getNexId() {
		return (++City.number);
	}

	public void setData(Object data) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Object getData() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
}
