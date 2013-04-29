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
        PopulationInterface myPopulation = new Population(population.getMaximumSize());
        List<ChromosomeInterface> people = population.getChromosomes();
        
        int peopleSize = people.size();
		int peopleMaxSize = population.getMaximumSize();
		
        for (int i = 0; i < peopleSize - peopleMaxSize; i++) {
            Random rdm1 = new Random();
            Random rdm2 = new Random();
            
            //remplace le joueur i par le vaiqueur du tournoi
            myPopulation.addChromosome(tournament(people.get(rdm1.nextInt(peopleSize)), people.get(rdm2.nextInt(peopleSize))));
        }
		return myPopulation;
    }
	
    public ChromosomeInterface tournament(ChromosomeInterface player1, ChromosomeInterface player2){ // retourne le meilleur joueur càd celui ayant la note la plus basse
        ChromosomeInterface winner;
        if(player1.getFitnessValue() < player2.getFitnessValue()){ 
            winner = player1;
        } else {
            winner = player2;
        }
        return winner;
    }
	
	@Override
	public String toString() {
		return "Tournament selector";
	}
   
}