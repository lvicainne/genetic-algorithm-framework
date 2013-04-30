package fr.isen.cir56.group3_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.AssociatedChromosomeView;
import java.awt.Point;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
@AssociatedChromosomeView(TspChromosomeView.class)
public class City implements GeneInterface {
	private final String name;
	private int id;
	private static int number = 0;
	private Object data;
	private Point point;

	
	public City(String name, Point point) {
		this.name = name;
		this.point = point;
		this.id = City.getNexId();
	}
	
	private static int getNexId() {
		return (++City.number);
	}

	@Override
	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public Object getData() {
		return this.data;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	
}
