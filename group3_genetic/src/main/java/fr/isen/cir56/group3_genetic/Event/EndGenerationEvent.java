package fr.isen.cir56.group3_genetic.Event;

import fr.isen.cir56.group3_genetic.Analyzer.AnalyzerInterface;
import fr.isen.cir56.group3_genetic.Model.GeneticModel;

/**
 *
 * @author Louis VICAINNE louis.vicainne@gmail.com
 */
public class EndGenerationEvent extends Event<GeneticModel> {
	private final AnalyzerInterface analyzer;

	public EndGenerationEvent(GeneticModel sender, AnalyzerInterface analyzer) {
		super(sender);
		this.analyzer = analyzer;
	}

	public AnalyzerInterface getAnalyzer() {
		return analyzer;
	}
	
}
