package fr.isen.cir56.group3_genetic;
import java.util.List;

public class Population{

	private final int initialSizePopulation;
	private List<PersonInterface> people;
	public List myIndividualInterface;
	public List myConfiguration;        

	public Population(int initialSizePopulation) {
		this.initialSizePopulation = initialSizePopulation;
	}
	
        public List<PersonInterface> getPeople(){
            return this.people;
        }
        
	public void selectIndividual() {
	}

}