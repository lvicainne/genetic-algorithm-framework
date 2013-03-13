/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isen.cir56.group3_genetic.View;

import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.Monitor.EndGenerationEvent;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class TerminalView extends AbstractGeneticView {
	private PrintStream stream;

	public TerminalView(PrintStream stream) {
		this.setStream(stream);
	}
	
	public final PrintStream getStream() {
		return stream;
	}

	public final void setStream(PrintStream stream) {
		this.stream = stream;
	}
	
	@Override
	public void refresh(Event event) {
		if(event.hasException()) {
			stream.println("Exception : " + event.getException().toString());
		}
		
		if(event instanceof EndGenerationEvent) {
			EndGenerationEvent myEvent = (EndGenerationEvent) event;
			stream.println("Generation ended !");
			
			PopulationInterface pop =  myEvent.getGeneticModel().getLastPopulation();
			
			stream.println("Number of generations  : " + myEvent.getGeneticModel().getMonitor().getBreeder().getNumberGenerations());
			stream.println("Time elapsed (ms) : " + myEvent.getGeneticModel().getMonitor().getBreeder().getTimeElapse());
			stream.println("Size of the last population : " + myEvent.getPop().size());
			stream.println("Best Chromosome : " + myEvent.getPop().getBetterChromosome());
			
			pop.sortChromosomes();
			List<ChromosomeInterface> list = pop.getChromosomes();
			int i = 0;
			for (ChromosomeInterface chromosomeInterface : list) {
				i++;
				stream.println(i + " : " + chromosomeInterface.getFitnessValue() + " " + chromosomeInterface);
			}
			
		} else {
			stream.println(event.toString());
		}
	}

}
