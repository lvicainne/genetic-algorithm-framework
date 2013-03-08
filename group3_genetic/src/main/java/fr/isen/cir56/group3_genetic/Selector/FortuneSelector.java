package fr.isen.cir56.group3_genetic.Selector;

import fr.isen.cir56.group3_genetic.PersonInterface;
import fr.isen.cir56.group3_genetic.Population;
import java.util.List;
import java.util.Random;

public class FortuneSelector implements Selector {

    public void select(Population population) {
        
        List<PersonInterface> people = population.getPeople();
        int sum = 0;
        
        for (PersonInterface personInterface : people) {
            sum += personInterface.getValue();
        }
        
        Random rdm = new Random();
        int random = rdm.nextInt((int)Math.round(sum));
    }
}