package fr.isen.cir56.group3_genetic.Implementations.curveApproximation;

import fr.isen.cir56.group3_genetic.Genotype.AbstractChromosomeFactory;
import fr.isen.cir56.group3_genetic.Genotype.Chromosome;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Geometry.DoublePoint;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.AssociatedView;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.DefaultConstructor;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Wasp
 */
public class CurveAppChromosomeFactory extends AbstractChromosomeFactory {

	private final CurveAppConfiguration configuration;
	
	@AssociatedView(CurveAppPopulationView.class)
	@DefaultConstructor
	public CurveAppChromosomeFactory(CurveAppConfiguration configuration) {
		this.configuration = configuration;
	}

	
	@Override
	protected double evaluate(ChromosomeInterface chromosome) {
		GeneInterface curveAppValue = chromosome.getGene(0);
		
		String AlgebricExpression = this.configuration.getAlgebricExpression();
		
		org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
			
		myParser.addStandardFunctions();
		myParser.addStandardConstants();
		myParser.addVariable("x", 0);
		
		myParser.parseExpression(AlgebricExpression);
		double xPoint = ((DoublePoint)curveAppValue.getData()).x;
		double yPoint = ((DoublePoint)curveAppValue.getData()).y;
		
		myParser.addVariable("x", xPoint);
		
		double yCurve = myParser.getValue();
		
		double result = Math.abs(yPoint-yCurve);
		
		return result;
	}

	@Override
	public PopulationInterface getNewPopulation() {
		int numberChromosomes = this.configuration.getPopulationSize();
		PopulationInterface population = new Population(numberChromosomes);
		
		double xMin = this.configuration.getxMin();
		double xMax = this.configuration.getxMax();
		double yMax = this.configuration.getyMax();
		double yMin = this.configuration.getyMin();
		double rangeX = xMax - xMin;
		double rangeY = yMax - yMin;
		
		for (int i = 0; i < numberChromosomes; i++) {
			
			List<GeneInterface> value = new LinkedList<>();
			Random random = new Random();
			value.add(new CurveAppValue(random.nextDouble() * rangeX + xMin, random.nextDouble() * rangeY + yMin));// on ajoute à la population un point placé aléatoirement
			
		
			ChromosomeInterface ch = new Chromosome(this.configuration);
			
			ch.setGenes(value);
		
			population.addChromosome(ch);
		}
		
		return population;
	}
	
}
