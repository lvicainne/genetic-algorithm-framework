package fr.isen.cir56.group3_genetic.Genotype;

import java.util.Comparator;

/**
 * A comparator for Chromosome. Compra by Age, the older first.
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class AgeChromosomeComparator implements Comparator {

	public AgeChromosomeComparator() {
		
	}
	
	public int compare(Object o1, Object o2) {
		if(!((o1 instanceof ChromosomeInterface) && (o2 instanceof ChromosomeInterface))) {
			throw new UnsupportedOperationException("This comparator can ONLY compare Chromosomes");
		}
		
		ChromosomeInterface ch1 = (ChromosomeInterface) o1;
		ChromosomeInterface ch2 = (ChromosomeInterface) o2;
		
		return ch1.getAge() - ch2.getAge();
	}
}
