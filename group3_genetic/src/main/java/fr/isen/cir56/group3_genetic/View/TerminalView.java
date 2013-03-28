package fr.isen.cir56.group3_genetic.View;

import fr.isen.cir56.group3_genetic.Event.EndGenerationEvent;
import fr.isen.cir56.group3_genetic.Event.ResumeGenerationEvent;
import fr.isen.cir56.group3_genetic.Event.StartGenerationEvent;
import fr.isen.cir56.group3_genetic.Event.SuspendGenerationEvent;
import fr.isen.cir56.group3_genetic.Genotype.ChromosomeInterface;
import fr.isen.cir56.group3_genetic.PopulationInterface;
import java.io.PrintStream;
import java.util.List;

/**
 *
 * @author Adrien STADLER adrien.stadler@gmail.com
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
			
			if(pop != null) {
				//The pop may be null if we stop the process before generations.
				
				stream.println("Size of the last population : " + pop.size());
				stream.println("Best Chromosome : " + pop.getBetterChromosome());

				stream.println();
				stream.println("N°:Value Genome");

				pop.sortChromosomes();
				List<ChromosomeInterface> list = pop.getChromosomes();
				int i = 0;
				for (ChromosomeInterface chromosomeInterface : list) {
					i++;
					stream.println(i + " : " + chromosomeInterface.getFitnessValue() + " " + chromosomeInterface);
				}
			}
		} else if(event instanceof StartGenerationEvent) {
			stream.println("Start generation...");
		} else if(event instanceof SuspendGenerationEvent) {
			stream.println("Suspend last generation...");
		} else if(event instanceof ResumeGenerationEvent) {
			stream.println("Resume last generation...");
		} else {
			stream.println(event.toString());
		}
	}

}
