package fr.isen.cir56.group3_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.View.ChromosomeViewInterface;
import fr.isen.cir56.group3_genetic.View.ChromosomeViewListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TspChromosomeView extends JPanel implements ChromosomeViewListener<City>, ChromosomeViewInterface<City> {

	private ChromosomeInterface chromosome;
	public static final float THICKNESS = 2;
	public static final float DIAMETER = 1.0F;
	private double xFactor;
	private double yFactor;
	private double printedWith;
	private double printedHeight;

	@Override
	public void chromosomeChanged(ChromosomeInterface<City> chromosome) {
		this.chromosome = chromosome;

		repaint();

		double minH = Integer.MAX_VALUE;
		double minW = Integer.MAX_VALUE;
		double maxH = Integer.MIN_VALUE;
		double maxW = Integer.MIN_VALUE;

		List<City> genes = chromosome.getGenes();
		for (City city : genes) {
			Point point = city.getPoint();
			if (point.getX() < minW) {
				minW = point.getX();
			}
			if (point.getX() > maxW) {
				maxW = point.getX();
			}
			if (point.getY() < minH) {
				minH = point.getY();
			}
			if (point.getY() > maxH) {
				maxH = point.getY();
			}

		}

		this.printedWith = maxW - minW;
		this.printedHeight = maxH - minH;

	}

	@Override
	public void resetView() {
		//We reset the view with a null chromosome
		this.chromosomeChanged(null);
	}

	public double getPrintedWith() {
		return this.printedWith;
	}

	public double getPrintedHeight() {
		return this.printedHeight;
	}

	@Override
	public void paintComponent(Graphics graphic) {
		Graphics2D graphic2D = (Graphics2D) graphic;
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphic2D.setRenderingHints(rh);
		graphic2D.setFont(new Font("Arial", Font.BOLD, 13));
		graphic2D.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		graphic2D.setPaint(Color.BLACK);

		//reset the Java2D
		graphic2D.clearRect(0, 0, this.getWidth(), this.getHeight());

		//special name for singers =D :
		this.xFactor = (this.getWidth() - 2 * THICKNESS) / this.getPrintedWith();
		this.yFactor = (this.getHeight() - 2 * THICKNESS) / this.getPrintedHeight();

		if (this.chromosome == null) {
			//if there is no chromosome currently, there is nothing to draw
			return;
		}

		List<GeneInterface> genes = chromosome.getGenes();
		GeneralPath path = new GeneralPath();

		if (genes.size() > 0) {
			City city1 = (City) genes.get(0);
			path.moveTo(
					(float) city1.getPoint().getX() * this.xFactor, 
					(float) city1.getPoint().getY() * this.yFactor);
		}

		for (GeneInterface geneInterface : genes) {
			if (geneInterface instanceof City) {
				City city = (City) geneInterface;
				this.drawCity(graphic2D, city);
				graphic2D.setPaint(Color.BLACK);
				path.lineTo(
						(float) city.getPoint().getX() * this.xFactor, 
						(float) city.getPoint().getY() * this.yFactor);
			}
		}

		path.closePath();

		graphic2D.draw(path);
		graphic2D.drawString("Fitness " + this.chromosome.getFitnessValue(), 0, 0);
	}

	public void drawCity(Graphics2D graphic2D, City city) {
		graphic2D.setPaint(Color.ORANGE);
		graphic2D.draw(
				new Ellipse2D.Double(
					city.getPoint().getX() * this.xFactor - DIAMETER / 2, 
					city.getPoint().getY() * this.yFactor - DIAMETER / 2, 
					DIAMETER, 
					DIAMETER)
				);
		
		graphic2D.drawString(
				city.toString(), 
				(float) (city.getPoint().getX() * this.xFactor + DIAMETER), 
				(float) (city.getPoint().getY() * this.yFactor + DIAMETER)
			);
	}
}
