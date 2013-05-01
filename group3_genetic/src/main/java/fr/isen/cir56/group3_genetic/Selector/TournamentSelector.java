package fr.isen.cir56.group3_genetic.Selector;

import fr.isen.cir56.group3_genetic.Genotype.Chromosome;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Population;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.util.List;
import java.util.Random;

public class TournamentSelector implements SelectorInterface { //problème de population qui augmente

	@Override
    public PopulationInterface select(PopulationInterface population) {
        PopulationInterface myPopulation = population.clone();
        List<ChromosomeInterface> people = myPopulation.getChromosomes();
        
		int maxSize = population.getMaximumSize();
		Random rdm = new Random();
        while(people.size() > maxSize) { // on supprime ceux avec la plus grande note 
            
            
            //remplace le joueur i par le vaiqueur du tournoi
            myPopulation.removeChromosome(tournament(people.get(rdm.nextInt(people.size())), people.get(rdm.nextInt(people.size()))));
        }
		return myPopulation;
    }
	
    public ChromosomeInterface tournament(ChromosomeInterface player1, ChromosomeInterface player2){ // retourne le meilleur joueur càd celui ayant la note la plus basse
        ChromosomeInterface looser;
        if(player1.getFitnessValue() > player2.getFitnessValue()){ 
            looser = player1;
        } else {
            looser = player2;
        }
        return looser;
    }
	
	@Override
	public String toString() {
		return "Tournament selector";
	}
   
}