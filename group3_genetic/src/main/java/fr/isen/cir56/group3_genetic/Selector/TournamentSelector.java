package fr.isen.cir56.group3_genetic.Selector;

import fr.isen.cir56.group3_genetic.PersonInterface;
import fr.isen.cir56.group3_genetic.Population;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TournamentSelector implements SelectorInterface {

    public Population select(Population population) {
        Population myPopulation = population.clone();
        List<PersonInterface> people = myPopulation.getPeople();
        
        int peopleSize = people.size();
        
        for (int i = 0; i < peopleSize; i++) {
            Random rdm1 = new Random();
            Random rdm2 = new Random();
            
            //remplace le joueur i par le vaiqueur du tournoi
            people.set(i,tournament(people.get(rdm1.nextInt(peopleSize)), people.get(rdm2.nextInt(peopleSize))));
        }
		
		return myPopulation;
    }
	
    public PersonInterface tournament(PersonInterface player1, PersonInterface player2){ // retourne le meilleur joueur
        PersonInterface winner;
        if(player1.getValue() > player2.getValue()){
            winner = player1;
        } else {
            winner = player2;
        }
        return winner;
    }
   
}