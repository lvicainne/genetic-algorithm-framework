package fr.isen.cir56.group3_genetic;

import java.util.List;

public class Population {

	private final int initialSizePopulation;
	private List<PersonInterface> people;
	public List myIndividualInterface;
	public List myConfiguration;

	public Population(int initialSizePopulation) {
		this.initialSizePopulation = initialSizePopulation;
	}

	public void selectIndividual() {
	}

	public List<PersonInterface> getPeople() {
		return people;
	}
}
