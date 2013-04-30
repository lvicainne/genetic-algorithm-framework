package fr.isen.cir56.group3_genetic.View;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Genotype.GeneInterface;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public interface ChromosomeViewInterface<GeneType extends GeneInterface> {
	public void chromosomeChanged(ChromosomeInterface<GeneType> chromosome);
}
