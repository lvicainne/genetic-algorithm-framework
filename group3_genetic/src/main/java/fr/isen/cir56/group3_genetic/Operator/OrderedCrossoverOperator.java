package fr.isen.cir56.group3_genetic.Operator;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;
import fr.isen.cir56.group3_genetic.Utils.Math.Probability.InvalidProbabilityValueException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A crossover operator for ordred genes
 * @author Adrien STADLER adrien.stadler@gmail.com
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class OrderedCrossoverOperator extends AbstractCrossoverOperator {
	public static final double probability = 0.5;

	public OrderedCrossoverOperator() throws InvalidProbabilityValueException {
		this(probability);
	}
	
	public OrderedCrossoverOperator(double probability) throws InvalidProbabilityValueException {
		super(probability);
	}
		
	@Override
	public void crossover(ChromosomeInterface ch1, ChromosomeInterface ch2) {
		List<GeneInterface> genesCh1 = ch1.getGenes();
		List<GeneInterface> genesCh2 = ch2.getGenes();
			
		int size = ch1.size();
		int alleleLocation1 = AbstractOperator.randomGenerator.nextInt(size);
		int alleleLocation2 = AbstractOperator.randomGenerator.nextInt(size);
			
		if(alleleLocation1 > alleleLocation2) { // swap de références !!! ça ne marche pas //SWAP de int, type primitif, ça marche !
			int temp = alleleLocation2;
			alleleLocation2 = alleleLocation1;
			alleleLocation1 = temp;
			
		}
		
		List<GeneInterface> newGenesCh1 = new ArrayList<>(size);
		List<GeneInterface> newGenesCh2 = new ArrayList<>(size);
		
		newGenesCh1.addAll(genesCh1);
		for(int i = alleleLocation1; i < alleleLocation2; i++) {
			GeneInterface tempGene = genesCh2.get(i);
			
			int otherIndex = newGenesCh1.indexOf(tempGene);
			Collections.swap(newGenesCh1, i, otherIndex);
			
			newGenesCh1.set(i, tempGene);
		}
		
		newGenesCh2.addAll(genesCh2);
		for(int i = alleleLocation1; i < alleleLocation2; i++) {
			GeneInterface tempGene = genesCh1.get(i);
			
			int otherIndex = newGenesCh2.indexOf(tempGene);
			Collections.swap(newGenesCh2, i, otherIndex);
			
			newGenesCh2.set(i, tempGene);
		}
		
		ch1.setGenes(newGenesCh1);
		ch2.setGenes(newGenesCh2);
					
	}
	
}
