package fr.isen.cir56.group3_genetic.Implementations.curveApproximation;

import fr.isen.cir56.group3_genetic.Configuration.GeneticConfigurationInterface;
import fr.isen.cir56.group3_genetic.Genotype.AbstractChromosomeFactory;
import fr.isen.cir56.group3_genetic.Genotype.Chromosome;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Geometry.DoublePoint;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.AssociatedView;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.DefaultConstructor;
import fr.isen.cir56.group3_genetic.Wizard.Annotations.Parameter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Wasp
 */
public class CurveAppChromosomeFactory extends AbstractChromosomeFactory {

	private final GeneticConfigurationInterface configuration;
	private final CurveAppConfiguration curveConfig;
	
	@AssociatedView(CurveAppPopulationView.class)
	@DefaultConstructor
	@Parameter(name={"Expression","xMin","xMax","yMin","yMax"},defaultValue={"x^2", "0", "10", "0", "10"})
	public CurveAppChromosomeFactory(GeneticConfigurationInterface configuration, String algebricExpression, int xMin, int xMax, int yMin, int yMax) {
		this.configuration = configuration;
		this.curveConfig = new CurveAppConfiguration(algebricExpression, xMin, xMax, yMin, yMax);
	}
	
	@Override
	protected double evaluate(ChromosomeInterface chromosome) {
		List<CurveAppValue> genes = chromosome.getGenes();
		CurveAppValue curveAppValue = genes.get(0);
		
		String AlgebricExpression = this.curveConfig.getAlgebricExpression();
		
		org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
			
		myParser.addStandardFunctions();
		myParser.addStandardConstants();
		myParser.addVariable("x", 0);
		
		myParser.parseExpression(AlgebricExpression);
		double xPoint = curveAppValue.getData().getX();
		double yPoint = curveAppValue.getData().getY();
		
		myParser.addVariable("x", xPoint);
		
		double yCurve = myParser.getValue();
		
		double result = Math.abs(yPoint-yCurve);
		
		return 1/result;
	}

	@Override
	public PopulationInterface getNewPopulation() {
		int numberChromosomes = this.configuration.getPopulationSize();
		PopulationInterface population = new Population(numberChromosomes);
		
		double xMin = this.curveConfig.getxMin();
		double xMax = this.curveConfig.getxMax();
		double yMax = this.curveConfig.getyMax();
		double yMin = this.curveConfig.getyMin();
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

	public CurveAppConfiguration getCurveConfig() {
		return curveConfig;
	}
	
	
	
}
