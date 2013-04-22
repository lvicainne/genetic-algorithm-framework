package fr.isen.cir56.group3_genetic.Implementations.min1d;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Geometry.DoublePoint;
import fr.isen.cir56.group3_genetic.View.ChromosomeViewListener;
import java.util.List;
import org.jfree.data.xy.XYSeries;

	
	
	
/**
 *
 * @author Wasp
 */
public class Min1DChromosomeView implements ChromosomeViewListener<Min1DValue>{

	private ChromosomeInterface chromosome;
	
	private XYSeries points;

	public Min1DChromosomeView(XYSeries points) {
		this.points = points;
	}
	
	
	@Override
	public void chromosomeChanged(ChromosomeInterface<Min1DValue> chromosome) {
		this.chromosome = chromosome;
		if (chromosome != null) {
			List<Min1DValue> genes = chromosome.getGenes();
			for (Min1DValue min1DValue : genes) {
				DoublePoint point = (DoublePoint) min1DValue.getData();
				points.add(point.x, point.y);
			}
		}
	}

	public XYSeries getPoints() {
		return this.points;
	}

	@Override
	public void resetView() {
		//reset the view with a null chromosome
		this.chromosomeChanged(null);
	}

}
