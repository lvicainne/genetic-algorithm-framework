package fr.isen.cir56.group3_genetic;
import java.util.List;

public class Population{

	private final int initialSizePopulation;
	public List<PersonInterface> people;
        public List<PersonInterface> selectedPerson;
	public List myIndividualInterface;
	public List myConfiguration;        

	public Population(int initialSizePopulation) {
		this.initialSizePopulation = initialSizePopulation;
	}
	

	public void selectIndividual() {
	}

}