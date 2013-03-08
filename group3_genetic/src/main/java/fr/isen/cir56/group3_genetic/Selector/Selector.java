package fr.isen.cir56.group3_genetic.Selector;

import fr.isen.cir56.group3_genetic.PersonInterface;
import java.util.List;

public interface Selector {

	PersonInterface select(List<PersonInterface> people);
}