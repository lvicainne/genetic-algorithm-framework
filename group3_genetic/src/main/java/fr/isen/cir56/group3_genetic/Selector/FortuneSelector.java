package fr.isen.cir56.group3_genetic.Selector;

import fr.isen.cir56.group3_genetic.PersonInterface;
import fr.isen.cir56.group3_genetic.Population;
import java.util.List;
import java.util.Random;

public class FortuneSelector implements SelectorInterface {

    public Population select(Population population) {
        Population myPopulation = population.clone();
        List<PersonInterface> people = myPopulation.getPeople();
        int sum = 0;
        
        for (PersonInterface personInterface : people) {
            sum += personInterface.getValue();
        }
        
        Random rdm = new Random();
        int random = rdm.nextInt((int)Math.round(sum));
		
		return myPopulation;
    }
}