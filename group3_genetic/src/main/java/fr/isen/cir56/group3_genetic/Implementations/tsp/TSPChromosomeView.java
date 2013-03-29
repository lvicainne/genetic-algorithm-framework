package fr.isen.cir56.group3_genetic.Implementations.tsp;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.View.ChromosomeViewListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TSPChromosomeView extends JPanel implements ChromosomeViewListener {
	private ChromosomeInterface chromosome;
	public static final float THICKNESS = 2;
	public static final float DIAMETER = 1.0F;

	@Override
	public void chromosomeChanged(ChromosomeInterface chromosome) {
		this.chromosome = chromosome;

		// exécution dans l'EDT vu que c'est une commande Swing
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				repaint();
			}
		});

	}
	
	@Override
	public void resetView() {
		//We reset the view with a null chromosome
		this.chromosomeChanged(null);
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

		if (this.chromosome == null) {
			//if there is no chromosome currently, there is nothing to draw
			return;
		}

		List<GeneInterface> genes = chromosome.getGenes();
		GeneralPath path = new GeneralPath();

		if (genes.size() > 0) {
			City city1 = (City) genes.get(0);
			path.moveTo((float) city1.getPoint().getX(), (float) city1.getPoint().getY());
		}

		for (GeneInterface geneInterface : genes) {
			if (geneInterface instanceof City) {
				City city = (City) geneInterface;
				this.drawCity(graphic2D, city);
				graphic2D.setPaint(Color.BLACK);
				path.lineTo((float) city.getPoint().getX(), (float) city.getPoint().getY());
			}
		}

		path.closePath();

		graphic2D.draw(path);
		graphic2D.drawString("Fitness " + this.chromosome.getFitnessValue(), 0, 0);
	}

	public void drawCity(Graphics2D g2d, City city) {
		g2d.setPaint(Color.ORANGE);
		g2d.draw(new Ellipse2D.Double(city.getPoint().getX() - DIAMETER / 2, city.getPoint().getY() - DIAMETER / 2, DIAMETER, DIAMETER));
		g2d.drawString(city.toString(), (float) city.getPoint().getX() + DIAMETER, (float) city.getPoint().getY() + DIAMETER);
	}

}
